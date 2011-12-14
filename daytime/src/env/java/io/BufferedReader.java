/* $Id: BufferedReader.java 834 2010-11-24 07:28:51Z cartho $ */

package env.java.io;

/* Stub class for BufferedReader. */

import gov.nasa.jpf.jvm.Verify;
import java.io.IOException;
import java.io.Reader;
import java.lang.Thread;

public class BufferedReader {
  java.io.BufferedReader impl;

  public BufferedReader(Reader in) {
    impl = new java.io.BufferedReader(in);
  }

  public String readLine() throws IOException {
    // readLine blocking is undocumented, but the actual implementation
    // will block if there is no data available in the stream. 
    Thread.yield();
    if (Verify.getBoolean()) {
      throw new IOException("Simulated exception when reading a line.");
    }
    return impl.readLine();
  }
}
