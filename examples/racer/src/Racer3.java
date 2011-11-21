public class Racer3 implements Runnable {
    int d = 42;

    public void run() {
	try {
	    Thread.sleep(10);		// (1)
	    synchronized (this) {
		d = 0;			// (2)
	    }
	} catch (InterruptedException e) {
	}
    }

    public static void main(String[] args) {
	Racer3 racer = new Racer3();
	Thread t = new Thread(racer);
	t.start();
	try {
	    Thread.sleep(9);		// (3)
	} catch (InterruptedException e) {
	}
	// Fix 2: test and use, synchronized
	synchronized (racer) {		// (4)
	    if (racer.d != 0) {
		int c  = 420 / racer.d;
		System.out.println(c);
	    }
	}
    }
}
