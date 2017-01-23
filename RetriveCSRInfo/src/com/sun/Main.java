package com.sun;

import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String [] args) throws IOException, NoSuchAlgorithmException {
        String fileDir = "/media/sun/20C1-A87E/java-library-test/RetriveCSRInfo/CSR.csr";
        Path path = Paths.get(fileDir);
        byte [] fileData = Files.readAllBytes(path);

        PEMParser parser = new PEMParser(new InputStreamReader(new ByteArrayInputStream(fileData)));
        PKCS10CertificationRequest request = (PKCS10CertificationRequest) parser.readObject();

        System.out.println(request.getSubject().toString());
        System.out.println(request.getSignatureAlgorithm().getAlgorithm().toString());
    }
}