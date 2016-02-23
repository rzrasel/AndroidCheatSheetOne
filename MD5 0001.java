package rz.rasel.installinbackgroundsample;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by nextdot on 2/23/16.
 */
public class ClsMd5 {
    //|------------------------------------|
    private static final int RADIX = 10 + 26; // 10 digits + 26 letters
    //|------------------------------------|
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    public static String urlToName(String url) {
        //|------------------------------------|
        byte[] md5 = MD5(url.getBytes());
        BigInteger data = new BigInteger(md5).abs();
        //String suffix = url.substring(url.lastIndexOf("."));
        //return data.toString(RADIX) + (suffix.length() <= ".jpeg".length() ? suffix : "");
        return data.toString(RADIX);
        //|------------------------------------|
    }
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    static byte[] MD5(byte[] data) {
        //|------------------------------------|
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data);
            hash = digest.digest();
        } catch (NoSuchAlgorithmException exp) {
            exp.printStackTrace();
        }
        return hash;
        //|------------------------------------|
    }
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
}
