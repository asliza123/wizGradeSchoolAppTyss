package com.wizGradeSchoolapp.admin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;
import com.wizGradeSchoolapp.objectRepositoryUtility.AddSubjectRoutingPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.SubjectRoutingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSubjectRoutingOR {

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
		
		//step-2:Read all the data from the excel sheet
		String grNm = eul.getExcelData("Sheet1", 9, 2);
		String sNm = eul.getExcelData("Sheet1", 9, 3);
		String tNm = eul.getExcelData("Sheet1", 9, 4);
		String fees = eul.getExcelData("Sheet1", 9, 5);
		
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
		
		//step-5:Navigate to Subject Routing Link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getSubRoutLnk().click();
		
		//step-6:Create subject Routing with mandatory inputs
		SubjectRoutingPage srp=new SubjectRoutingPage(driver);
		srp.getAddBtn().click();
		
		AddSubjectRoutingPage asrp=new AddSubjectRoutingPage(driver);
		asrp.createSubRouting(grNm,sNm,tNm,fees);
		srp.getMsgCloseBtn().click();
		srp.getSearchTxBx().sendKeys(sNm);
		String actCheck = srp.verifySubRoutCrtOrNot(sNm, grNm);
		if(grNm.equals(actCheck)) {
			System.out.println("verified-->PASS");
		}
		else
		{
			System.out.println("verified-->FAIL");
		}
		//step-7:Logout from the application
		ahp.logoutFrmApp();
		driver.close();
	}

}
