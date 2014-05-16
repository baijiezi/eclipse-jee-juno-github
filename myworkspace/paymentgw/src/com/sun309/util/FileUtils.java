package com.sun309.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils
{
	public String writeLog(String content, String type) 
	{
		BufferedOutputStream Buff = null;
		FileOutputStream outSTr = null;
		try
		{
			byte[] bs = content.getBytes();  
			DateUtils du = new DateUtils();
			String dir = "/tran_data/" + du.getSimpleDate() + "/";
			File _dir = new File(dir);
			if(!_dir.exists())
			{
				_dir.mkdirs();
			}
			
			String file = dir + type + DateUtils.getNowTime() + ".log";
			File _file = new File(file);
			if(!_file.exists())
			{
				_file.createNewFile();
			}
			
			outSTr = new FileOutputStream(_file);
			Buff = new BufferedOutputStream(outSTr);
			Buff.write(bs);
			Buff.flush();
			Buff.close();
			return file;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
		finally
		{
			try
			{
				Buff.close();
			}
			catch (Exception e)
			{
				return null;
			}
		}
	}

	public String readFileContent(String path)
	{
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();
		try
		{
			
			String tempString;
			reader = new BufferedReader(new FileReader(path));
			while ((tempString = reader.readLine()) != null)
			{
				content.append(tempString);
			}
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e1)
				{
				}
			}
		}
		return content.toString();
	}
	public static void main(String[] args)
	{
		FileUtils f = new FileUtils();
		String file = f.writeLog("test", "dept");
		System.out.println(file);
	}
}
