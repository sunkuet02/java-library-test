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
        byte[] fileData = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(fileData);
        fileInputStream.close();

        Main m = new Main();

        System.out.println("=========================Single Function check====================================");

        Cipher cipherLDE = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipherLDE.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedData = m.cryptDataBytes(cipherLDE, fileData,true);

        Cipher cipherLDD = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipherLDD.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedData = m.cryptDataBytes(cipherLDD, encryptedData, false);

        System.out.println(new String(decryptedData));

    }

    private byte[] cryptDataBytes(Cipher cipher, byte[] dataBytes, boolean encription)
            throws BadPaddingException, IllegalBlockSizeException, IOException {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        if (blockSize == 0) {
            blockSize = outputSize - 11;
        }

        int readingBlockSize = 0;
        if(encription) {
            readingBlockSize = blockSize;
        } else {
            readingBlockSize = outputSize;
        }

        int inLen = dataBytes.length;
        int fullBlockCount = inLen / readingBlockSize;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int inOffset = 0;
        for (int i=0; i<fullBlockCount; i++) {
            outputStream.write(cipher.doFinal(dataBytes,inOffset,readingBlockSize));
            inOffset += readingBlockSize;
        }
        if (inOffset < inLen) {
            outputStream.write(cipher.doFinal(dataBytes,inOffset,inLen-inOffset) );
        }
        return outputStream.toByteArray();
    }
}
