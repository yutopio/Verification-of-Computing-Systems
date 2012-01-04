/* $Id: OutputStreamWriter.java 713 2009-09-24 06:45:20Z cartho $ */

package env.java.io;

/* Stub class for OutputStreamWriter (writing to stdout). */

import java.io.IOException;

public class OutputStreamWriter {
  static int nLines = 0;

  public OutputStreamWriter(OutputStream target) {
  }

  public void write(String str) throws IOException {
    nLines++;
    System.out.print(str);
  }

  public void flush() throws IOException {
  }

  public void close() throws IOException {
    System.out.println(nLines + " lines written.");
  }
}
