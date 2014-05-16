package com.sun309.gateway.util;

import java.security.MessageDigest;

public class ProcessPassword
{
	/**
	 * 装字符串MD5加密
	 * 
	 * @param encryString
	 * @return
	 */
	public String createMD5String(String encryString)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(encryString.getBytes());
			byte b[] = digest.digest();
			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++)
			{
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			digest = null;
			b = null;
			return buf.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对密码加�?
	 * 
	 * @param password
	 * @return String encryptPassword
	 */
	public String encryptPassword(String password)
	{
		String encryptPassword = "";
		RandomString rand = new RandomString();
		String randStr = rand.randString(6, 2, false);
		String randEncrypt = createMD5String(randStr).substring(0, 4);
		encryptPassword = new StringBuffer(createMD5String(new StringBuffer(createMD5String(password)).append(randEncrypt).toString())).append(":").append(randEncrypt).toString();
		return encryptPassword;
	}

	/**
	 * 对密码进行校�?
	 * 
	 * @param password
	 *            //表单提交的密�?
	 * @param encryptedString
	 *            //数据库已加密的密�?
	 * @return String encryptPassword
	 */
	public boolean checkPassword(String password, String encryptedString)
	{
		String stringArray[] = new String[2];
		String checkPassword = "";
		stringArray = encryptedString.split(":");
		checkPassword = new StringBuffer(createMD5String(new StringBuffer(createMD5String(password)).append(stringArray[1]).toString())).append(":").append(stringArray[1]).toString();
		if (checkPassword.equals(encryptedString))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param password
	 * @param algorithm
	 * @return
	 */
	public String md5(String password)
	{
		String algorithm = "MD5";
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try
		{
			md = MessageDigest.getInstance(algorithm);
		}
		catch (Exception e)
		{
			return password;
		}
		md.reset();
		md.update(unencodedPassword);
		byte[] encodedPassword = md.digest();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++)
		{
			if ((encodedPassword[i] & 0xff) < 0x10)
			{
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	public static void main(String[] args)
	{
	}

}

