package utils.JUtils.other;
   
import java.io.File;   
import java.io.IOException;   
import java.net.URL;   
import java.util.Enumeration;   
import java.util.jar.JarFile;   
import java.util.zip.ZipEntry;   
   
import org.apache.commons.logging.Log;   
import org.apache.commons.logging.LogFactory;   
   
/**  
 * PackageUtil.java -- 包实用程序  
 *   
 * 根据包名找到在classpath中的所有类，并在其上执行动作 <br>  
 * PackageUtil不处理子包，譬如你有一个包foo.bar,下面有子包foo.bar.zz,  
 * 那么只处理foo.bar下面的包，而不处理foo.bar.zz下面的类。  
 * @author <a href="mailto:shiyiying@hotmail.com">ShiYiYing</a>  
 *  
 *   
 */   
public class PackageUtil {   
    private static Log log = LogFactory.getLog(PackageUtil.class);   
    private static final int FILEPROTOCOLLEN = "file:/".length();   
    private static final int JARTRINGLEN = ".jar".length();   
    public static void processClassesInPackage(   
        String packageName,   
        IClassAction action) {   
        String packagePathName = getPackagePathName(packageName);   
        Enumeration enum = getPackageResources(packageName, packagePathName);   
        if (enum == null)   
            return;   
        for (; enum.hasMoreElements();) {   
            URL url = (URL) (enum.nextElement());   
            if (isFile(url)) {   
                processDirectory(packageName, action, url);   
            } else if (isJar(url)) {   
                processJar(packageName, action, url);   
            }   
        }   
    }   
   
    /**  
     * Method processJar.  
     * @param packageName  
     * @param action  
     * @param url  
     */   
    private static void processJar(   
        String packageName,   
        IClassAction action,   
        URL url) {   
        final JarFile jarFile;   
        try {   
            jarFile =   
                new JarFile(   
                    url.getFile().substring(   
                        FILEPROTOCOLLEN,   
                        url.getFile().lastIndexOf(".jar!") + JARTRINGLEN));   
        } catch (IOException ioe) {   
            log.debug(   
                "Could not configure datastore from jar " + url.getFile());   
            return;   
        }   
   
        if (jarFile == null) {   
            log.debug(   
                "Could not configure datastore from jar " + url.getFile());   
            return;   
        }   
        Enumeration enum = jarFile.entries();   
        String packagePathName = getPackagePathName(packageName);   
        while (enum.hasMoreElements()) {   
            ZipEntry z = (ZipEntry) enum.nextElement();   
            String fileName = z.getName();   
            if (isClassFile(fileName)) {   
                String entryPackagePathName =   
                    fileName.substring(0, fileName.lastIndexOf("/"));   
                if (entryPackagePathName.equals(packagePathName)) {   
                    try {   
                        String entryClassName =   
                            fileName.substring(fileName.lastIndexOf("/") + 1);   
                        String className = getClassName(entryClassName);   
                        action.execute(   
                            loadClass(packageName + "." + className));   
                    } catch (Exception e) {   
                        log.info(   
                            "class file " + z.toString() + " cannot be loaded");   
                        log.debug(e.getMessage());   
                    }   
                }   
            }   
   
        }   
   
    }   
   
    private static boolean isFile(URL url) {   
        return url.getProtocol().equals("file");   
    }   
    private static boolean isJar(URL url) {   
        return url.getProtocol().equals("jar");   
    }   
    public static void processDirectory(   
        String packageName,   
        IClassAction action,   
        URL url) {   
        String pathName = converPercent20ToWhiteChar(url.getPath());   
        File file = new File(pathName);   
        if (file.isDirectory()) {   
            File[] classFiles = file.listFiles();   
            for (int i = 0; i  classFiles.length; i++) {   
                File classFile = classFiles[i];   
                if (classFile.isFile()) {   
                    String fileName = classFile.getName();   
                    if (isClassFile(fileName)) {   
                        try {   
                            String className = getClassName(fileName);   
                            action.execute(   
                                loadClass(packageName + "." + className));   
                        } catch (Exception e) {   
                            log.info(   
                                "class file "   
                                    + classFile.getAbsoluteFile()   
                                    + " cannot be loaded");   
                            log.debug(e.getMessage());   
                        }   
                    }   
                }   
            }   
        }   
    }   
   
