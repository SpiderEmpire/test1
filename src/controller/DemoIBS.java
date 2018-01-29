package controller;

import it.unisa.dia.gas.crypto.jpbc.signature.ps06.engines.PS06Signer;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.generators.PS06ParametersGenerator;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.generators.PS06SecretKeyGenerator;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.generators.PS06SetupGenerator;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.PS06Parameters;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.PS06PublicKeyParameters;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.PS06SecretKeyGenerationParameters;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.PS06SecretKeyParameters;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.PS06SetupGenerationParameters;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.PS06SignParameters;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.PS06VerifyParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JOptionPane;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.SHA256Digest;

import services.User;
import algorithm.IBS;

public class DemoIBS {

	public static PS06Parameters createParameters(int nU, int nM) {
		// Generate Public PairingParameters
		return new PS06ParametersGenerator().init(
				PairingFactory.getPairingParameters("a.properties"), nU, nM)
				.generateParameters();
	}

	public static AsymmetricCipherKeyPair setup(PS06Parameters parameters) {
		PS06SetupGenerator setup = new PS06SetupGenerator();
		setup.init(new PS06SetupGenerationParameters(null, parameters));

		return setup.generateKeyPair();
	}

	public static CipherParameters extract(AsymmetricCipherKeyPair keyPair,
			String identity) {
		PS06SecretKeyGenerator extract = new PS06SecretKeyGenerator();
		extract.init(new PS06SecretKeyGenerationParameters(keyPair, identity));

		return extract.generateKey();
	}

	public static byte[] sign(String message, CipherParameters secretKey) {
		byte[] bytes = message.getBytes();

		PS06Signer signer = new PS06Signer(new SHA256Digest());
		signer.init(true, new PS06SignParameters(
				(PS06SecretKeyParameters) secretKey));
		signer.update(bytes, 0, bytes.length);

		byte[] signature = null;
		try {
			signature = signer.generateSignature();
		} catch (CryptoException e) {
			e.printStackTrace();
		}

		return signature;
	}

	public static boolean verify(CipherParameters publicKey, String message,
			String identity, byte[] signature) {
		byte[] bytes = message.getBytes();

		PS06Signer signer = new PS06Signer(new SHA256Digest());
		signer.init(false, new PS06VerifyParameters(
				(PS06PublicKeyParameters) publicKey, identity));
		signer.update(bytes, 0, bytes.length);

		return signer.verifySignature(signature);
	}

	public static void main(String[] args) {

		String message = "dipali";
		int userid = 28;

		// Setup -> (Public Key, Master Secret Key)
		AsymmetricCipherKeyPair keyPair = DemoIBS.setup(DemoIBS
				.createParameters(256, 256));

		// Extract -> Secret Key for Identity "01001101"
		CipherParameters secretKey = DemoIBS.extract(keyPair, "01001101");

		// Sign

		byte[] signature = DemoIBS.sign(message, secretKey);

		// verify with the same identity
		boolean flag = DemoIBS.verify(keyPair.getPublic(), message, "01001101",
				signature);

		// verify with another identity
		boolean flag1 = DemoIBS.verify(keyPair.getPublic(), message,
				"01001100", signature);
	}

}
