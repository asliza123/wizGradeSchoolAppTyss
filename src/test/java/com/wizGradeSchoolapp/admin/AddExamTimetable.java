package com.wizGradeSchoolapp.admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddExamTimetable {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		FileUtilittyLibraries ful=new FileUtilittyLibraries();
		ExcelUtilitityLibraries eul= new ExcelUtilitityLibraries();
		WebdriverUtilityLibraries wul=new WebdriverUtilityLibraries();
		//step-1:Read all the common data
		String BROWSER = ful.getPropertyKeyValue("browser");
		String URL = ful.getPropertyKeyValue("url");
		String UN = ful.getPropertyKeyValue("adminusername");
		String PWD = ful.getPropertyKeyValue("adminpassword");
		//step-2:Read all the data from the excel sheet
		String grade = eul.getExcelData("Sheet1", 1, 2);
		String exam = eul.getExcelData("Sheet1", 1, 3);
		String days = eul.getExcelData("Sheet1", 1, 4);
		String subjects = eul.getExcelData("Sheet1", 1, 5);
		String classrooms = eul.getExcelData("Sheet1", 1, 6);
		String startTime = eul.getExcelData("Sheet1", 1, 7);
		String endTime = eul.getExcelData("Sheet1", 1, 8);
		//step-3:Launch the browser --> Runtime polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid Browser name");
		}
		wul.maximizeTheWindow(driver);
		wul.waitForElementInDom(driver);
		//step-4:Login to application
		driver.get(URL);
		driver.findElement(By.id("email")).sendKeys(UN);
		driver.findElement(By.id("password")).sendKeys(PWD);
		driver.findElement(By.id("btnSubmit")).click();
		//step-5:Navigate to Exam Link
		driver.findElement(By.xpath("//span[text()='Exam']")).click();
		driver.findElement(By.xpath("//a[text()=' Exam Timetable']")).click();
		WebElement gradeList = driver.findElement(By.id("grade"));
		wul.select(gradeList, grade);
		WebElement examList = driver.findElement(By.id("exam"));
		wul.select(examList, exam);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		//step-6:Create exam timetable with mandatory inputs
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-plus']")).click();
		WebElement dayList = driver.findElement(By.id("day"));
		wul.select(dayList, days);
		WebElement subjectList = driver.findElement(By.id("subject"));
		wul.select(subjectList, subjects);
		WebElement classroomList = driver.findElement(By.id("classroom"));
		wul.select(classroomList, classrooms);
		driver.findElement(By.id("start_time")).sendKeys(startTime);
		driver.findElement(By.id("end_time")).sendKeys(endTime);
		driver.findElement(By.id("btnSubmit")).click();
		//step-7:verify the exam timetable created or not
		driver.findElement(By.xpath("//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")).click();
		String actual = driver.findElement(By.xpath("//table[@id='example1']/descendant::td[contains(text(),'"+subjects+"')]")).getText();
		if(actual.contains(subjects))
		{
			System.out.println("Exam timetable created-->PASS");
		}
		else
		{
			System.out.println("Exam timetable not created-->FAIL");
		}
		//step-8:Logout from the application
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
	}

}
