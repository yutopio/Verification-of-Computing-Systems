/* $Id: InetSocketAddress.java 767 2009-10-14 06:46:59Z cartho $ */

package java.net;

public class InetSocketAddress extends SocketAddress {
    private InetAddress inet;

    private int port;

    public InetSocketAddress(InetAddress inet, int port) {
	this.inet = inet;
	this.port = port;
    }

    public InetSocketAddress(String hostname, int p) {
	port = p;
	// discard hostname
    }

    public int getPort() {
	return port;
    }
}
