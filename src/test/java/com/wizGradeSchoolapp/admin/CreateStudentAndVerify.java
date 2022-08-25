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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateStudentAndVerify {

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
		String grdPh= ful.getPropertyKeyValue("grdphoto");
		
		//step-2: read all excel data
		String stdIndx=exl.getExcelData("Sheet1", 11, 2);
		String stdFullNm=exl.getExcelData("Sheet1", 11, 3);
		String stdNameWI=exl.getExcelData("Sheet1", 11, 4);
		String stdAdd = exl.getExcelData("Sheet1", 11, 5);
		String stdEml = exl.getExcelData("Sheet1", 11, 6);
		String stdPhn = exl.getExcelData("Sheet1", 11, 7);
		String stdDOB = exl.getExcelData("Sheet1", 11, 8);
		String stdGndr = exl.getExcelData("Sheet1", 11, 9);
		String grdFullNm= exl.getExcelData("Sheet1", 11, 10);
		String grdNameWI= exl.getExcelData("Sheet1", 11, 11);
		String grdAdd = exl.getExcelData("Sheet1", 11, 12);
		String grdEml = exl.getExcelData("Sheet1", 11, 13);
		String grdPhn = exl.getExcelData("Sheet1", 11, 14);
		String grdDOB = exl.getExcelData("Sheet1", 11, 15);
		String grdGndr = exl.getExcelData("Sheet1", 11, 16);
		String grdNm = exl.getExcelData("Sheet1", 11, 17);
		
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
		driver.get(URL);
		
		//step-4: login as admin
		driver.findElement(By.id("email")).sendKeys(UN);
		driver.findElement(By.id("password")).sendKeys(PWD);
		driver.findElement(By.id("btnSubmit")).click();
		
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
		
		driver.findElement(By.id("g_full_name")).sendKeys(grdFullNm);
		driver.findElement(By.id("g_i_name")).sendKeys(grdNameWI);
		driver.findElement(By.id("g_address")).sendKeys(grdAdd);
		driver.findElement(By.id("g_email")).sendKeys(random+grdEml);
		driver.findElement(By.id("g_phone")).sendKeys(grdPhn);
		driver.findElement(By.id("g_b_date")).sendKeys(grdDOB);
		WebElement pGenderList = driver.findElement(By.id("g_gender"));
		wul.select(pGenderList, grdGndr);
		File f1=new File(grdPh);
		String grdAbsolutePath = f1.getAbsolutePath();
		WebElement grdPhoto = driver.findElement(By.id("g_fileToUpload"));
		grdPhoto.sendKeys(grdAbsolutePath);
		
		driver.findElement(By.id("btnSubmit")).click();
		
		//step-8: select grade for student
		WebElement gradeList = driver.findElement(By.id("grade"));
		wul.select(gradeList, grdNm);
		driver.findElement(By.xpath("//table[@class='table ']/tbody/tr/td[text()='Subject 2']/parent::tr/td/input[@id='checkbox']")).click();
		driver.findElement(By.xpath("//table[@class='table ']/tbody/tr/td[text()='Subject 7 ']/parent::tr/td/input[@id='checkbox']")).click();
		driver.findElement(By.id("btnSubmit1")).click();
		driver.findElement(By.xpath("//div[@class='msk-heading']/descendant::span[@class='glyphicon glyphicon-remove']")).click();
		
		//step-9: verify the created student
		driver.findElement(By.xpath("//span[text()='Student']")).click();
		driver.findElement(By.xpath("//a[text()=' All Student']")).click();
		WebElement gradeList1 = driver.findElement(By.id("grade"));
		wul.select(gradeList1, grdNm);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		driver.findElement(By.xpath("//input[@class='form-control input-sm']")).sendKeys(stdNameWI);
		String actual = driver.findElement(By.xpath("//table[@id='example1']/tbody/tr/td/a[text()='"+stdNameWI+"']")).getText();
		if(stdNameWI.equals(actual)) {
			System.out.println("Student created.");
		}
		else
		{
			System.out.println("student not created");
		}
		
		//step-10: logout from the application
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		driver.close();
	}

}