    public static void processFile(   
        String packageName,   
        IClassAction action,   
        File classFile,   
        String fileName) {   
        if (isClassFile(fileName)) {   
            try {   
                String className = getClassName(fileName);   
                action.execute(loadClass(packageName + "." + className));   
            } catch (Exception e) {   
                log.info(   
                    "class file "   
                        + classFile.getAbsoluteFile()   
                        + " cannot be loaded");   
                log.debug(e.getMessage());   
            }   
        }   
    }   
   
    public static void processFile(   
        String packageName,   
        IClassAction action,   
        File classFile) {   
        if (classFile.isFile()) {   
            String fileName = classFile.getName();   
            if (isClassFile(fileName)) {   
                try {   
                    String className = getClassName(fileName);   
                    action.execute(loadClass(packageName + "." + className));   
                } catch (Exception e) {   
                    log.info(   
                        "class file "   
                            + classFile.getAbsoluteFile()   
                            + " cannot be loaded");   
                    log.debug(e.getMessage());   
                }   
            }   
        }   
    }   
    public static void processDirectory(   
        String packageName,   
        IClassAction action,   
        File file) {   
        if (file.isDirectory()) {   
            File[] classFiles = file.listFiles();   
            for (int i = 0; i  classFiles.length; i++) {   
                File classFile = classFiles[i];   
                if (classFile.isFile()) {   
                    String fileName = classFile.getName();   
                    if (isClassFile(fileName)) {   
                        try {   
                            String className = getClassName(fileName);   
                            action.execute(   
                                loadClass(packageName + "." + className));   
                        } catch (Exception e) {   
                            log.info(   
                                "class file "   
                                    + classFile.getAbsoluteFile()   
                                    + " cannot be loaded");   
                            log.debug(e.getMessage());   
                        }   
                    }   
                }   
            }   
        }   
    }   
    public static boolean isClassFile(String fileName) {   
        return fileName.endsWith(".class");   
    }   
    public static Class loadClass(String className)   
        throws ClassNotFoundException {   
        Class clazz = PackageUtil.class.getClassLoader().loadClass(className);   
        if (clazz != null)   
            return clazz;   
        return Thread.currentThread().getContextClassLoader().loadClass(   
            className);   
    }   
    public static String getClassName(String fileName) {   
        String className = fileName.substring(0, fileName.length() - 6);   
        return className;   
    }   
    public static Enumeration getPackageResources(   
        String packageName,   
        String packagePathName) {   
        Enumeration enum;   
        try {   
            enum =   
                PackageUtil.class.getClassLoader().getResources(   
                    packagePathName);   
            if (enum == null || !enum.hasMoreElements()) {   
                enum =   
                    Thread   
                        .currentThread()   
                        .getContextClassLoader()   
                        .getResources(   
                        packagePathName);   
            }   
        } catch (Exception e) {   
            log.info("cannot locate package " + packageName + " in classpath.");   
            log.debug(e.getMessage());   
            enum = null;   
        }   
        return enum;   
    }   
    public static String getPackagePathName(String packageName) {   
        String packagePathName = packageName.replace('.', '/');   
        return packagePathName;   
    }   
    private static String converPercent20ToWhiteChar(String pathName) {   
        if (pathName.equals("%20"))   
            return "";   
        int percent20StrPosition = pathName.indexOf("%20");   
   
        if (noPercent20Str(percent20StrPosition)) {   
            return pathName;   
        }   
        if (percent20StrPosition == (pathName.length() - 3)) {   
            return pathName.substring(0, percent20StrPosition);   
        }   
   
        StringBuffer resultBuffer = new StringBuffer();   
        resultBuffer.append(pathName.substring(0, percent20StrPosition));   
        resultBuffer.append(" ");   
        resultBuffer.append(   
            converPercent20ToWhiteChar(   
                pathName.substring(percent20StrPosition + 3)));   
        return resultBuffer.toString();   
    }   
    private static boolean noPercent20Str(int percent20StrPosition) {   
        return percent20StrPosition  0;   
    }   
}   



