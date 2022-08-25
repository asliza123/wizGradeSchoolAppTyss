package com.wizGradeSchoolapp.practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.wizGradeSchoolapp.generic.JavaUtilityLibraries;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TakeScreenshot {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://google.com");
		//TakesScreenshot ts=(TakesScreenshot)driver;
		EventFiringWebDriver edriver= new EventFiringWebDriver(driver);
		JavaUtilityLibraries jLib=new JavaUtilityLibraries();
		int random = jLib.getRandomNumber();
		String sysDate = jLib.getSystemDate();
		File src = edriver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+sysDate+random+".png");
		FileUtils.copyFile(src, dest);
		
	}

}
