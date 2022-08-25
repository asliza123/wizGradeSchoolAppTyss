package com.wizGradeSchoolapp.admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateTimetableAsAdminVerifyInTeacherModule {

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
		
		//step-2:Read all the data from the excel sheet
		String clsNm = eul.getExcelData("Sheet1", 5, 2);
		String clsCount = eul.getExcelData("Sheet1", 5, 3);
		String grd = eul.getExcelData("Sheet1", 5, 4);
		String dayNm = eul.getExcelData("Sheet1", 5, 5);
		String subNm = eul.getExcelData("Sheet1", 5, 6);
		String teNm = eul.getExcelData("Sheet1", 5, 7);
		String startTm = eul.getExcelData("Sheet1", 5, 8);
		String endTm = eul.getExcelData("Sheet1", 5, 9);
		
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
		driver.get(URL);
		
		//step-4:Admin Login to application
		driver.findElement(By.id("email")).sendKeys(AUN);
		driver.findElement(By.id("password")).sendKeys(APWD);
		driver.findElement(By.id("btnSubmit")).click();
		
		//step-5:Navigate to classroom Link
		driver.findElement(By.xpath("//span[text()='Classroom']")).click();

		//step-6:create a classroom
		driver.findElement(By.id("name")).sendKeys(clsNm);
		driver.findElement(By.id("student_count")).sendKeys(clsCount);
		driver.findElement(By.id("btnSubmit")).click();
		driver.findElement(By.xpath("//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")).click();
		
		//step-7:Navigate to timetable link
		driver.findElement(By.xpath("//span[text()='Timetable']")).click();
		
		//step-8:select a grade
		WebElement gradeList = driver.findElement(By.id("grade"));
		wul.select(gradeList, grd);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		//step-9:create a timetable
		driver.findElement(By.xpath("//a[@class='btn btn-success btn-sm pull-right']//span[@class='glyphicon glyphicon-plus']")).click();
		WebElement dNm = driver.findElement(By.id("day"));
		wul.select(dNm, dayNm);
		
		WebElement sNm = driver.findElement(By.id("subject"));
		wul.select(sNm, subNm);
		
		WebElement trNm = driver.findElement(By.id("teacher"));
		wul.select(trNm, teNm);
		
		WebElement crNm = driver.findElement(By.id("classroom"));
		wul.select(crNm, clsNm);
		
		driver.findElement(By.id("start_time")).sendKeys(startTm);
		driver.findElement(By.id("end_time")).sendKeys(endTm);
		driver.findElement(By.id("btnSubmit")).click();
		
		//step-10: Verify timetable created or not
		driver.findElement(By.xpath("//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")).click();
		
		//step-11: Admin Logout from the application
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		
		//step-12: Teacher login to application
		driver.findElement(By.id("email")).sendKeys(TUN);
		driver.findElement(By.id("password")).sendKeys(TPWD);
		driver.findElement(By.id("btnSubmit")).click();
		
		//step-13:Navigate to timetable link
		driver.findElement(By.xpath("//span[text()='Timetable']")).click();
		driver.findElement(By.xpath("//a[text()='All Timetable']")).click();
		WebElement grde = driver.findElement(By.id("grade"));
		Select s6=new Select(grde);
		s6.selectByVisibleText(grd);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		String actualSub = driver.findElement(By.xpath("//table[@id='example1']/descendant::td[contains(text(),'"+subNm+"')]")).getText();
		if(actualSub.contains(subNm))
		{
			System.out.println("Sucessfully Done");
		}
		else
		{
			System.out.println("Not Done");
		}
		
		//step-14: Teacher Logout from the application
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		driver.close();
	}

}
