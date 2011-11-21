public class Racer1 implements Runnable {
    int d = 42;

    public void run() {
	try {
	    Thread.sleep(10);		// (1)
	    d = 0;			// (2)
	} catch (InterruptedException e) {
	}
    }

    public static void main(String[] args) {
	Racer1 racer = new Racer1();
	Thread t = new Thread(racer);
	t.start();
	try {
	    Thread.sleep(9);		// (3)
	} catch (InterruptedException e) {
	}
	// Fix 1: test and use
	if (racer.d != 0) {		// (4a)
	   int c  = 420 / racer.d;	// (4b)
	   System.out.println(c);
	}
    }
}
