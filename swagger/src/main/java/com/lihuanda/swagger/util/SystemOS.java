package com.lihuanda.swagger.util;

public class SystemOS
{
	/*
	 * @return true---是Windows操作系统
	 */
	public static boolean isWindowsOS()
	{
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1)
		{
			isWindowsOS = true;
		}
		return isWindowsOS;
	}
}
