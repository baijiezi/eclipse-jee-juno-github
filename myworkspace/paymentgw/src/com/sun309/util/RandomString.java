package com.sun309.util;

public class RandomString
{
	/**
	 * 生成随机字符串
	 * 
	 * @param randLen
	 * @param randType
	 * @param isUpperCase
	 * 
	 * @return String random
	 */
	public String randString(int randLen, int randType, boolean isUpperCase)
	{
		String random = "";
		int position = 0;
		String char26[] = {"a", "b", "c", "d", "e",  "f",  "g",  "h",  "i",  "j",  "k",  "l",  "m",  "n",  "o",  "p",  "q",  "r",  "s",  "t",  "u",  "v",  "w",  "y",  "x",  "z"};
		for(int i = 0; i < randLen; i++)
		{
			switch (randType) 
			{
				case 1:				// numbers
				{
					try {
						position = (int)(Math.random() * 9 + 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					Integer temp = new Integer(position);
					random += temp.toString();
					break;
				}
				case 2:             // charaters
				{
					try {
						position = (int)(Math.random() * 25 + 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
					random += char26[position];
					break;
				}
				case 3:				// charaters and numbers
				{
					int charaterOrNumber = (int)(Math.random() * 2 + 0);
					switch (charaterOrNumber) 
					{
						case 0:
						{
							try {
								position = (int)(Math.random() * 25 + 0);
							} catch (Exception e) {
								e.printStackTrace();
							}
							random += char26[position];
							break;
						}
						case 1:
						{
							try {
								position = (int)(Math.random() * 9 + 0);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							Integer temp = new Integer(position);
							random += temp.toString();
							break;
						}
						default:
							break;
					}
	
					break;
				}
				default:
				{
					break;
				}
			}
		}
		if(isUpperCase)
		{
			random = random.toUpperCase();
		}
		return random;
	}

}
