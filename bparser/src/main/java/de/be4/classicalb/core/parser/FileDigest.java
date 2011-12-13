package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class FileDigest {

	public static String sha(final File f) throws NoSuchAlgorithmException,
			IOException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		byte[] buf = new byte[8192];
		InputStream in = new FileInputStream(f);
		for (int n = 0; (n = in.read(buf)) > -1;) {
			md.update(buf, 0, n);
		}
		StringBuffer sb = new StringBuffer("");
		byte[] digest = md.digest();
		for (int i = 0; i < digest.length; i++) {
			sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

}