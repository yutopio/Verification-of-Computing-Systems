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

	// EXERCISE: add the same code for the echo client
	t2 = new CentralizedProcess(...) {
	    public void run() {
		...
	    }
	};
	t2.start();
    }
}
