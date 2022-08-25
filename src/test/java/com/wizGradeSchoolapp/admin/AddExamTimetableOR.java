package com.wizGradeSchoolapp.admin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.CreateExamTimetablePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddExamTimetableOR {

	public static void main(String[] args) throws IOException {
		WebDriver driver = null;
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
		LoginPage lp=new LoginPage(driver);
		driver.get(URL);
		lp.loginToApp(UN, PWD);

		//step-5:Navigate to Exam Link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getExmLink().click();
		ahp.getExamTimTbl().click();
		CreateExamTimetablePage cet=new CreateExamTimetablePage(driver);
		cet.selectDetails(grade, exam);
		
		//step-6:Create exam timetable with mandatory inputs
		cet.createExmTT(days, subjects, classrooms, startTime, endTime);
		cet.getpopupClsBtn().click();
		
		//step-7:verify the exam timetable created or not
		//String actual = driver.findElement(By.xpath("//table[@id='example1']/descendant::td[contains(text(),'"+subjects+"')]")).getText();
		String actual = cet.verifyText(subjects);
		if(actual.contains(subjects))
		{
			System.out.println("Exam timetable created-->PASS");
		}
		else
		{
			System.out.println("Exam timetable not created-->FAIL");
		}
		//step-8:Logout from the application
		ahp.logoutFrmApp();		
	}

}
