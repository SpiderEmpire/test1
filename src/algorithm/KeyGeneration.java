package algorithm;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.ibe.BFCipher;
import uk.ac.ic.doc.jpair.pairing.Predefined;
import dal.Dbconnect;

public class KeyGeneration {
	static PublicKey uPub;
	static PrivateKey uPri;
	static Pairing e = Predefined.nssTate();
	static java.security.KeyPair masterKey = BFCipher.setup(e, new Random());


	public static Boolean setup(String userid) throws IOException {
		
		//String serverid = Dbconnect.getserverid(userid);
		java.security.KeyPair userKey = BFCipher.extract(masterKey, userid,
				new Random());
		System.out.println("user key" + userKey);

		uPub = userKey.getPublic(); // to use in encrypt()
		uPri = userKey.getPrivate(); // to use in decrypt()
		return null;

		/*Boolean flag = Dbconnect.saveKeys(uPub, uPri, userid);
		if (flag) {
			return true;
		} else{		
		return flag;}*/

	}

	public static PrivateKey getPri() {
		return uPri;
	}

	public static PublicKey getPub() {
		return uPub;
	}

	public static Boolean ServerSetup(String userid) throws IOException {
		
		Boolean flag=false;
		System.out.println("Master key " + masterKey);
		if(Dbconnect.saveMK(masterKey))
		{
			String[] serverdetails = Dbconnect.getserverid(userid);
			java.security.KeyPair userKey = BFCipher.extract(masterKey, serverdetails[1],
					new Random());
			System.out.println("server key" + userKey);

			uPub = userKey.getPublic(); // to use in encrypt()
			uPri = userKey.getPrivate(); // to use in decrypt()
			/*if(Dbconnect.saveKeys(uPub, uPri, serverdetails[0]))
			{
				flag=true;
			}*/
		}
		return flag;

	}
	public static CipherParameters getIBSKey(int userid, PublicKey spub)
	{
		CipherParameters secretKey = null;
		int masterkey=256;
		AsymmetricCipherKeyPair keyPair = IBS.setup(IBS
				.createParameters(userid, masterkey));
		secretKey=IBS.extract(keyPair, Integer.toString(userid));
		
		return secretKey;
	}

	public static PrivateKey getUserPrivateKey(int uid) {
		// TODO Auto-generated method stub
		
		
		java.security.KeyPair userKey = BFCipher.extract(masterKey, Integer.toString(uid),
				new Random());
		System.out.println("user key" + userKey);	
		uPri = userKey.getPrivate();
		return uPri;
	}

}
