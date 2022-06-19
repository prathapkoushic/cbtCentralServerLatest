package com.ttipl.util;

import org.springframework.stereotype.Component;

/**
 * some utility methods
 *@author  Gopi prasad 
 * 
 **/
@Component
public class Utility 
{

	/**
	 * @author : Gopi Prasad
	 * @Date : 2020-12-08
	 * @Des : Check ip address
	 * @return: boolean
	 */
	public boolean isIPAddressCorrect(String IPAddress)
	{
		boolean status = false;
		String[] ips;
		if (IPAddress.contains(".")) 
		{
			ips = IPAddress.split("\\.");
			if (ips.length == 4) 
			{
				for (String IPBlock : ips) 
				{
					if (!is_ip_block(IPBlock)) 
					{
						status = false;
						break;
					} 
					else
					{
						status = true;
					}
				}
			}
		}
		return status;
	}

	/**
	 * @author : Gopi Prasad
	 * @Date : 2020-12-08
	 * @param: ip_block
	 * @return: boolean
	 */
	public boolean is_ip_block(String ip_block) {
		int num = 0;
		boolean status = false;
		try {
			num = Integer.parseInt(ip_block);
			if (num <= 255) {
				status = true;
			}
		} catch (NumberFormatException e) {

		}
		return status;
	}
	
	
	
	/**
	 * @author : Gopi Prasad
	 * @Date : 2020-12-08
	 * @param: number
	 * @return: boolean
	 */
	public boolean isNumber(String number) {
		boolean status = false;
		try {
			 Integer.parseInt(number);
			 status=true;
		} catch (NumberFormatException e) {

		}
		return status;
	
	}
/*	public static void main(String[] args) {
		Utility utility = new Utility();
		System.out.println("correct  :" + utility.isIPAddressCorrect("1.1.1.1"));
		System.out.println("256 value :" + utility.isIPAddressCorrect("1.1.1.255"));
		System.out.println("String :" + utility.isIPAddressCorrect("1.1.1.ss"));
		System.out.println("wrong:" + utility.isIPAddressCorrect("1.1.1."));
		System.out.println("correct 255:" + utility.isIPAddressCorrect("255.255.255.255"));
	}*/
}
