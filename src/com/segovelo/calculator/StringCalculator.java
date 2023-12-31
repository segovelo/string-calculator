package com.segovelo.calculator;

import java.util.ArrayList;
import java.util.List;
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
		
		String[] numbers= {"//[###][||][%]\n1###2||3%4","//[|][%]\n1||2%3","//[|||]\n1|||2|||3", "//[:::]\n1:::2:::3","//[???]\n1???2???3","3, 4,,5","1\r\n2\n3,4","","2,-4,3,-5", "//#\n1#2#3#4", "//:\n1:2:3:4", "1001,2" };
		for(String number : numbers) {
			    System.out.println("Result = " + add(number));
		}		 
	}
	
	public static Integer add(String numbers) {
		if (StringUtils.isNotBlank(numbers)) {
			List<Integer> negativeValues = new ArrayList<Integer>();
			Pattern pattern = Pattern.compile("//.\n.+");
			Matcher matcher = pattern.matcher(numbers);
			Pattern patternMult = Pattern.compile("//\\[(.)\\1+\\]\n.+");
			Matcher matcherMult = patternMult.matcher(numbers);
			Pattern patternMult2 = Pattern.compile("//(\\[.+\\]){2,}\n.+");
			Matcher matcherMult2 = patternMult2.matcher(numbers);

			String delimiter = ",|\r?\n|\r";
			if(matcher.matches()) {
				delimiter = numbers.substring(2,3);
				numbers = numbers.substring(3);
		/*	} else if (matcherMult.matches()) {
				delimiter = numbers.substring(3,numbers.indexOf("]")); 
				numbers = numbers.substring(numbers.indexOf("\n")+1);
				numbers = numbers.replaceAll("(.)\\1+", "$1");
				delimiter = "\\"+ delimiter.substring(0,1);  */
			} else if (matcherMult.matches() || matcherMult2.matches()) {
				List<String> delimiters = new ArrayList<String>();
				String numbersTemp = numbers;
				int openBracketIndex = numbers.indexOf("["); 
				int closeBracketIndex = numbers.indexOf("]");
				do {
					delimiters.add(numbersTemp.substring(openBracketIndex+1,closeBracketIndex).substring(0,1));
					numbersTemp = numbersTemp.substring(closeBracketIndex+1);
					openBracketIndex = numbersTemp.indexOf("[");
					closeBracketIndex = numbersTemp.indexOf("]");
				} while (openBracketIndex != -1);
				StringBuilder sb = new StringBuilder("[");
				for(String delim : delimiters) {
					sb.append("\\\\");
					sb.append(delim);
				}
				sb.append("]");
				delimiter = sb.toString();
				numbers = numbers.substring(numbers.indexOf("\n")+1);
				numbers = numbers.replaceAll("(.)\\1+", "$1");
			}
			String[] numberArray = numbers.split(delimiter);
			Integer result = 0;
			for ( String number : numberArray) {
				if (StringUtils.isNotBlank(number)) {
					Integer value = Integer.valueOf(number.strip());
					try {
						if(value < 0) throw new NegativesNotAllowedException(value);
						if(value < 1001)
							result += Integer.valueOf(number.strip());
					} catch (NegativesNotAllowedException exception) {
						System.out.println(String.format("Negatives Not Allowed: %s", exception.getNumber()));
						negativeValues.add(value);
					}
				}
			}
			if(!negativeValues.isEmpty())
				System.out.println(String.format("Negatives Not Allowed : %s",negativeValues.toString()));
			return result;
						
		}
		else return 0;
	}

}
