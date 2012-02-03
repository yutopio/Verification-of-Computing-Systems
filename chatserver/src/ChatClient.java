/* $Id: ChatClient.java 723 2009-09-24 07:48:58Z cartho $ */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChatClient {
    int id;
    static int currID = 0;

    public final static void main(String args[]) {
        new ChatClient().exec();
    }

    public ChatClient() {
        synchronized(getClass()) {
            id = currID++;
        }
    }

    public void exec() {
        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 4444);
            socket.connect(addr);
            System.out.println("Client " + id + " connected.");
            InputStreamReader istr =
                new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(istr);
            OutputStreamWriter out =
                new OutputStreamWriter(socket.getOutputStream());
            out.write(id + ": Hello, world!\n");
            out.flush();
            for (int i = 0; i < 1; i++) {
                System.out.println(id + ": Received " + in.readLine());
            }
            out.close();
        } catch(IOException e) {
            System.err.println("Client " + id + ": " + e);
        }
    }
}
