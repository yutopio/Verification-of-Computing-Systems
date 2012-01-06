/**
 * Sim/"driver" program for the Echo client/server example.
 */

import centr.rt.CentralizedProcess;

public class EchoSim extends Thread {
    public static final void main(String[] args) {
	Thread t1, t2;

	t1 = new CentralizedProcess(0) {
	    public void run() {
		EchoServer.main(null);
	    }
	};
	t1.start();

	t2 = new CentralizedProcess(1) {
	    public void run() {
		EchoClient.main(null);
	    }
	};
	t2.start();
    }
}
