package com.sunkuet02.todoapplication.utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sun on 3/5/17.
 */
public class HashUtils {
    final static Logger logger = Logger.getLogger(HashUtils.class);

    public static String getMD5Hash(String original) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.info("No such Algorithm");
            return null;
        }
        m.reset();
        m.update(original.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);

        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return hashtext;
    }
}
