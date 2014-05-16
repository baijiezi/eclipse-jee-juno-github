package utils.JUtils.other;
   
import java.io.*;   
import java.security.*;   
import javax.crypto.*;   
import javax.crypto.spec.SecretKeySpec;   
   
/**  
* CryptTool ��װ��һЩ���ܹ��߷���, ���� 3DES, MD5, BASE64��.  
*  
* @author bonjovi  
* @version 1.0  
* 2004-05-12  
*/   
public class CryptUtils {   
 /**  
  * ����3DES��Կ.  
  *  
  * @param key_byte seed key  
  * @throws Exception  
  * @return javax.crypto.SecretKey Generated DES key  
  */   
 public static javax.crypto.SecretKey genDESKey(byte[] key_byte)   
  throws Exception {   
  //    javax.crypto.spec.DESKeySpec deskeyspec = new javax.crypto.spec.DESKeySpec(   
  //        key_byte);   
  //    javax.crypto.SecretKeyFactory skf = javax.crypto.SecretKeyFactory.   
  //        getInstance("DES", "SunJCE");   
  //    return (javax.crypto.SecretKey) skf.generateSecret(deskeyspec);   
  //KeyGenerator kg = KeyGenerator.getInstance("DESede");   
  SecretKey k = null;   
  k = new SecretKeySpec(key_byte, "DESede");   
  return k;   
 }   
   
 /**  
  * 3DES ����(byte[]).  
  *  
  * @param key SecretKey  
  * @param crypt byte[]  
  * @throws Exception  
  * @return byte[]  
  */   
 public static byte[] desDecrypt(javax.crypto.SecretKey key, byte[] crypt)   
  throws Exception {   
  javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");   
  cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);   
  return cipher.doFinal(crypt);   
 }   
   
 /**  
  * 3DES ����(String).  
  *  
  * @param key SecretKey  
  * @param crypt byte[]  
  * @throws Exception  
  * @return byte[]  
  */   
 public static String desDecrypt(javax.crypto.SecretKey key, String crypt)   
  throws Exception {   
  return new String(desDecrypt(key, crypt.getBytes()));   
 }   
   
 /**  
  * 3DES����(byte[]).  
  *  
  * @param key SecretKey  
  * @param src byte[]  
  * @throws Exception  
  * @return byte[]  
  */   
 public static byte[] desEncrypt(javax.crypto.SecretKey key, byte[] src)   
  throws Exception {   
  javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");   
  cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);   
  return cipher.doFinal(src);   
 }   
   
 /**  
  * 3DES����(String).  
  *  
  * @param key SecretKey  
  * @param src byte[]  
  * @throws Exception  
  * @return byte[]  
  */   
 public static String desEncrypt(javax.crypto.SecretKey key, String src)   
  throws Exception {   
  return new String(desEncrypt(key, src.getBytes()));   
 }   
   
 /**  
  * MD5 ժҪ����(byte[]).  
  *  
  * @param src byte[]  
  * @throws Exception  
  * @return byte[] 16 bit digest  
  */   
 public static byte[] md5Digest(byte[] src) throws Exception {   
  java.security.MessageDigest alg =   
   java.security.MessageDigest.getInstance("MD5");   
  // MD5 is 16 bit message digest   
   
  return alg.digest(src);   
 }   
   
 /**  
  * MD5 ժҪ����(String).  
  *  
  * @param src String  
  * @throws Exception  
  * @return String  
  */   
 public static String md5Digest(String src) throws Exception {   
  return new String(md5Digest(src.getBytes()));   
 }   
   
 /**  
  * BASE64 ����.  
  *  
  * @param src String inputed string  
  * @return String returned string  
  */   
 public static String base64Encode(String src) {   
  sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();   
   
  return encoder.encode(src.getBytes());   
 }   
   
 /**  
  * BASE64 ����(byte[]).  
  *  
  * @param src byte[] inputed string  
  * @return String returned string  
  */   
 public static String base64Encode(byte[] src) {   
  sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();   
   
  return encoder.encode(src);   
 }   
   
 /**  
  * BASE64 ����.  
  *  
  * @param src String inputed string  
  * @return String returned string  
  */   
 public static String base64Decode(String src) {   
  sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();   
   
  try {   
   return new String(decoder.decodeBuffer(src));   
  }   
  catch (Exception ex) {   
   return null;   
  }   
   
 }   
   
 /**  
  * BASE64 ����(to byte[]).  
  *  
  * @param src String inputed string  
  * @return String returned string  
  */   
 public static byte[] base64DecodeToBytes(String src) {   
  sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();   
   
  try {   
   return decoder.decodeBuffer(src);   
  }   
  catch (Exception ex) {   
   return null;   
  }   
   
 }   
   
 /**  
  * �Ը����ַ����� URL ����.  
  *  
  * @param src String  
  * @return String  
  */   
 public static String urlEncode(String src) {   
  try {   
   src = java.net.URLEncoder.encode(src, "GB2312");   
   
   return src;   
  }   
  catch (Exception ex) {   
   ex.printStackTrace();   
  }   
   
  return src;   
 }   
   
 /**  
  * �Ը����ַ����� URL ����  
  * @param value ����ǰ���ַ���  
  * @return �������ַ���  
  */   
 public String urlDecode(String value) {   
  try {   
   return java.net.URLDecoder.decode(value, "GB2312");   
  }   
  catch (Exception ex) {   
   ex.printStackTrace();   
  }   
   
  return value;   
 }   
   
 /* Test crypt */   
 public static void main(String[] args) {   
  byte src_byte[] = "1234567812345678".getBytes();   
  System.out.println(src_byte.length);   
  byte key_byte[] = "123456781234567812345678".getBytes();   
  // 3DES 24 bytes key   
   
  try {   
   // ����DES��Կ   
   javax.crypto.SecretKey deskey;   
   //����DES��Կ   
   //      javax.crypto.KeyGenerator key = javax.crypto.KeyGenerator.getInstance(   
   //          "DES");   
   //      key.init(56);   
   //      deskey = key.generateKey();   
   
   deskey = genDESKey(key_byte);   
   System.out.println("Generator DES KEY OK");   
   
   // DES�ӽ���   
   byte[] encrypt, decrypt;   
   //����   
   encrypt = desEncrypt(deskey, src_byte);   
   System.out.println("encrypt=" + new String(encrypt));   
   //����   
   decrypt = desDecrypt(deskey, encrypt);   
   System.out.println("decrypt=" + new String(decrypt));   
   
   //      String s = "12345678";   
   //      //����   
   //      s = desEncrypt(deskey, s);   
   //      System.out.println("encrypt=" + s);   
   //      //����   
   //      s = desDecrypt(deskey, s);   
   //      System.out.println("decrypt=" + s);   
   
  }   
  catch (Exception ex) {   
   ex.printStackTrace();   
  }   
   
  System.out.println("BASE64 Test:" + base64Decode(base64Encode("1234")));   
 }   
}  


