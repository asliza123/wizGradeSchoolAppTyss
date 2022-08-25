package com.wizGradeSchoolapp.admin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.JavaUtilityLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.StudentPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddStudentAndVerifyOR {

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
		int random=jul.getRandomNumber();
		String stdIndx=exl.getExcelData("Sheet1", 11, 2)+random;
		String stdFullNm=exl.getExcelData("Sheet1", 11, 3);
		String stdNameWI=exl.getExcelData("Sheet1", 11, 4);
		String stdAdd = exl.getExcelData("Sheet1", 11, 5);
		String stdEml = random+exl.getExcelData("Sheet1", 11, 6);
		String stdPhn = exl.getExcelData("Sheet1", 11, 7);
		String stdDOB = exl.getExcelData("Sheet1", 11, 8);
		String stdGndr = exl.getExcelData("Sheet1", 11, 9);
		String grdFullNm= exl.getExcelData("Sheet1", 11, 10);
		String grdNameWI= exl.getExcelData("Sheet1", 11, 11);
		String grdAdd = exl.getExcelData("Sheet1", 11, 12);
		String grdEml = random+exl.getExcelData("Sheet1", 11, 13);
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
		
		//step-4: login as admin
		LoginPage lp=new LoginPage(driver);
		driver.get(URL);
		lp.loginToApp(UN, PWD);
		
		//step-5: Navigate to Student link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getStdLink().click();
		
		//step-6: Navigate to student preview page
		ahp.getAddStdLink();
		
		//step-7: Create a student
		StudentPage sp=new StudentPage(driver);
		sp.studentDetails(stdIndx, stdFullNm, stdNameWI, stdAdd, stdEml, stdPhn, stdDOB, stdGndr, stdPh);
		sp.guardianDetails(grdFullNm, grdNameWI, grdAdd, grdEml, grdPhn, grdDOB, grdGndr, grdPh);
		sp.getSubmitBtn().click();
				
		//step-8: select grade for student
		sp.selectGrade(grdNm);
		sp.chooseSub();
		sp.getMsgCloseBtn().click();
		
		//step-9: verify the created student
		ahp.getStdLink().click();
		ahp.getAllStdLink().click();
		sp.selectGrade(grdNm);
		sp.getSelectBtn().click();
		sp.getSearchTxBx().sendKeys(stdNameWI);
		String actual=sp.verifyStudentDetails(stdNameWI);
		if(stdNameWI.equals(actual)) {
			System.out.println("Student is created");
		}
		else
		{
			System.out.println("student is not created");
		}
		
		//step-10: logout from the application
		ahp.logoutFrmApp();
		driver.close();
	}

}
