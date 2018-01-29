package services;

import java.io.IOException;
import java.io.Serializable;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.util.Random;
import dal.Dbconnect;
import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.ibe.BFCipher;
import uk.ac.ic.doc.jpair.ibe.key.BFMasterPublicKey;
import uk.ac.ic.doc.jpair.ibe.key.BFUserPrivateKey;
import uk.ac.ic.doc.jpair.pairing.Point;
import uk.ac.ic.doc.jpair.pairing.Predefined;

public class DemoKeys implements Serializable{

	static Pairing e = Predefined.nssTate();
	private static final long serialVersionUID = 1L;
	String name;
	java.security.KeyPair userKey ;
	
	public java.security.KeyPair getUserKey() {
		return userKey;
	}
	public void setUserKey(java.security.KeyPair userKey) {
		this.userKey = userKey;
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		
		java.security.KeyPair masterKey = BFCipher.setup(e, new Random());		
	//	uPub = userKey.getPublic(); // to use in encrypt()
	
			
		/*DemoKeys dm=new DemoKeys();		
		//dm.setUserKey(BFCipher.extract(masterKey, "23",	new Random()));		
		dm.setName("Amey");		
		Dbconnect.savepk(dm);*/
		
		/*	
		
		DemoKeys dm1=Dbconnect.getpk(30);
		System.out.println("dm"+dm1);
		//PrivateKey pk1= dm1.getPk();
		System.out.println("name : "+ dm1.getName());
		System.out.println("key after retrieving ");
		System.out.println("done123");
		
		*/
		
		
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}
