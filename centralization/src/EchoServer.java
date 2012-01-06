import env.java.io.BufferedReader;
import env.java.io.InputStreamReader;
import java.io.IOException;
import env.java.io.OutputStreamWriter;
import env.java.net.ServerSocket;
import env.java.net.Socket;

public class EchoServer {

  public final static int DEFAULT_PORT = 1024;

  public static void main(String[]args) {

    int port = DEFAULT_PORT;
    try {
      ServerSocket server = new ServerSocket(port);

      Socket connection = null;
      boolean done = false;
      while (!done) {

        try {
          connection = server.accept();
          OutputStreamWriter out
            = new OutputStreamWriter(connection.getOutputStream());
          InputStreamReader istr =
            new InputStreamReader(connection.getInputStream());
          BufferedReader in = new BufferedReader(istr);
          String line;
          int n = 0;
          while ((line = in.readLine()) != null) {
            n++;
            out.write("Echo " + n + ": " + line + "\n");
            out.flush();
          }
	  out.close();
          connection.close();
          done = true;
        } catch(IOException e) {
          System.err.println(e);
        } finally {
          try {
            if (connection != null)
              connection.close();
          } catch(IOException e) {
          }
          System.out.println("Connection closed.");
        }
      }                         // end while
      server.close();
    } catch(IOException e) {
      System.err.println(e);
    }                           // end catch
  }                             // end main
}                               // end EchoServer
