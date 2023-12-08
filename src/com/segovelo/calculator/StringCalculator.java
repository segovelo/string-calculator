package com.segovelo.calculator;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/** 
* 8 Dec 2023
* @Javadoc TODO 
*
* @author Segovelo  **/

public class StringCalculator {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String str= "";
		do {
			System.out.print("Enter a string: ");  
			//str= sc.nextLine();
			str = "1\r\n2\n3,4";
			if(!"exit".equalsIgnoreCase(str))
			    System.out.println("Result = " + add(str));
		} while(!"exit".equalsIgnoreCase(str));
		sc.close();
	}
	public static Integer add(String numbers) {
		if (StringUtils.isNotBlank(numbers)) {
			String[] numberArray = numbers.split(",|\r?\n|\r");
			Integer result = 0;
			for ( String number : numberArray) {
				if (StringUtils.isNotBlank(number))
					result += Integer.valueOf(number.strip());
			}
			return result;
						
		}
		else return 0;
	}

}
