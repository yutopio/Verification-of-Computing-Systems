/* (C) JNuke project */

/* $Id: OutputStreamWriter.java 767 2009-10-14 06:46:59Z cartho $ */

package java.io;

public class OutputStreamWriter extends Writer {

  private OutputStream out;

  public OutputStreamWriter(OutputStream out) {
    super(out);
    this.out = out;
  }

  public OutputStreamWriter(OutputStream in, String charsetName) {
    this(in);
  }
  
  private void convertBuf (char[] from, byte[] to, int startFrom, int len) {
    /* TODO: cover case where sizeof (byte) > sizeof (char) */
    int i;
    for (i = 0; i < len; i++) {
      to[i] = (byte)from[i + startFrom];
    }
  }
  
  public void write(int c) throws IOException {
    out.write(c);
  }

  public void write(char cbuf[], int off, int len) throws IOException {
    byte[] b = new byte[len];
    convertBuf(cbuf, b, off, len);
    out.write(b, 0, len);
  }

  public void flush() throws IOException {
    out.flush();
  }

  void flushBuffer() throws IOException {
    flush();
  }

  public void close() throws IOException {
    synchronized (lock) {
      if (out == null)
	return;
      out.close();
      out = null;
    }
  }
}
