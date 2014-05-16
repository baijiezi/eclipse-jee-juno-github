package com.sun309.gateway.api;

import java.io.*;
import java.util.zip.*;
import sun.misc.*;

public class CompressUtil
{
	// 压缩
	public static String Compress(String data)
	{
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DeflaterOutputStream zos = new DeflaterOutputStream(bos);
			zos.write(data.getBytes("GB2312"));
			zos.close();
			return new String(new BASE64Encoder().encode(bos.toByteArray())).replaceAll("\r", "").replaceAll("\n", "");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return "压缩失败";
		}
	}

	public static String DeCompress(String encdata)
	{
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InflaterOutputStream zos = new InflaterOutputStream(bos);
			zos.write(new BASE64Decoder().decodeBuffer(encdata));
			zos.close();
			return new String(bos.toByteArray());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return "解压缩失败";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String xml = "<Ms c=\"1\"><m><FD>13790286236,13760607472</FD><FM>发送内容</FM><FT>2009-07-17</FT></m></Ms>";
		System.out.println(Compress(xml));
	}

}
