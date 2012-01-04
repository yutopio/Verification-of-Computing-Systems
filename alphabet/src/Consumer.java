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
import java.io.InputStream;

public class Consumer extends Thread {

	private InputStream is;

	private int maxRead;

	public Consumer(InputStream stream) {
		this(stream, Integer.MAX_VALUE);
	}

	public Consumer(InputStream stream, int maxRead) {
		is = stream;
		this.maxRead = maxRead;
	}

	public void run() {
		int b;

		for (int i = 0; i < maxRead; i++) {
			try {
				System.out.println("[Consumer : run()] trying to read");
				b = is.read();

				if (b < 0)
					break;
//				System.out.println("[Consumer : run()] read " + (char) b);
			} catch (IOException e) {
				System.out.println("[Consumer : run()] IOException occurs");
			}
		}

		System.out.println("[Consumer : run()] terminates");
	}
}
