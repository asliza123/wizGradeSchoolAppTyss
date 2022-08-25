package com.wizGradeSchoolapp.admin;

import java.io.IOException; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateExamAsAdminAndVerifyInTeacherAndParentModule {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		FileUtilittyLibraries ful=new FileUtilittyLibraries();
		ExcelUtilitityLibraries eul= new ExcelUtilitityLibraries();
		WebdriverUtilityLibraries wul=new WebdriverUtilityLibraries();
		//step-1:Read all the common data
		String BROWSER = ful.getPropertyKeyValue("browser");
		String URL = ful.getPropertyKeyValue("url");
		String AUN = ful.getPropertyKeyValue("adminusername");
		String APWD = ful.getPropertyKeyValue("adminpassword");
		String TUN = ful.getPropertyKeyValue("teacherusername");
		String TPWD = ful.getPropertyKeyValue("teacherpassword");
		String PUN = ful.getPropertyKeyValue("parentusername");
		String PPWD = ful.getPropertyKeyValue("parentpassword");

		//step-2:Read all the data from the excel sheet
		String examName = eul.getExcelData("Sheet1", 3, 2);
		
		//step-3:Launch the browser --> Runtime polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		wul.maximizeTheWindow(driver);
		wul.waitForElementInDom(driver);
		//step-4:Login to application
		driver.get(URL);
		driver.findElement(By.id("email")).sendKeys(AUN);
		driver.findElement(By.id("password")).sendKeys(APWD);
		driver.findElement(By.id("btnSubmit")).click();
		//step-5:Navigate to Exam Link
		driver.findElement(By.xpath("//span[text()='Exam']")).click();
		driver.findElement(By.xpath("//a[text()='Create Exam']")).click();
		//step-6:Create exam with mandatory inputs
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-plus']")).click();
		driver.findElement(By.id("name")).sendKeys(examName);
		driver.findElement(By.id("btnSubmit")).click();
		//step-7: Admin Logout from the application
		driver.findElement(By.xpath("//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")).click();
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		//step-8:verify with teacher 
		driver.findElement(By.id("email")).sendKeys(TUN);
		driver.findElement(By.id("password")).sendKeys(TPWD);
		driver.findElement(By.id("btnSubmit")).click();
		driver.findElement(By.xpath("//span[text()='Exam']")).click();
		driver.findElement(By.xpath("//a[text()='Exam Timetable']")).click();
		String actualEx= driver.findElement(By.xpath("//select[@id='exam']/option[text()='"+examName+"']")).getText();
		System.out.println(actualEx);
		if(actualEx.equals(examName))
		{
			System.out.println("Exam "+actualEx+" created");
		}
		else
		{
			System.out.println("Exam "+actualEx+" not created");
		}
		//step-9: Teacher Logout from the application
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		//step-10:verify with parent module
		driver.findElement(By.id("email")).sendKeys(PUN);
		driver.findElement(By.id("password")).sendKeys(PPWD);
		driver.findElement(By.id("btnSubmit")).click();
		driver.findElement(By.xpath("//span[text()='Exam']")).click();
		driver.findElement(By.xpath("//a[@href='my_sons_exam_timetable.php' and text()]")).click();
		String actualExm = driver.findElement(By.xpath("//select[@id='exam']/option[text()='"+examName+"']")).getText();
		System.out.println(actualExm);
		if(actualExm.equals(examName))
		{
			System.out.println("Exam "+actualExm+" created");
		}
		else
		{
			System.out.println("Exam "+actualExm+" not created");
		}
		//step-11: Parent Logout from the application
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		
	}

}
