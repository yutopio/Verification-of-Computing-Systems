public class Racer implements Runnable {
    int d = 42;

    public void run() {
	try {
	    Thread.sleep(10);		// (1)
	    d = 0;			// (2)
	} catch (InterruptedException e) {
	}
    }

    public static void main(String[] args) {
	Racer racer = new Racer();
	Thread t = new Thread(racer);
	t.start();
	try {
	    Thread.sleep(9);		// (3)
	} catch (InterruptedException e) {
	}
	int c  = 420 / racer.d;		// (4)
	System.out.println(c);
    }
}
