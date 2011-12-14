/* $Id: OutputStream.java 276 2006-01-17 03:32:37Z cartho $ */

package env.java.io;

/* Stub class for OutputStream (just a /dev/null). */

import gov.nasa.jpf.jvm.Verify;
import java.io.IOException;

public class OutputStream extends java.io.OutputStream {
  public OutputStream() {
  }

  public void write(int b) throws IOException {
    if (Verify.getBoolean()) {
      throw new IOException("There was an error reading a byte from stream.");
    }
    // success
  }

  public void write(byte[] b) throws IOException {
    if (Verify.getBoolean()) {
      throw new IOException("There was an error reading a byte from stream.");
    }
    // success
  }
}
