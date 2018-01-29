/* 
 * H1,H2 =hash function
 * n=size of plan text
 * mk=master key
 * lamda=security parameter
 * q=prime order
 */
package algorithm;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import CentralAuthority.PKG;
import dal.Dbconnect;
import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.ibe.BFCipher;
import uk.ac.ic.doc.jpair.pairing.Predefined;

public class Setup {

	public static Boolean setup(String userid, String serverid) {
		
		PKG pkg=new PKG();
		Boolean flag=false;		
		
		try {
			
			BigInteger uPublic_key = pkg.get_public_key(userid);
			BigInteger uPrivate_key = pkg.get_private_key(userid);
			//System.out.println(uPublic_key+"-"+uPrivate_key+"-"+userid);
			if(Dbconnect.saveKeys(uPublic_key, uPrivate_key, userid))
			{
				BigInteger sPublic_key = pkg.get_public_key(serverid);		
				BigInteger sPrivate_key = pkg.get_private_key(serverid);		
			
				//System.out.println(sPublic_key+"-"+sPrivate_key+"-"+serverid);
				if(Dbconnect.saveKeys(sPublic_key,sPrivate_key,serverid))
				{
					//System.out.println("keys saved");
					flag=true;
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	
		return flag;
	}

}
