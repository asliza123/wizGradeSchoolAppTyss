package com.wizGradeSchoolapp.genericLibraries;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.wizGradeSchoolapp.generic.DataBAseUtility;
import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.FileUtilittyLibraries;
import com.wizGradeSchoolapp.generic.JavaUtilityLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver sdriver;
	public DataBAseUtility dLib=new DataBAseUtility();
	public ExcelUtilitityLibraries eLib=new ExcelUtilitityLibraries();
	public FileUtilittyLibraries fLib=new FileUtilittyLibraries();
	public JavaUtilityLibraries jLib=new JavaUtilityLibraries();
	public WebdriverUtilityLibraries wLib=new WebdriverUtilityLibraries();
	
	@BeforeSuite
	public void createDBConnection()
	{
		System.out.println("suite started");
	}
	@AfterSuite
	public void closeDBConnection()
	{
		System.out.println("suite ended");
	}
	//Read all the common data
	
	//@Parameters("BROWSER")
	
	@BeforeClass
	public void launchBrowser() throws IOException 
	{
		System.out.println("class started");
		String BROWSER = fLib.getPropertyKeyValue("browser");
		//Launch the browser --> Runtime polymorphism
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
		sdriver=driver;
		wLib.maximizeTheWindow(driver);
		wLib.waitForElementInDom(driver);


	}
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("class ended");
		driver.close();
	}
	@BeforeMethod
	public void loginToApplication() throws IOException
	{
		String URL = fLib.getPropertyKeyValue("url");
		driver.get(URL);
		
	}
	@AfterMethod
	public void logoutFromApplication()
	{
		System.out.println("method ended");
		AdminHomePage hp=new AdminHomePage(driver);
		hp.logoutFrmApp();
		
	}
	

}
