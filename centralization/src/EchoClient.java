/* $Id: EchoClient.java 713 2009-09-24 06:45:20Z cartho $ */

import env.java.io.BufferedReader;
import java.io.IOException;
import env.java.io.InputStream;
import env.java.io.InputStreamReader;
import env.java.io.OutputStreamWriter;
import env.java.net.InetSocketAddress;
import env.java.net.Socket;

public class EchoClient {
    public final static void main(String args[]) {
	String line;
	int n = 0;
	try {
	    Socket socket = new Socket();
	    InetSocketAddress addr =
		new InetSocketAddress("localhost", 1024);
	    socket.connect (addr);
	    InputStreamReader istr =
		new InputStreamReader(socket.getInputStream());
	    BufferedReader in = new BufferedReader (istr);
	    OutputStreamWriter out =
		new OutputStreamWriter(socket.getOutputStream());
	    out.write ("One...\n");
	    out.write ("Two...\n");
	    //out.write ("Three...\n");
	    out.flush();
	    while ((n++ < 2) && (line = in.readLine()) != null) {
		System.out.println ("Received " + line);
	    }
	    out.close();
	} catch (IOException e) {
	}
    }
}
