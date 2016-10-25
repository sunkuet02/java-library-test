package Default;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by sun on 10/25/16.
 */
public class Main {
    public static void main(String args[]) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String alias = "MRTD";
        String certPath = "/media/sun/A4FE1C5EFE1C2AD6/EJBCA/TestCrypto/totalcertificate.p12";

        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        keyStore.load(new FileInputStream(certPath),"tigerit".toCharArray());

        PublicKey publicKey = keyStore.getCertificate(alias).getPublicKey();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, "tigerit".toCharArray());

        System.out.println("============================Public Key Start================================");
        System.out.println(Base64.encode(publicKey.getEncoded()));
        System.out.println("============================Public Key End==================================");

        System.out.println("============================Private Key Start===============================");
        System.out.println(Base64.encode(publicKey.getEncoded()));
        System.out.println("==============================Private Key End===============================");

        String message = "This is a message";

        System.out.println("===================Message Encription=====================");
        Cipher encrypt=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = encrypt.doFinal(message.getBytes("UTF-8"));
        System.out.println(new String(encryptedMessage));


        System.out.println("===================Message Encription=====================");
        Cipher decrypt=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decrypt.init(Cipher.DECRYPT_MODE, privateKey);
        String decryptedMessage = new String(decrypt.doFinal(encryptedMessage), "UTF-8");
        System.out.println("Message : " + decryptedMessage);
    }
}
