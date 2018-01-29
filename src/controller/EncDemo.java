package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import algorithm.Decrypt;
import algorithm.Encrypt;
import CentralAuthority.PKG;
import dal.Dbconnect;

public class EncDemo {
	static PKG pkg = new PKG();

	public static void main(String[] args) throws IOException {

		String serverids[] = Dbconnect.getserverid("41");
		if (algorithm.Setup.setup("41", serverids[1])) {
			 System.out.println("setup done");
		}
		BigInteger Public_key = new BigInteger(
				Dbconnect.getPublicKey(serverids[1]));
		BigInteger n = pkg.getn();
		System.out.println("n"+n);
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		File file = new File("E:\\NewDemo.txt");
		File encoded=new File("E:\\Enc.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
	
		String line;
		while ((line = bufferedReader.readLine()) != null) {

			System.out.println(line);
			Encrypt encrypt1 = new Encrypt(serverids[1],line,n,Public_key); 				
			String cipher=encrypt1.msgencryption();	

			fw = new FileWriter(encoded,true);
			bw = new BufferedWriter(fw);
			bw.write(cipher);	
			//bw.write(ct.toString());
			bw.write("\n");
			bw.close();
			fw.close();

		}
		fileReader.close();
		
		
		String key = Dbconnect.getPrivateKey(serverids[1]);
		BigInteger private_key = new BigInteger(key);
		
		BufferedWriter bw1 = null;
		FileWriter fw1 = null;
		File file1 = new File("E:\\Enc.txt");
		
		FileReader fileReader1 = new FileReader(file1);
		BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
		BigInteger nn=new BigInteger("20700392650712484105694356561689220696120957347483281969047263548808850389466847239285307311939759875214841701495955830732014794746138712579026142053033857416058904920042637429885545058317377410625494977093504492620609409686843021008931793547064043769031687394450553013448219933165897606158683641836889637299880965206638053980061446326006219534354693145279544586649658937149064250759945594794052995790592236106926607725100843028359464654463305667112949702920619167760589345713641707601993461596256020190321095182513710027740239891471530864780885067818026425436490441762364055775521875629810530615902868630053121833559");
		String line1;
		while ((line1 = bufferedReader1.readLine()) != null) {

			
			Decrypt decryptMessage = new Decrypt(serverids[1], nn,
					private_key);

			String test = decryptMessage.msgdecryption(line1);

			

			fw1 = new FileWriter("E:\\Final.txt",true);
			bw1 = new BufferedWriter(fw1);
			bw1.write(test);	
			bw1.write("\n");

			bw1.close();
			fw1.close();

		}
		fileReader1.close();
		
		/*if(encoded.delete()){
			System.out.println(encoded.getName() + " is deleted!");
		}else{
			System.out.println("Delete operation is failed.");
		}*/
		
		

	}
}
