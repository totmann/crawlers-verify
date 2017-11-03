package com.xiaoniu66.util;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class HMACSHA1 {
    private static final String MAC_NAME = "HmacSHA1";    
    private static final String ENCODING = "UTF-8";   
      
    /** 
     * 生成签名数据 
     *  
     * @param data 待加密的数据 
     * @param key  加密使用的key 
     * @return 生成哈希字符串  
     * @throws InvalidKeyException 
     * @throws NoSuchAlgorithmException 
     */  
    public static String getSignature(byte[] data, byte[] key)  throws InvalidKeyException, NoSuchAlgorithmException {  
        SecretKeySpec signingKey = new SecretKeySpec(key, MAC_NAME);  
        Mac mac = Mac.getInstance(MAC_NAME);  
        mac.init(signingKey);  
        byte[] rawHmac = mac.doFinal(data);  
        return new String(new Hex().encode(rawHmac));  
    }  
    
    public static String getSignature(String data, String key) {
        try {
            return getSignature(data.getBytes(ENCODING), key.getBytes(ENCODING));
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getURLString(TreeMap<String, String> map) {
        boolean isFisrt = true;
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry: map.entrySet()) {
            if (isFisrt == false) {
                sb.append("&");
            } else {
                isFisrt = false;
            }
            
            sb.append(entry.getKey()); 
            sb.append("=");
            sb.append(entry.getValue());
        }
        
        return sb.toString();
    }
}
