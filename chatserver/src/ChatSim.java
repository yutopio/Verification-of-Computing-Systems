/**
 * Sim/"driver" program for the Chat client/server example.
 */

public class ChatSim extends Thread {
    static final int nClients = 2;
    static final int nClientsServed = 2;
    //static final int portNumber = 4444 % java.net.Socket.NPORTS;

    public static final void main(String[]args) {

        Thread t[] = new Thread[nClients + 1];
          t[0] = new CentralizedProcess(0) {
            public void run() {
                new ChatServer(nClientsServed);
        }};
        t[0].start();

	// wait for server to be ready (only possible in centralized version)
	try {
	    synchronized (java.net.Socket.port) {
		while (!java.net.Socket.port.isOpen) {
		    java.net.Socket.port.wait();
		}
	    }
	} catch (InterruptedException e) {
	}

        for (int i = 1; i <= nClients; i++) {
            t[i] = new CentralizedProcess(i) {
                public void run() {
                    ChatClient.main(null);
                }
            };
            t[i].start();
        };
    }
}
