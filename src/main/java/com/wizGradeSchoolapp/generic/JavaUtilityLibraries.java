package com.wizGradeSchoolapp.generic;

import java.util.Date;
import java.util.Random;

/**
 * it's contains java specific libraries like getRandom and getSystumDate etc 
 * @author Asmita
 */
public class JavaUtilityLibraries {
	/**
	 * it is used to get random number with in the range of 1000
	 * @return
	 */
	public static int getRandomNumber() {
		Random ran=new Random();
		int random=ran.nextInt(1000);
		return random;
	}
	/**
	 * it is used to get the current system date based on YYYY-MM-DD format
	 * @return
	 */
	public String getSystemDate() {
		Date date=new Date();
		String currentDate=date.toString();
		System.out.println(currentDate);
		String []arr=currentDate.split(" ");
		String yyyy=arr[5];
		String dd=arr[2];
		int mm=date.getMonth()+1;
		String format=dd+"-"+mm+"-"+yyyy;
		return format;
	}
}
