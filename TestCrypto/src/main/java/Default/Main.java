package Default;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;

/**
 * Created by sun on 10/25/16.
 */
public class Main {
    public static void main(String args[]) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        String alias = "MRTD";
        String certPath = "/media/sun/20C1-A87E/java-library-test/TestCrypto/totalcertificate.p12";

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



        System.out.println("=======================Encrypt-Decrypt Large Data=================================");

        File file = new File("largeDataToEncrypt.txt");
        byte[] bFile = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();

        Main m = new Main();
        byte[] encryptedData =  m.encryptDataBytes(publicKey, bFile);

        System.out.println(Base64.encode(encryptedData) + "\n");

        byte[] decryptedData = m.decryptDataBytes(privateKey, encryptedData);

        System.out.println(new String(decryptedData) + "\n");

    }

    private byte[] encryptDataBytes(PublicKey publicKey, byte[] plainBytes) throws BadPaddingException, ShortBufferException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, KeyStoreException, InvalidKeyException {
        Cipher enLarge = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        enLarge.init(Cipher.ENCRYPT_MODE, publicKey);

        int blockSize = enLarge.getBlockSize();
        int outputSize = enLarge.getOutputSize(blockSize);
        if(blockSize == 0) {
            blockSize = outputSize -11;
        }
        int inLen = plainBytes.length;
        int fullBlockCount = inLen / blockSize;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int inOffset = 0;
        for (int i=0; i<fullBlockCount; i++) {
            outputStream.write(enLarge.doFinal(plainBytes,inOffset,blockSize));
            inOffset += blockSize;
        }
        if (inOffset < inLen) {
            outputStream.write(enLarge.doFinal(plainBytes,inOffset,inLen-inOffset) );
        }
        return outputStream.toByteArray();
    }

    private byte[] decryptDataBytes(PrivateKey privateKey, byte[] encryptedBytes) throws BadPaddingException, ShortBufferException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, KeyStoreException, InvalidKeyException {
        Cipher enLarge = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        enLarge.init(Cipher.DECRYPT_MODE, privateKey);

        int blockSize = enLarge.getBlockSize();
        int outputSize = enLarge.getOutputSize(blockSize);

        int inLen = encryptedBytes.length;
        int fullBlockCount = inLen / outputSize;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int inOffset = 0;
        for (int i=0; i<fullBlockCount; i++) {
            outputStream.write(enLarge.doFinal(encryptedBytes,inOffset,outputSize));
            inOffset += outputSize;
        }
        if (inOffset < inLen) {
            outputStream.write(enLarge.doFinal(encryptedBytes,inOffset,inLen-inOffset) );
        }
        return outputStream.toByteArray();
    }
}
