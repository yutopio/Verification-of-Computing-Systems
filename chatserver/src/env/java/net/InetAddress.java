package java.net;

public class InetAddress {
    private byte[] address;

    static final InetAddress LOCALHOST =
	new InetAddress(new byte[] { 127, 0, 0, 1 });

    private InetAddress(byte[] address) {
        this.address = address;
    }

    public static InetAddress getLocalHost() throws UnknownHostException {
	return LOCALHOST;
    }
}
