package controller;

import org.apache.commons.codec.binary.Base64;

public class base64demo {
	public static void main(String[] args) {
		String orig = "dipali ,asdsfedfggr thtrtrhy rtjh"
				+ "r ykykykykykykykyk jtyjtjt "
				+ "yt y yukyukyukyukyuky"
				+ "ujut  ukuyk yukuy kyky"
				+ "t jtjtujiiuytiuytijyt";

		// encoding byte array into base 64
		byte[] encoded = Base64.encodeBase64(orig.getBytes());

		System.out.println("Original String: " + orig);
		System.out.println("Base64 Encoded String : " + new String(encoded));

		String asd="b3JpZ2luYWwgU3RyaW5nIGJlZm9yZSBiYXNlNjQgZW5jb2RpbmcgaW4gSmF2YQ==";
		
		
		
		byte[] dec=asd.getBytes();
		// decoding byte array into base64
		byte[] decoded = Base64.decodeBase64(dec);
		System.out.println("Base 64 Decoded  String : " + new String(decoded));

	}
}
