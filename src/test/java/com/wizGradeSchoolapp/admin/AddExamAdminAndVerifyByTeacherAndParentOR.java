package com.wizGradeSchoolapp.admin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;
import com.wizGradeSchoolapp.objectRepositoryUtility.AddExamPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.ParentHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.TeacherExamTimetablePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.TeacherHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.mySonsExamPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddExamAdminAndVerifyByTeacherAndParentOR {

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
		LoginPage lp=new LoginPage(driver);
		driver.get(URL);
		lp.loginToApp(AUN, APWD);
		
		//step-5:Navigate to Exam Link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getExmLink().click();
		ahp.getCrtExm().click();
		
		//step-6:Create exam with mandatory inputs
		AddExamPage aep=new AddExamPage(driver);
		aep.createExam(examName);
		aep.getMsgCloseBtn().click();
		//step-7: Admin Logout from the application
		ahp.logoutFrmApp();
		
		//step-8:verify with teacher 
		lp.getUnTbx().sendKeys(TUN);
		lp.getPwdTbx().sendKeys(TPWD);
		lp.getLgBtn().click();
		
		TeacherHomePage thp=new TeacherHomePage(driver);
		thp.getExmLink().click();
		thp.getExamTimTbl().click();
		TeacherExamTimetablePage tettp=new TeacherExamTimetablePage(driver);
		String actualEx=tettp.verifyExam(examName);
		System.out.println(actualEx);
		if(actualEx.equals(examName))
		{
			System.out.println("Exam "+examName+" created");
		}
		else
		{
			System.out.println("Exam "+examName+" not created");
		}
		//step-9: Teacher Logout from the application
		thp.getLogoutlnk().click();
		thp.getLogoutBtn().click();

		//step-10:verify with parent module
		lp.getUnTbx().sendKeys(PUN);
		lp.getPwdTbx().sendKeys(PPWD);
		lp.getLgBtn().click();
		
		ParentHomePage php=new ParentHomePage(driver);
		php.getExmLink().click();
		php.getMySonsExmTT().click();
		
		mySonsExamPage msep=new mySonsExamPage(driver);
		
		String actualExm = msep.verifyExam(examName);
		
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
		php.getLogoutlnk().click();
		php.getLogoutBtn().click();
	}

}
