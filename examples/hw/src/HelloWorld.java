public class HelloWorld extends Thread {
    StringBuffer buf;
    String data;

    HelloWorld(StringBuffer buf, String data) {
	this.buf = buf;
	this.data = data;
    }

    public void run() {
	buf.append(data);
    }

    public static final void main(String[] args) {
	StringBuffer buf = new StringBuffer();
	HelloWorld hw1 = new HelloWorld(buf, "Hello, ");
	HelloWorld hw2 = new HelloWorld(buf, "World!");
	hw1.start();
	hw2.start();
	try {
	    hw1.join();
	    hw2.join();
	} catch (InterruptedException e) {
	}
	System.out.println(buf.toString());
    }
}
