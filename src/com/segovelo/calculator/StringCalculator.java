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
			str= sc.nextLine(); 
			System.out.println("Result = " + add(str));
		} while("exit" != str);
	}
	public static Integer add(String numbers) {
		if (StringUtils.isNotBlank(numbers)) {
			String[] numberArray = numbers.split(",");
			Integer result = 0;
			for ( String number : numberArray) {
			   result += Integer.valueOf(number);
			}
			return result;
						
		}
		else return Integer.valueOf("0");
	}

}
