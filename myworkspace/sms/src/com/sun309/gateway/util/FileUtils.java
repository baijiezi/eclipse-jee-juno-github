package com.sun309.gateway.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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
			if (!_dir.exists())
			{
				_dir.mkdirs();
			}

			String file = dir + type + DateUtils.getNowTime() + ".log";
			File _file = new File(file);
			if (!_file.exists())
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
		File file = new File(path);
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			
			while ((tempString = reader.readLine()) != null)
			{
				// 显示行号
				content.append(tempString);
				line++;
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
		System.out.println(f.readFileContent("c:/test.xml"));
	}
}
