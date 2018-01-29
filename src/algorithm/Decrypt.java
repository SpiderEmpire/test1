package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class Decrypt {
	
	static BigInteger n;
	static BigInteger private_key;
	String ID;
	
	java.net.URL url = getClass().getResource("EncryptedMessage.txt");
    File file = new File(url.getPath());
	
    
	public Decrypt( String ID,BigInteger n , BigInteger private_Key){
		
		this.ID = ID;
		this.private_key = private_Key;
		this.n = n;
		
	}
	
	public String decrypt() {							// First Reading Message then crypting
		
		byte [] message = new byte[0];
		int k;
		boolean flag = false;
		
		try {
			
			Scanner p = new Scanner(file);
			while(p.hasNext()){
				
					if(p.next().compareTo(ID) == 0){			// Message Found for this server
						
						k = p.nextInt();
						message  = new byte[k];
						
						for(int i=0;i<k;i++){
							
							message[i] = p.nextByte();
							
						}
						
						flag = true;
						System.out.println(new String(decryptMessage(message)) + "\n");
						
					}
					else{
						
						k = p.nextInt();
						
						for(int i=0;i<k;i++){
							
							p.nextByte();
							
						}
						
					}
					
			}
							
			p.close();
			if(flag)	return new String(decryptMessage(message)); 
			
		} 
		catch (FileNotFoundException e) {			
			
			e.printStackTrace();			
		}
		System.out.println(new String("There is no message for you !!!!!!!"));
		return new String("There is no message for you !!!!!!!");   // No Message Found for this server
		
        
    } 
	
	private static String bytesToString(byte[] encrypted) { 
		
        String test = ""; 
        
        for (byte b : encrypted) { 
            test += Byte.toString(b); 
        } 
        
        return test; 
        
    } 
	
	public byte[] decryptMessage(byte[] message) {			// Actual Message Decryption
		
        return (new BigInteger(message)).modPow(private_key, n).toByteArray();         
    }
	
	
	public String decryption(byte[] encryptedMessage)
	{		
		String test=new String(decryptMessage(encryptedMessage));
		return test;
	}
	
public BigInteger decryptionMessage(String message) {			// Actual Message Decryption
		
        return (new BigInteger(message)).modPow(private_key, n);         
    }
	
	
	public String msgdecryption(String encryptedMessage)
	{		
		byte[] dec=encryptedMessage.getBytes();			
		byte[] decoded1 = Base64.decodeBase64(dec);
		String ln=new String(decoded1);
		
		BigInteger pt=decryptionMessage(ln);
		String test = new String(pt.toByteArray());
					
		
		return test;
	}
     
}
