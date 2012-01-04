/* $Id: InputStream.java 713 2009-09-24 06:45:20Z cartho $ */

package env.java.io;

/* Stub class for InputStream (hard-coded one-line string). */

import java.io.IOException;

public class InputStream extends java.io.InputStream {
  int pos = 0;
  int line = 0;
  static final String[] hardCodedString =
    { "One...\n", "Two...\n" }; //, "Three...\n" };

  public InputStream() {
  }

  public int read() throws IOException {
    char ch;
    if (line == hardCodedString.length) {
      return -1;
    }
    ch = hardCodedString[line].charAt(pos++);
    if (pos == hardCodedString[line].length()) {
      line++;
      pos = 0;
    }
    return ch;
  }
}
