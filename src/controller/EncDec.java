package controller;

import java.math.BigInteger;
import java.util.Random;

import CentralAuthority.PKG;
import dal.Dbconnect;

public class EncDec {

	static PKG pkg = new PKG();

	public static void main(String[] args) {
		
		Random r=new Random();
		//System.out.println("r"+r);
		String message = "hello";
		//String message = "Automated face annotation aims to automatically detect human faces "
		//		+ "from a photo and further name the faces with the corresponding human names."
		//		+ " Most of the images are obtained from World Wide Web (WWW), ";
		System.out.println(message);
		BigInteger b = new BigInteger(message.getBytes());		

		String m1 = b.toString();		

		String nn = "448378203247";
		BigInteger aa = new BigInteger(nn);
		String v = new String(aa.toByteArray());
		 System.out.println("v"+v);
		
		////////////////////////////////////////***************

		String serverids[] = Dbconnect.getserverid("41");
		if (algorithm.Setup.setup("41", serverids[1])) {
			// System.out.println("setup done");
		}
		BigInteger Public_key = new BigInteger(
				Dbconnect.getPublicKey(serverids[1]));
		 BigInteger n = pkg.getn();
		// BigInteger n=new BigInteger("113256854565645645645646546847");

		BigInteger ct = (new BigInteger(message.getBytes())).modPow(Public_key,
				n);
		System.out.println("BIGINTEGER" + ct.toString());
		
	

		String key = Dbconnect.getPrivateKey(serverids[1]);
		BigInteger private_key = new BigInteger(key);

		// String nn1="448378203247";
		BigInteger pt = ct.modPow(private_key, n);

		String test = new String(pt.toByteArray());
		System.out.println("test" + test);
	}
}
