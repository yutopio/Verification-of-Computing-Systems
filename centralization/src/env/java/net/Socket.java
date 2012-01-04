/* $Id: Socket.java 713 2009-09-24 06:45:20Z cartho $ */

package env.java.net;

/* Stub class for java.net.Socket. */

import env.java.io.InputStream;
import env.java.io.OutputStream;
import java.io.IOException;

public class Socket {
  InputStream hardCodedInput;

  public Socket() {
     hardCodedInput = new InputStream();
  }

  public void connect(InetSocketAddress addr)
    throws java.net.ConnectException {
  }

  public void close() throws IOException {
  }

  public OutputStream getOutputStream() throws IOException {
    return new OutputStream();
  }

  public InputStream getInputStream() throws IOException {
    return hardCodedInput;
  }
}
