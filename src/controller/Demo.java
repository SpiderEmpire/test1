package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import dal.Dbconnect;
import CentralAuthority.PKG;
import algorithm.Decrypt;
import algorithm.Encrypt;

public class Demo {

	public static void main(String[] args) throws IOException {
		
		PKG pkg = new PKG();
		BigInteger Public_key = pkg.get_public_key("admin");
		Public_key = pkg.get_public_key("1006");
		BigInteger n = pkg.getn();

		Encrypt encrypt1 = new Encrypt("1006", "dipali", n,
				Public_key);
		byte[] cipher = encrypt1.encryption();

		File f = new File("C:\\SmartGrid\\admin\\AdminTest.txt");
		
		
		InputStream is = new FileInputStream(f);

		// Get the size of the file
		long length = f.length();
		if (length > Integer.MAX_VALUE) {
			throw new IOException("Could not completely read file "
					+ f.getName() + " as it is too long (" + length
					+ " bytes, max supported " + Integer.MAX_VALUE
					+ ")");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length
						- offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ f.getName());
		}

		// Close the input stream and return bytes
		is.close();
		// return bytes
		System.out.println("bytes" + bytes);
		
		
		PKG pkg1 = new PKG();
		BigInteger Private_key = pkg.get_private_key("admin");
		BigInteger n1 = pkg1.getn();
		
		Decrypt decryptMessage = new Decrypt("1006", n1,
				Private_key);

		String msg = decryptMessage.decryption(cipher);
		
		System.out.println("after "+msg);
	}
}
