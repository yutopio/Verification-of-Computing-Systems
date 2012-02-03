import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Worker implements Runnable {
    Socket sock;
    PrintWriter out;
    BufferedReader in;
    ChatServer chatServer;
    int n;

    public Worker(int n, Socket s, ChatServer cs) {
        this.n = n;
        chatServer = cs;
        sock = s;
        out = null;
        in = null;
    }

    public void run() {
        System.out.println("Thread running: " + Thread.currentThread());
	try {
            out = new PrintWriter(sock.getOutputStream(), true);
            assert(out != null);
            in = new BufferedReader(new
                                    InputStreamReader(sock.getInputStream()));
            String s = null;
            while ((s = in.readLine()) != null) {
                chatServer.sendAll("[" + n + "]" + s);
            }
            chatServer.remove(n);
            sock.close();
        }
        catch(IOException ioe) {
            System.out.println("Worker thread " + n + ": " + ioe);
            chatServer.remove(n);
        }
    }

    public void send(String s) {
        out.println(s);
    }
}

public class ChatServer {
    Worker workers[];

    public ChatServer(int maxServ) {
        int port = 4444;
        workers = new Worker[2];
        Socket sock;
        try {
            ServerSocket servsock = new ServerSocket(port);
            while (maxServ-- != 0) {
                sock = servsock.accept();
                int i;
                  synchronized(this) {
                    for (i = 0; i < workers.length; i++) {
                        if (workers[i] == null) {
                            workers[i] = new Worker(i, sock, this);
                            new Thread(workers[i]).start();
                              break;
                        }
                    } if (i == workers.length) {
                        System.out.println("Can't serve.");
                    }
                }
            }
        }
        catch(IOException ioe) {
            System.out.println("Server: " + ioe);
        }
        System.out.println("Server shutting down.");
    }

    public static void main(String args[]) throws IOException {
	if (args.length == 0) {
            new ChatServer(-1);
	} else {
	    new ChatServer(Integer.parseInt(args[0]));
        }
    }

    public synchronized void sendAll(String s) {
        int i;
        for (i = 0; i < workers.length; i++) {
            if (workers[i] != null)
                workers[i].send(s);
        }
    }

    public void remove(int n) {
        workers[n] = null;
        sendAll("Client " + n + " quit.");
    }
}
