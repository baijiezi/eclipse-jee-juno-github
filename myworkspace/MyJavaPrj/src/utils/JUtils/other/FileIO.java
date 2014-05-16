package utils.JUtils.other;

import java.io.*;   
   
public class FileIO {   
private FileIO() { }   
public static void copyFile(String inName, String outName)   
throws FileNotFoundException, IOException {   
BufferedInputStream is =    
new BufferedInputStream(new FileInputStream(inName));   
BufferedOutputStream os =    
new BufferedOutputStream(new FileOutputStream(outName));   
copyFile(is, os, true);   
}   
   
/** Copy a file from an opened InputStream to opened OutputStream */   
public static void copyFile(InputStream is, OutputStream os, boolean close)    
throws IOException {   
int b; while ((b = is.read()) != -1) {   
os.write(b);   
}   
is.close();   
if (close)   
os.close();   
}   
   
   
public static void copyFile(Reader is, Writer os, boolean close)    
throws IOException {   
int b; while ((b = is.read()) != -1) {   
os.write(b);   
}   
is.close();   
if (close)   
os.close();   
}   
   
   
public static void copyFile(String inName, PrintWriter pw, boolean close)    
throws FileNotFoundException, IOException {   
BufferedReader is = new BufferedReader(new FileReader(inName));   
copyFile(is, pw, close);   
}   
   
   
public static String readLine(String inName)   
throws FileNotFoundException, IOException {   
BufferedReader is = new BufferedReader(new FileReader(inName));   
String line = null;   
line = is.readLine();   
is.close();   
return line;   
}   
   
protected static final int BLKSIZ = 8192;   
   
   
public void copyFileBuffered(String inName, String outName) throws   
FileNotFoundException, IOException {   
InputStream is = new FileInputStream(inName);   
OutputStream os = new FileOutputStream(outName);   
int count = 0;    
byte b[] = new byte[BLKSIZ];    
while ((count = is.read(b)) != -1) {   
os.write(b, 0, count);   
}   
is.close();   
os.close();   
}   
   
   
public static String readerToString(Reader is) throws IOException {   
StringBuffer sb = new StringBuffer();   
char[] b = new char[BLKSIZ];   
int n;   
   
   
while ((n = is.read(b)) > 0) {   
sb.append(b, 0, n);   
}   
   
   
return sb.toString();   
}   
   
   
public static String inputStreamToString(InputStream is)   
throws IOException {   
return readerToString(new InputStreamReader(is));   
}   
   
   
public static void stringToFile(String text, String fileName)   
throws IOException {   
BufferedWriter os = new BufferedWriter(new FileWriter(fileName));   
os.write(text);   
os.flush();   
os.close();   
}   
   
   
public static BufferedReader openFile(String fileName)   
throws IOException {   
return new BufferedReader(new FileReader(fileName));   
}   
}  


