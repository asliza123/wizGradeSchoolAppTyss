package com.wizGradeSchoolapp.admin;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.JavaUtilityLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddTeacherAndVerifyOR {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		ExcelUtilitityLibraries exl= new ExcelUtilitityLibraries();
		FileUtilittyLibraries ful=new FileUtilittyLibraries();
		JavaUtilityLibraries jul=new JavaUtilityLibraries();
		WebdriverUtilityLibraries wul=new WebdriverUtilityLibraries();
		//step-1: read all common data
		String BROWSER = ful.getPropertyKeyValue("browser");
		String URL = ful.getPropertyKeyValue("url");
		String UN = ful.getPropertyKeyValue("adminusername");
		String PWD = ful.getPropertyKeyValue("adminpassword");
		String stdPh= ful.getPropertyKeyValue("stdphoto");

		//step-2: read all excel data
		String stdIndx=exl.getExcelData("Sheet1", 11, 2);
		String stdFullNm=exl.getExcelData("Sheet1", 11, 3);
		String stdNameWI=exl.getExcelData("Sheet1", 11, 4);
		String stdAdd = exl.getExcelData("Sheet1", 11, 5);
		String stdEml = exl.getExcelData("Sheet1", 11, 6);
		String stdPhn = exl.getExcelData("Sheet1", 11, 7);
		String stdDOB = exl.getExcelData("Sheet1", 11, 8);
		String stdGndr = exl.getExcelData("Sheet1", 11, 9);

		//step-3: launch the browser --> RunTime Polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new ChromeDriver();
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		wul.maximizeTheWindow(driver);
		wul.waitForElementInDom(driver);

		//step-4: login as admin
		LoginPage lp=new LoginPage(driver);
		driver.get(URL);
		lp.loginToApp(UN, PWD);

		//step-5: Navigate to Student link
		driver.findElement(By.xpath("//span[text()='Student']")).click();

		//step-6: Navigate to student preview page
		driver.findElement(By.xpath("//a[text()=' Add Student']")).click();

		//step-7: Create a student
		int random=jul.getRandomNumber();
		driver.findElement(By.id("index_number")).sendKeys(stdIndx+random);
		driver.findElement(By.id("full_name")).sendKeys(stdFullNm);
		driver.findElement(By.id("i_name")).sendKeys(stdNameWI);
		driver.findElement(By.id("address")).sendKeys(stdAdd);
		driver.findElement(By.id("email")).sendKeys(random+stdEml);
		driver.findElement(By.id("phone")).sendKeys(stdPhn);
		driver.findElement(By.id("b_date")).sendKeys(stdDOB);
		WebElement genderList = driver.findElement(By.id("gender"));
		wul.select(genderList, stdGndr);
		File f=new File(stdPh);
		String stdAbsolutePath = f.getAbsolutePath();
		WebElement stdPhoto = driver.findElement(By.id("fileToUpload"));
		stdPhoto.sendKeys(stdAbsolutePath);

	}

}
