/* $Id: DaytimeServer.java 838 2010-11-24 08:16:23Z cartho $ */

/* Daytime server (implementing RFC 867).
 * This modified version aborts after having served one client. */

import java.io.IOException;
import env.java.io.OutputStreamWriter;
import env.java.net.ServerSocket;
import env.java.net.Socket;
import env.java.util.Date;

public class DaytimeServer {

  public final static int PORT = 1024;  //13;

  public static void main(String[]args) {
    Boolean connected = false;
    ServerSocket server = null;
    try {
      server = new ServerSocket(PORT);
      Socket connection = null;

      while (true) {
        try {
          connection = server.accept();
          connected = true;

          OutputStreamWriter out
            = new OutputStreamWriter(connection.getOutputStream());
          Date now = new Date();
          out.write(now.toString() + "\n");
          out.flush();
        }
        catch(IOException e) {
          System.err.println(e);
        }
        finally {
          try {
            if (connection != null)
              connection.close();
          }
          catch(IOException e) {
            System.err.println(e);
          }
          finally {
            connected = false;
          }
          System.out.println("Connection closed.");
        }
      }
    }
    catch(IOException e) {
      System.err.println(e);
    }
    finally {
      // Close listening socket.
      try {
        if (server != null)
          server.close();
      }
      catch(IOException e) {
        System.err.println(e);
      }

      // Connection must be closed at the end.
      assert !connected;
    }
  }
}
