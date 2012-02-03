/* $Id: ServerSocket.java 767 2009-10-14 06:46:59Z cartho $ */

package java.net;

import java.io.IOException;

public class ServerSocket {

    public ServerSocket(int p) throws IOException {
	synchronized (Socket.port) {
	    Socket.port.isOpen = true;
	}
    }

    public Socket accept() throws IOException {
	return Socket.accept();
    }

    public void bind(InetAddress host, int p) {
    }

    public void doClose(Socket conn) throws IOException {
	if (conn != null) {
	    conn.close();
	}
    }

    public void close() throws IOException {
	synchronized (Socket.port) {
	    doClose(Socket.conn1);
	    doClose(Socket.conn2);
	    Socket.conn1 = null;
	    Socket.conn2 = null;
	    Socket.port.isOpen = false;
	    Socket.port.notifyAll();
	}
    }
}
