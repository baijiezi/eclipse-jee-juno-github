package com.sun309.gateway.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

public class StrutsUpload
{
	private static Logger log = Logger.getLogger(StrutsUpload.class);
	/**
	 * struts 文件上传
	 * 
	 * @param file
	 * @param request
	 * @param storePath 保存新路径 (eg:"/a/ab/")
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String beginUpload(FormFile file, HttpServletRequest request, String storePath)
	{
		String newFilePath = "";
		
        try 
        {
        	InputStream stream = file.getInputStream();//把文件读入
            String filePath = request.getRealPath("/");//取当前系统路径
            String fileStoreNewPath = filePath + storePath;//文件保存的新路径
            log.debug("fileStoreNewPath -- " + fileStoreNewPath);
    		File storePathFile = new File(fileStoreNewPath);
    		if(!storePathFile.exists())
    		{
    			storePathFile.mkdirs();
    		}
            filePath = Common.processAbsolutePath(fileStoreNewPath);
            String ext = getFileExtension(file.getFileName());
            RandomString rand = new RandomString();
            String newFileName = Common.getNowTime() + rand.randString(6, 3, false) + "." + ext;
            newFilePath = filePath + newFileName;
            
            OutputStream bos = new FileOutputStream(newFilePath);//建立一个上传文件的输出流
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while((bytesRead = stream.read(buffer, 0, 8192)) != -1) 
            {
            	bos.write(buffer, 0, bytesRead);//将文件写入服务器
            }
            bos.close();
            stream.close();
        }
        catch(Exception e)
        {
        	log.debug(e);
        }
        return newFilePath;
	}
	
    /**
     * 获取相片的后缀名
     * 
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName)
    {
    	String file[] = fileName.split("\\x2E");
    	String ext = file[(file.length-1)];
    	return ext;
    }
}
