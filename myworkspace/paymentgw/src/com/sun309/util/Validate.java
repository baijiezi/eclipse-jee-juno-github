package com.sun309.util;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate
{
	public boolean isMobile(String mobile)
	{
		String cmcc = "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[012789]{1})|([8]{1}[78]{1}))[0-9]{8}$";
		String cucc = "^[1]{1}(([3]{1}[0-3]{1})|([5]{1}[356]{1})|([8]{1}[56]{1}))[0-9]{8}$";
		String cnc = "^[1]{1}(([5]{1}[3]{1})|([8]{1}[9]{1}))[0-9]{8}$";

		boolean flag;// 存储匹配结果
		// 判断手机号码是否是11位
		if (mobile.length() == 11)
		{
			// 判断手机号码是否符合中国移动的号码规则
			if (mobile.matches(cmcc))
			{
				flag = true;
			}
			// 判断手机号码是否符合中国联通的号码规则
			else if (mobile.matches(cucc))
			{
				flag = true;
			}
			// 判断手机号码是否符合中国网通的号码规则
			else if (mobile.matches(cnc))
			{
				flag = true;
			}
			// 都不合适
			else
			{
				flag = false;
			}
		}
		// 不是11位
		else
		{
			flag = false;
		}
		return flag;

	}

	public boolean isSimpleDate(String date)
	{
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		if(!p.matcher(date).matches()) return false;
		
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			dateFormat.setLenient(false);
			dateFormat.parse(date);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
		return true;
	}

	public boolean isEmail(String email)
	{
		if (email == null || email.equals(""))
			return false;
		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		Matcher isMail = pattern.matcher(email);
		return isMail.matches();

	}
	
	public boolean isUrl(String url)
	{
		if(url == null || url.equals(""))
			return false;
		Pattern pattern = Pattern.compile("^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$",Pattern.CASE_INSENSITIVE );
		Matcher isUrl = pattern.matcher(url);
		return isUrl.matches();
	}
	
	public boolean isNum(String num)
	{
		if (num == null || num.equals(""))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(num);
		return isNum.matches();
	}
	
	public boolean isChar(String c)
	{
		if (c == null || c.equals(""))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*|[a-z]*|[A-Z]*");
		Matcher isNum = pattern.matcher(c);
		return isNum.matches();
	}

	public boolean isTel(String tel)
	{
		if (tel == null || "".equals(tel))
			return false;
		Pattern pattern = Pattern.compile("[0-9]{3}-[0-9]{8}|[0-9]{4}-[0-9]{8}|[0-9]{4}-[0-9]{7}$"); 
		Matcher isNum = pattern.matcher(tel);
		return isNum.matches();
	}

	public boolean isIdCard(String idCard)
	{
		if (idCard == null || idCard.equals(""))
			return false;
		if (idCard.length() != 15 && idCard.length() != 18)
			return false;
		Pattern pattern = Pattern.compile("^(\\d{15}|(\\d{17}[xX\\d]))$");
		Matcher isIdentityCard = pattern.matcher(idCard);
		if (!isIdentityCard.matches())
			return false;
		if (idCard.length() == 18)
		{
			int yearPrefix = Integer.parseInt(idCard.substring(6, 8));
			if (yearPrefix < 19 || yearPrefix > 21)
				return false;// 出生日期必须大于1900年小于2100年
			int month = Integer.parseInt(idCard.substring(10, 12));
			if (month > 12 || month == 0)
				return false; // 验证月
			int day = Integer.parseInt(idCard.substring(12, 14));
			if (day > 31 || day == 0)
				return false; // 验证日
			String checkDigit = getCheckDigitFor18(idCard);
			if (checkDigit.equals("-1"))
				return false;
			if (checkDigit.equals(idCard.substring(17, 18).toUpperCase()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (idCard.length() == 15)
		{
			int month = Integer.parseInt(idCard.substring(8, 10));
			if (month > 12 || month == 0)
				return false; // 验证月
			int day = Integer.parseInt(idCard.substring(10, 12));
			if (day > 31 || day == 0)
				return false;
			return true;
		}
		return false;
	}

	private static String getCheckDigitFor18(String card)
	{
		// 身份证校验位
		String[] CHECK_DIGIT = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
		// 身份证加权因子
		int[] gene = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		if (card == null || card.equals(""))
			return "-1";
		int sum = 0;
		for (int i = 0; i < 17; i++)
		{
			sum += Integer.parseInt(card.substring(i, i + 1)) * gene[i];
		}
		return CHECK_DIGIT[sum % 11];
	}

	public boolean isChinese(char c)
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
		{
			return true;
		}
		return false;
	}

	public boolean isChinese(String strName)
	{
		char[] ch = strName.toCharArray();
		boolean result = true;
		for (int i = 0; i < ch.length; i++)
		{
			char c = ch[i];
			if (!isChinese(c))
			{
				result=false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 普通康众卡
	 * 
	 * @param kzCardNo
	 * @return
	 */
	public boolean checkKzCardNo(String kzCardNo)
	{
		if(kzCardNo == null) return false;
		if(kzCardNo.length() != 18) return false;
		
		Pattern pattern = Pattern.compile("309[0-9]{11}8309$"); 
		Matcher isKzCardNo = pattern.matcher(kzCardNo);
		return isKzCardNo.matches(); 
	}
	
//	public boolean checkKzCardNo(String kzCardNo)
//	{
//		if(kzCardNo == null) return false;
//		if(kzCardNo.length() != 16 && kzCardNo.length() != 18) return false;
//		
//		Pattern pattern1 =Pattern.compile("620085[0-9]{10}$"); 
//		Matcher cardNo1 = pattern1.matcher(kzCardNo);
//		
//		Pattern pattern2 =Pattern.compile("309[0-9]{11}8309$"); 
//		Matcher cardNo2 = pattern2.matcher(kzCardNo);
//		
//		boolean result = cardNo1.matches() || cardNo2.matches();
//		
//		return result;
//	}
	
	/**
	 * 阳光康众卡
	 * 
	 * @param kzCardNo
	 * @return
	 */
	//测试运用期，两个月
	//加入isTestUse的目的：为了让2010-09-01光大的系统能正常运作后，业务系统不做修改，只修改这个参数
	public boolean checkSunKzCardNo(String kzCardNo, boolean isTestUse)
	{
		if(!isTestUse) return false;
		
		return this.checkSunKzCardNo(kzCardNo);
	}
	
	public boolean checkSunKzCardNo(String kzCardNo)
	{
		//if(true) return false;
		
		if(kzCardNo == null) return false;
		if(kzCardNo.length() != 16) return false;
		
//		Pattern pattern = Pattern.compile("62008512[0-9]{8}$"); 
//		Matcher isKzCardNo = pattern.matcher(kzCardNo);
//		return isKzCardNo.matches(); 
//		
		
		Pattern pattern = Pattern.compile("620085[0-9]{10}$"); 
		Matcher isKzCardNo = pattern.matcher(kzCardNo);
		
		Pattern pattern1 =Pattern.compile("622661[0-9]{10}$"); 
		Matcher cardNo1 = pattern1.matcher(kzCardNo);
		
		Pattern pattern3 =Pattern.compile("622662[0-9]{10}$"); 
		Matcher cardNo3 = pattern3.matcher(kzCardNo);
		
		Pattern pattern2 =Pattern.compile("900302[0-9]{10}$"); 
		Matcher cardNo2 = pattern2.matcher(kzCardNo);
		
		boolean result = isKzCardNo.matches() || cardNo1.matches() || cardNo2.matches() || cardNo3.matches();
		
		return result;
	}
	
	/**
	 * 体验卡验证
	 * 
	 * @param kzCardNo
	 * @return
	 */
	public boolean checkTryCardNo(String kzCardNo)
	{
		if(kzCardNo == null) return false;
		if(kzCardNo.length() != 12) return false;
		
		Pattern pattern = Pattern.compile("309[0-9]{9}$"); 
		Matcher isKzCardNo = pattern.matcher(kzCardNo);
		return isKzCardNo.matches(); 
	}
	
	public boolean checkTimeIsToday(Long time)
	{
		DateUtils du = new DateUtils();
		Long start = du.stringDataToLongTime(new StringBuffer(du.getSimpleDate()).append(" 00:00:00").toString());
		Long end = du.stringDataToLongTime(new StringBuffer(du.getSimpleDate()).append(" 23:59:59").toString());
		if(time > start && end > time)
		{
			return true;
		}
		return false;
	}
	
	public boolean isNetPayWaresCode(String waresCode)
	{
		if(waresCode == null || waresCode.equals(""))
			return false;
		//Pattern pattern = Pattern.compile("^[A-Z]{1}+[A-Z0-9]{7,31}+$"); 
		//Matcher isWaresCode = pattern.matcher(waresCode);
		//return isWaresCode.matches();
		return true;
	}
	
	public boolean isWaresName(String waresName)
	{
		if(isChinese(waresName) && waresName.length() >= 4 &&waresName.length() <= 32)
			return true;
		return false;
	}
	
	public boolean isSystmeName(String sysName)
	{
		if(sysName == null || sysName.equals(""))
			return false;
		Pattern pattern = Pattern.compile("^[A-Z]{1}+[A-Z0-9]{3,31}+$"); 
		Matcher isWaresCode = pattern.matcher(sysName);
		return isWaresCode.matches();
	}
	
	public boolean isRsultType(String type)
	{
		if(type.equals("HTML") || type.equals("XML"))
		{
			return true;
		}
		return false;
	}
	
	public boolean isString(String string)
	{
		if(string == null || string.equals(""))
			return false;
		return true;
	}
		
	public boolean isObject(Object obj)
	{
		if(obj == null)
			return false;
		return true;
	}
	
	public boolean isNetPayID(String ID)
	{
		if(ID.startsWith("NPGW"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
