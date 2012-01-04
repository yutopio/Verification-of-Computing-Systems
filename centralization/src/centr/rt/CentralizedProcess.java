package centr.rt;

public class CentralizedProcess extends Thread {
    public int pid;

    public CentralizedProcess(int procId) {
	super();
	pid = procId;
    }
}
