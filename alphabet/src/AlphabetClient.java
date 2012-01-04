//
// Copyright (C) 2006 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
//
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
//
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class AlphabetClient {

	public final static String MSG = "0123456789:;<=>?@ABCDEFFGHI";

	private static byte[] getByte(String a) {
		int n = a.length();
		byte[] m = new byte[n];

		for (int i = 0; i < n; i++) {
			m[i] = (byte) a.charAt(i);
		}

		return m;
	}

	public static void main(String[] args) throws IOException {
		InetAddress addr = InetAddress.getLocalHost();
		Socket s[];
		Thread[] p, c;
		int num_threads, num_messages;
		int msg_start = 0;

		num_threads = Integer.parseInt(args[0]);
		num_messages = Integer.parseInt(args[1]);
		p = new Thread[num_threads];
		c = new Thread[num_threads];
		s = new Socket[num_threads];

		for (int i = 0; i < num_threads; i++) {
			s[i] = new Socket(addr, AlphabetServer.SERVER_PORT);
			p[i] = new Producer(s[i].getOutputStream(),
				getByte(MSG.substring(msg_start, msg_start + num_messages)));
			c[i] = new Consumer(s[i].getInputStream(), num_messages);
			msg_start += num_messages;
		}

		for (int i = 0; i < num_threads; i++) {
			p[i].start();
			c[i].start();
		}
	}

}
