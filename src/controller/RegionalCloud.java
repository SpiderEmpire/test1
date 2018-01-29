package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import dal.Dbconnect;
import services.User;
import CentralAuthority.PKG;
import algorithm.Decrypt;

import algorithm.IBS;

public class RegionalCloud {

	static Boolean IBEDecryption(User cl, byte[] msgCipher) {
		Boolean flag = false;
		PKG pkg = new PKG();
		// Decrypt the message
		try {
			// String msg = IBE.Decryprtion(msgCipher);
			
			
			BigInteger n = pkg.getn();
			String key = Dbconnect.getPrivateKey(cl.getServerid());
			BigInteger private_key = new BigInteger(key);

			Decrypt decryptMessage = new Decrypt(cl.getServerid(), n,
					private_key);

			String msg = decryptMessage.decryption(msgCipher);
		
			BufferedWriter bw = null;
			FileWriter fw = null;

			File file = new File(cl.getFilepath());
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(msg);
			bw.close();
			fw.close();
			//System.out.println("Done decryption");

			if (AmazonStorage.uploadUsage(cl.getId(), cl.getFilenm(), file,	cl.getRegion())) {
				System.out.println("done uploading to cloud");
				if(dal.Dbconnect.SaveUsage(cl)){
					System.out.println("done uploading to db");
					flag = true;
				}
					
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	static Boolean IBSverify(AsymmetricCipherKeyPair keyPair, byte[] signature,
			User cl, String filecontent) {

		Boolean flag = false;
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			boolean f1 = IBS.verify(keyPair.getPublic(), filecontent,
					Integer.toString(cl.getId()), signature);
			if (f1 == true) {

				File file = new File(cl.getFilepath());
				// true = append file
				fw = new FileWriter(file.getAbsoluteFile(), true);
				bw = new BufferedWriter(fw);
				bw.write(filecontent);
				bw.close();
				fw.close();

				if (AmazonStorage.uploadUsage(cl.getId(), cl.getFilenm(), file,
						cl.getRegion())) {
					System.out.println("done uploading");

					// save to database
					dal.Dbconnect.SaveUsage(cl);
					flag = true;
				} else {
					System.out.println("failed to upload file");
				}
			} else {
				System.out.println("sign not verified");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
