package main.java.com.mydao.utils;


public class ElectronicSignatureUtil {

    /**
     * 获取签名
     * @param ak
     * @param nonce
     * @param sk
     * @return
     */
    public static String getSign(String ak,String nonce,String sk){
        return ak+nonce+Md5Util.MD5Encode(nonce+sk);
    }
}
