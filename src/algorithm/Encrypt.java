package algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.apache.commons.codec.binary.Base64;

public class Encrypt {

	private static String Message;
	private String ID;
	static BigInteger n;
	static BigInteger Public_key;

	// java.net.URL url =
	// getClass().getResource("../Server/EncryptedMessage.txt");
	// File file = new File(url.getPath());

	public Encrypt(String ID, String Message, BigInteger n,
			BigInteger Public_key) {

		this.ID = ID;
		this.Message = Message;
		this.n = n;
		this.Public_key = Public_key;

	}

	// Encrypt message
	public byte[] encrypt() {
		return null; // Encrypting message

		/*
		 * byte[] message = Message.getBytes();
		 * 
		 * System.out.println(bytesToString((new
		 * BigInteger(message)).modPow(Public_key, n).toByteArray()));
		 * 
		 * 
		 * byte [] encryptedMessage = messageEncrypt(message);
		 * 
		 * try(
		 * 
		 * FileWriter fileWriter = new FileWriter(file,true); BufferedWriter
		 * bufferFileWriter = new BufferedWriter(fileWriter);
		 * 
		 * ) {
		 * 
		 * fileWriter.append(ID); // Sending Message to server
		 * fileWriter.append(" ");
		 * fileWriter.append(Integer.toString(encryptedMessage.length));
		 * fileWriter.append(" ");
		 * 
		 * for(byte e_message : encryptedMessage){
		 * fileWriter.append(Byte.toString(e_message)); fileWriter.append(" ");
		 * }
		 * 
		 * fileWriter.append("\n"); bufferFileWriter.close();
		 * fileWriter.close();
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 * 
		 * 
		 * return (new BigInteger(message)).modPow(Public_key, n).toByteArray();
		 */
	}

	public byte[] messageEncrypt(byte[] message) { // Actual message Encryption

		return (new BigInteger(message)).modPow(Public_key, n).toByteArray();

	}

	private static String bytesToString(byte[] encrypted) {

		String test = "";

		for (byte b : encrypted) {
			test += Byte.toString(b);
			// test +=" ";
		}

		return test;

	}

	public byte[] encryption() {
		byte[] message = Message.getBytes();
		byte[] encryptedMessage = messageEncrypt(message);

		return encryptedMessage;
	}
	
	public String msgencryption() {
		byte[] message = Message.getBytes();
		BigInteger encryptedMessage = messageEncryption(message);
		
		String orig=encryptedMessage.toString();
		byte[] base=Base64.encodeBase64(orig.getBytes());  

		String encoded = new String(base);
		return encoded;
	}
	

	public BigInteger messageEncryption(byte[] message) { // Actual message Encryption

	return (new BigInteger(message)).modPow(Public_key, n);

	}

}
