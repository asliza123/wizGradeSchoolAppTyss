package com.wizGradeSchoolapp.generic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClassPractice {
	public WebDriver driver;
	public static WebDriver stdriver;
	
	@BeforeSuite
	public void createDBConnection()
	{
		System.out.println("suite started");
	}
	
//	@Parameters("BROWSER")
	FileUtilittyLibraries fLib=new FileUtilittyLibraries();
	@BeforeClass
	public void launchBrowser() throws IOException//String BROWSER) 
	{
		System.out.println("class started");
		String BROWSER=fLib.getPropertyKeyValue("browser");
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
		stdriver=driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void loginToApplication() throws IOException
	{
		System.out.println("method started");
		driver.get("https://www.facebook.com/");
	}
	@AfterMethod
	public void logoutFromApplication()
	{
		System.out.println("method ended");		
	}
	
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("class ended");
		driver.close();
	}
	
	@AfterSuite
	public void closeDBConnection()
	{
		System.out.println("suite ended");
	}

}
