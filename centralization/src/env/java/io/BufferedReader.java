/* $Id: BufferedReader.java 752 2009-10-13 07:45:02Z cartho $ */

package env.java.io;

/* Stub class for BufferedReader. */

import java.io.IOException;
import java.io.Reader;

public class BufferedReader {
  Reader in;

  public BufferedReader(Reader input) {
    in = input;
  }

  public String readLine() throws IOException {
    StringBuilder buffer = new StringBuilder();

    for(int c = in.read(); c != -1 && c != '\n';c = in.read()) {
      buffer.append((char) c);
    }

    return (buffer.length() == 0) ? null : buffer.toString();
  }
}
