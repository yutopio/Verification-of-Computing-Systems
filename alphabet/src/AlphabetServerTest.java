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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class AlphabetServerTest {

	public static void main(String[] args) throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		Socket s = new Socket(addr, AlphabetServer.SERVER_PORT);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		int n = args.length;

		for (int i = 0; i < n; i++) {
			char sentChar = args[i].charAt(0);

			System.out.println("[AlphabetServerTest : main(String[])] send "
					+ sentChar);
			out.write(sentChar);
			System.out.println("[AlphabetServerTest : main(String[])] receive "
					+ (char) in.read());
		}
	}

}
