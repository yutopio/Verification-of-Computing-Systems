/* $Id: ServerSocket.java 838 2010-11-24 08:16:23Z cartho $ */

package env.java.net;

/* ServerSocket stub. */

import gov.nasa.jpf.jvm.Verify;
import java.io.IOException;
import java.lang.Thread;

public class ServerSocket {
  
  public ServerSocket(int port) throws IOException {
    if (Verify.getBoolean()) {
      throw new java.io.IOException("Port bind failure.");
    }
  }

  public Socket accept() throws IOException {
    Thread.yield();
    if (Verify.getBoolean()) {
      throw new java.io.IOException("Some socket error.");
    }
    return new Socket();
  }
}
