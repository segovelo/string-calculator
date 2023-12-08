package com.segovelo.calculator;

import java.util.ArrayList;
import java.util.List;
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
		
		String[] numbers= {"3, 4,,5","1\r\n2\n3,4","","2,-4,3,-5", "//#\n1#2#3#4", "//:\n1:2:3:4"};
		for(String number : numbers) {
			    System.out.println("Result = " + add(number));
		}		
	}
	
	public static Integer add(String numbers) {
		if (StringUtils.isNotBlank(numbers)) {
			List<Integer> negativeValues = new ArrayList<Integer>();
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
				if (StringUtils.isNotBlank(number)) {
					Integer value = Integer.valueOf(number.strip());
					try {
						if(value < 0) throw new NegativesNotAllowedException(value);
						if(value < 1000)
							result += Integer.valueOf(number.strip());
					} catch (NegativesNotAllowedException exception) {
						System.out.println(String.format("Negatives Not Allowed: %s", exception.getNumber()));
						negativeValues.add(value);
					}
				}
			}
			System.out.println(String.format("Negatives Not Allowed : %s",negativeValues.toString()));
			return result;
						
		}
		else return 0;
	}

}
