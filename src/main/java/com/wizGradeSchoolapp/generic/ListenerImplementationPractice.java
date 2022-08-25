package com.wizGradeSchoolapp.generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.wizGradeSchoolapp.genericLibraries.BaseClass;

public class ListenerImplementationPractice implements ITestListener{

	public void onTestFailure(ITestResult result) {
		JavaUtilityLibraries jLib=new JavaUtilityLibraries();
		String sysDate = jLib.getSystemDate();
		int random = jLib.getRandomNumber();
		String testName=result.getMethod().getMethodName();
		System.out.println(testName+"======Execute and I am Listening======");
		EventFiringWebDriver eDriver=new EventFiringWebDriver(BaseClassPractice.stdriver);
		File src=eDriver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./screenshot/"+sysDate+testName+random+".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
// how to write for findElements as we are writing @findby for findElement

	
	
	
}
