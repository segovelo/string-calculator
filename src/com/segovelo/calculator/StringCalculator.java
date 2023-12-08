package com.segovelo.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/** 
* 8 Dec 2023
* @Javadoc TODO 
*
* @author Segovelo  **/

public class StringCalculator {
	public static void main(String[] args) {
		
		String[] numbers= {"3, 4,,5","1\r\n2\n3,4","","  ", "//#\n1#2#3#4", "//:\n1:2:3:4"};
		for(String number : numbers) {
			    System.out.println("Result = " + add(number));
		}		
	}
	
	public static Integer add(String numbers) {
		if (StringUtils.isNotBlank(numbers)) {
			Pattern pattern = Pattern.compile("//.\n.+");
			Matcher matcher = pattern.matcher(numbers);
			String delimiter = ",|\r?\n|\r";
			if(matcher.matches()) {
				delimiter = numbers.substring(2,3);
				numbers = numbers.substring(3);
			}
			String[] numberArray = numbers.split(delimiter);
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
