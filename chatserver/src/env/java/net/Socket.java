/* $Id: Socket.java 767 2009-10-14 06:46:59Z cartho $ */

package java.net;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.OutputStream;

public class Socket {

    InputStream istr;
    OutputStream ostr;

    static Socket conn1 = null;
    static Socket conn2 = null;
    public static PortStatus port = new PortStatus();
    static Socket acceptingConn = null;

    public Socket() {
	try {
	    istr = new PipedInputStream();
	    ostr = new PipedOutputStream((PipedInputStream) istr);
	} catch (IOException e) {
	    System.err.println("Cannot create pipe: " + e);
	}
    }

    public Socket(InetAddress addr, int port) throws IOException {
	this();
	connect(new InetSocketAddress(addr, port));
    }

    void checkServerPort() throws IOException {
	if (! port.isOpen) {
	    throw new IOException("Connection refused");
	}
    }

    /** Allow a client to connect to a port. */
    public void connect(SocketAddress addr) throws IOException {
	InputStream tmpIn;
	synchronized (port) {
	    checkServerPort();
	    while (acceptingConn == null) {
		try {
		    port.wait();
		} catch (InterruptedException e) {
		}
		checkServerPort();
	    }
	    // set up reverse pipe on server side
	    tmpIn = istr;
	    istr = acceptingConn.istr;
	    acceptingConn.istr = tmpIn;
	    acceptingConn = null;
	    port.notifyAll();
	}
    }

    void doClose() throws IOException {
	ostr.flush();
	ostr.close();
	istr.close();
    }

    /** Closes a socket. The difficulty is that two socket objects
	typically exist, one on the client and one on the server side.
	Both entities can call close, or only one. However, the global
	entry on the port table should only be freed once! Therefore,
	only remove that entry when the <em>server</em> closes the
	socket. */
    public void close() throws IOException {
	// cover case when (client) socket was never used
	//if (conn == -1) {
	//    return;
	//}
	// disable check for efficiency reasons
	synchronized(port) {
	    doClose();
	    if (conn1 == this) {
		conn1 = null;
	    } else if (conn2 == this) {
		conn2 = null;
	    } else {
		return; // client close should only close pipes
	    }
	    // another thread has closed the entire ServerSocket
	    if (! port.isOpen) {
		return;
	    }

	    /* Fill "hole" in array of connections when more recent
	     * connections are still open. Renumber connection first.
	     * Example: 1 2 X 4 -> 1 2 X 3 -> 1 2 3 X */
	    port.notifyAll();
	    // notify waiting server in accept about free conn. slot
	}
    }

    /** Allow for client connections on this port. Returns a new
	socket for an established connection. Only a fixed number of
	connections are allowed, and the server blocks until a free
	connection is available. */
    public static Socket accept() throws IOException {
	Socket sock;
	synchronized (port) {
	    // only one accepting server thread per port
	    // only up to MAXCONN connections
	    while ((conn1 != null) && (conn2 != null) ||
		   (acceptingConn != null)) {
		try {
		    port.wait();
		} catch (InterruptedException e) {
		}
	    }
	    InputStream in;
	    sock = new Socket();
	    in = sock.istr;
	    acceptingConn = sock;
	    if (conn1 == null) {
		conn1 = sock;
	    } else {
		assert (conn2 == null);
		conn2 = sock;
	    }
	    // other threads may now change indices in the pool
	    port.notifyAll();
	    while ((in == sock.istr) && (port.isOpen)) {
		// wait for client connection resulting in "pipe swap"
		try {
		    port.wait();
		} catch (InterruptedException e) {
		}
 	    }
	    assert (acceptingConn == null);
	    if (! port.isOpen) {
		throw new IOException("Connection reset by server");
	    }
	    // allow another accept call to be executed
	    return sock;
	}
    }

    public OutputStream getOutputStream() throws IOException {
	return ostr;
    }

    public InputStream getInputStream() throws IOException {
	return istr;
    }
}
