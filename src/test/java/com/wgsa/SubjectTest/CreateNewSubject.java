package com.wgsa.SubjectTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewSubject {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver=null;
		//step-1:Read all the common data
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Data\\commondata.properties");
		Properties p=new Properties();
		p.load(fis);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String UN = p.getProperty("username");
		String PW = p.getProperty("password");
		//step-2:Read all the data from the excel sheet
		FileInputStream fisExcel=new FileInputStream(".\\src\\test\\resources\\Data\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fisExcel);
		String subName = wb.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue();
		//step-3:Launch the browser --> Runtime polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		//step-4:Login to application
		driver.findElement(By.id("email")).sendKeys(UN);
		driver.findElement(By.id("password")).sendKeys(PW);
		driver.findElement(By.id("btnSubmit")).click();
		//step-5:Navigate to Subject Link
		driver.findElement(By.xpath("//span[text()='Subject']")).click();
		Random ran = new Random();
		int r = ran.nextInt(500);
		//step-6:Create subjects with mandatory inputs
		driver.findElement(By.id("name")).sendKeys(subName + r);
		//step-7:Save the subject
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		//step-8:Logout from the application
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Ingenious Developer1']")).click();
		driver.findElement(By.linkText("Sign out")).click();
		driver.close();
	}

}
