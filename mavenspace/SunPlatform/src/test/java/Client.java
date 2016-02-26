import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * author: Leo Zhang
 */
public class Client {

    public static void main(String[] args) throws Exception{
        String secretKey = "0b698e3e96383ee31305aae1647ea056f1133dee";
        String apiKey = "4706cf6178a795d46741d9c2743f5ef7cee212f5";

        String url = "http://localhost:8080/SunPlatform/sunConsume.do";

        String date = new Date().toString();
        System.out.println("date : " + date);

        String signature = getHmac(date, secretKey);

        System.out.println("signature : " + signature);

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);



        post.addHeader("ApiKey", apiKey);
        post.addHeader("X-Date", date);
        post.addHeader("Signature", signature);
        post.setHeader("Content-Type", "application/json");
        StringEntity input = new StringEntity("{\"UserId\":\"666\",\"UserName\":\"369\",\"OptionPayments\":{\"BALANCE\":7},\"MemberCard\":\"98656321\",\"Total\":\"1000\",\"Password\":\"397445\",\"TerminalId\":\"0007\",\"TerminalTradeLine\":\"065478\",\"TraderId\":\"3698441\",\"CreateTime\":\"2014-06-13 12:23\"}");
        input.setContentType("application/json");
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println("Result : "
                + result);

    }

    private static String getHmac(String salt, String secretKey){
        String generateHmacSHA256Signature = null;
        try {
            generateHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey);

        } catch (GeneralSecurityException e) {

            e.printStackTrace();
        }

        return generateHmacSHA256Signature;

    }

    private static String generateHmacSHA256Signature(String data, String key) throws GeneralSecurityException {
        byte[] hmacData =null;

        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            hmacData = mac.doFinal(data.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(hmacData), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }
}
