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

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AlphabetServer {

	static int count = 0;

	static class WorkerThread extends Thread {

		Socket sock;

		WorkerThread(Socket s) {
			sock = s;
		}

		public void run() {
			InputStream is = null;
			OutputStream os = null;
			int in, out;
			boolean finished = false;
			assert (count > 0);

			try {
				is = sock.getInputStream();
				os = sock.getOutputStream();

				while (!finished) {
					System.out.println("[SERVER] threadID " + getId() + " reading ...");
					in = is.read();

					if (in == -1)
						finished = true;
					else {
						System.out.println("[SERVER] threadID " + getId() + " reads " + (char) in);
						out = in - '0' + 'A';

						System.out.println("[SERVER] threadID " + getId() + " writes " + (char) out);
						os.write(out);
					}
				}
			} catch (IOException e) {
			}

			try {
				assert (is != null && os != null && sock != null);
				is.close();
				os.close();
				sock.close();
			}
			catch(IOException e) {
			}

			System.out.println("[AlphabetServer.WorkerThread : run() threadID=" + getId() + "] end of stream");
		}
	}

	public static final int SERVER_PORT = 18586;

	public static void main(String[] args) throws IOException {
		Socket[] sock;
		ServerSocket receiver = new ServerSocket(SERVER_PORT);
		int maxConnection;

		maxConnection = Integer.parseInt(args[0]);

		if (maxConnection > 0) {
			sock = new Socket[maxConnection];

			System.out.println("[AlphabetServer : main(String[])] starts");
			for (int i = 0; i < maxConnection; i++) {
				sock[i] = receiver.accept();
				System.out.println("[AlphabetServer : main(String[])] accept a connection");

				new WorkerThread(sock[i]).start();
				count++;
			}
		} else {
			System.out.println("[AlphabetServer : main(String[])] starts");
			while (true) {
				Socket s = receiver.accept();
				System.out.println("[AlphabetServer : main(String[])] accept a connection");

				new WorkerThread(s).start();
			}
		}

		// for(Socket s : sock)
		// new WorkerThread(s).start();
	}

}
