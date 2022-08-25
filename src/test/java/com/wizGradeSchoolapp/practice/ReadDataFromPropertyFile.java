package com.wizGradeSchoolapp.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {
	public static void main(String[] args) throws IOException {
		//step-1: use fileinputstream to load the property file
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Data\\commondata.properties");
		//step-2: create object of properties and load the file
		Properties p=new Properties();
		p.load(fis);
		//step-3: provide key to read the value
		String URL = p.getProperty("url");
		System.out.println(URL);
		String un = p.getProperty("username");
		System.out.println(un);
		String pw=p.getProperty("password");
		System.out.println(pw);
		String BROWSER = p.getProperty("browser");
		System.out.println(BROWSER);
	}
}
