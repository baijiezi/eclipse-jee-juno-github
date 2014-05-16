package utils.JUtils.chenbug.chineseren;

// Decompiled by DJ v3.2.2.71 Copyright 2003 Atanas Neshkov  Date: 2004-10-26 18:25:12   
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!   
// Decompiler options: packimports(3)   
   
import java.io.*;   
import java.util.zip.GZIPInputStream;   
import java.util.zip.GZIPOutputStream;   
import org.apache.commons.logging.Log;   
import org.apache.commons.logging.LogFactory;   
   
public class ZipUtils   
{   
   
    public ZipUtil()   
    {   
    }   
   
    public static byte[] zipText(String s)   
    {   
        byte abyte0[] = null;   
        try   
        {   
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();   
            GZIPOutputStream gzipoutputstream = new GZIPOutputStream(bytearrayoutputstream);   
            gzipoutputstream.write(s.getBytes());   
            gzipoutputstream.close();   
            abyte0 = bytearrayoutputstream.toByteArray();   
            bytearrayoutputstream.close();   
        }   
        catch(IOException ioexception)   
        {   
            _logger.error(ioexception.toString());   
        }   
        return abyte0;   
    }   
   
    public static String unzipText(byte abyte0[])   
    {   
        String s = null;   
        try   
        {   
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);   
            GZIPInputStream gzipinputstream = new GZIPInputStream(bytearrayinputstream);   
            byte abyte1[] = new byte[100];   
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();   
            int i;   
            while((i = gzipinputstream.read(abyte1, 0, 100)) != -1)   
                bytearrayoutputstream.write(abyte1, 0, i);   
            gzipinputstream.close();   
            s = bytearrayoutputstream.toString();   
        }   
        catch(IOException ioexception)   
        {   
            _logger.error(ioexception.toString());   
        }   
        return s;   
    }   
   
    public static void main(String args[])   
    {   
        try   
        {   
            StringBuffer stringbuffer = new StringBuffer();   
            for(int i = 0; i  100; i++)   
                stringbuffer.append("ÖÐ¹úÈË");   
            //System.out.println(stringbuffer.length());   
            _logger.info("The stringbuffer is: " + stringbuffer);   
            _logger.info(String.valueOf("The stringbuffer.length() is: " + stringbuffer.length()));   
   
            byte abyte0[] = zipText(stringbuffer.toString());   
            //System.out.println(abyte0.length);   
            _logger.info("The abyte0.toString() is: " + new String(abyte0,"ISO-8859-1"));   
            _logger.info("The abyte0.length is: " + String.valueOf(abyte0.length));   
   
            String s = unzipText(abyte0);   
            //System.out.println(s.length());   
            _logger.info("The upzipText String s is:" + s);   
            _logger.info("The upzipText String s.length() is:" + String.valueOf(s.length()));   
        }   
        catch(Exception exception)   
        {   
            exception.printStackTrace();   
        }   
        System.exit(0);   
    }   
   
    private static final Log _logger;   
   
    static   
    {   
        _logger = LogFactory.getLog(ZipUtil.class);   
    }   
}  



