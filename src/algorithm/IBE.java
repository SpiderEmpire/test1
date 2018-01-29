package algorithm;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;


import uk.ac.ic.doc.jpair.ibe.*;
import uk.ac.ic.doc.jpair.ibe.key.*;

public class IBE implements Serializable{
	BFCtext first,second;
	public IBE(BFCtext first, BFCtext second) {
        this.first = first;
        this.second = second;
    }

    public BFCtext getFirst() {
        return first;
    }

    public BFCtext getSecond() {
        return second;
    }

	public static BFCtext Encryption(PublicKey pub,String message)
	{
		byte msgenc[] = new byte[50];				
		msgenc = message.getBytes();
		BFCtext msgCipher = BFCipher.encrypt(
				(BFUserPublicKey) KeyGeneration.getPub(), msgenc,
				new Random());
		return msgCipher;
	}	
	public static String Decryprtion(BFCtext msgCipher)
	{
		
		  byte msgdec[]=new byte[50];
		  System.out.println((BFUserPrivateKey) KeyGeneration.getPri());
	        msgdec=BFCipher.decrypt(msgCipher, (BFUserPrivateKey) KeyGeneration.getPri()) ;
	     
//	        for(int j = 0; j < msgdec.length; j++) {
//	            System.out.print((char)msgdec[j]);
//	        }
	       String message=new String(msgdec);
	        return message;
	}
	
	public static BFCtext ReEncryption(PublicKey spub,String message)
	{
		BFCtext Emsg = IBE.Encryption(spub, message); 		
		
		BFCtext REmsg = IBE.Encryption(spub, Emsg.toString()); 
	
		return Emsg;			
		
	}
	public static String REDecryprtion(BFCtext Remsg,PrivateKey spk) {	
		
		  byte msgdec[]=new byte[50];
		  byte msgdec11[]=new byte[50];
		  
		  System.out.println("cipher123"+Remsg);
		  
	       msgdec=BFCipher.decrypt(Remsg, (BFUserPrivateKey) spk) ;
	       
	       
	    /*   ByteArrayInputStream bais;

           ObjectInputStream ins;

           try {

           bais = new ByteArrayInputStream(msgdec);
           ins = new ObjectInputStream(bais);
            BFCtext mc =(BFCtext)ins.readObject();
	        
	        msgdec11=new byte[50];
	        msgdec11=BFCipher.decrypt(mc, (BFUserPrivateKey) KeyGeneration.getPri()) ;	        
      
           }
           catch(Exception e)
           {
        	   e.printStackTrace();
           }
           */
           return new String(msgdec);
	}
}
