package algorithm;

import it.unisa.dia.gas.crypto.jpbc.signature.ps06.engines.PS06Signer;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.generators.*;
import it.unisa.dia.gas.crypto.jpbc.signature.ps06.params.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.digests.SHA256Digest;

public class IBS {

    public IBS() {
    }

    public static PS06Parameters createParameters(int nU, int nM) {
        // Generate Public PairingParameters
        return new PS06ParametersGenerator().init(
                PairingFactory.getPairingParameters("a.properties"),
                nU, nM).generateParameters();
    }

    public static AsymmetricCipherKeyPair setup(PS06Parameters parameters) {
        PS06SetupGenerator setup = new PS06SetupGenerator();
        setup.init(new PS06SetupGenerationParameters(null, parameters));

        return setup.generateKeyPair();
    }


    public static CipherParameters extract(AsymmetricCipherKeyPair keyPair, String identity) {
        PS06SecretKeyGenerator extract = new PS06SecretKeyGenerator();
        extract.init(new PS06SecretKeyGenerationParameters(keyPair, identity));

        return extract.generateKey();
    }

    public static byte[] sign(String message, CipherParameters secretKey) {
        byte[] bytes = message.getBytes();

        PS06Signer signer = new PS06Signer(new SHA256Digest());
        signer.init(true, new PS06SignParameters((PS06SecretKeyParameters) secretKey));
        signer.update(bytes, 0, bytes.length);

        byte[] signature = null;
        try {
            signature = signer.generateSignature();
        } catch (CryptoException e) {
          e.printStackTrace();
        }

        return signature;
    }

    public static boolean verify(CipherParameters publicKey, String message, String identity, byte[] signature) {
        byte[] bytes = message.getBytes();

        PS06Signer signer = new PS06Signer(new SHA256Digest());
        signer.init(false, new PS06VerifyParameters((PS06PublicKeyParameters) publicKey, identity));
        signer.update(bytes, 0, bytes.length);

        return signer.verifySignature(signature);
    }  

}
