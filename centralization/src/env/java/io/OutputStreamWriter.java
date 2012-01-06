/* $Id: OutputStreamWriter.java 713 2009-09-24 06:45:20Z cartho $ */

package env.java.io;

/* Stub class for OutputStreamWriter (writing to stdout). */

import centr.rt.CentralizedProcess;
import java.io.IOException;

public class OutputStreamWriter {
  static int nLines[] = new int[2];

  static {
    int i;
    for (i = 0; i < 2; i++) {
      nLines[i] = 0;
    }
  }


  public OutputStreamWriter(OutputStream target) {
  }

  public void write(String str) throws IOException {
    int pid = ((CentralizedProcess)Thread.currentThread()).pid;
    nLines[pid]++;
    System.out.print(str);
  }

  public void flush() throws IOException {
  }

  public void close() throws IOException {
    int pid = ((CentralizedProcess)Thread.currentThread()).pid;
    System.out.println(nLines[pid] + " lines written.");
    assert nLines[pid] == 2;
  }
}
