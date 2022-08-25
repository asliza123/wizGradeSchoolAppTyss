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

public class CreateExamTest {

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
		//step-2:Read all the data from excel sheet
		FileInputStream fisEx=new FileInputStream(".\\src\\test\\resources\\Data\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fisEx);
		String exName = wb.getSheet("Sheet1").getRow(2).getCell(2).getStringCellValue();
		//step-3:Launch the browser-->Runtime Polymorphism
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
			System.out.println("Invalid Browser");
		}
		driver.get(URL);
		//step-4:Login to application
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys(UN);
		driver.findElement(By.id("password")).sendKeys(PW);
		driver.findElement(By.id("btnSubmit")).click();
		//step-5:Navigate to exam
		driver.findElement(By.xpath("//span[text()='Exam']")).click();
		driver.findElement(By.linkText("Create Exam")).click();
		Random r=new Random();
		int ran = r.nextInt(500);
		//step-6:Add new exam name
		driver.findElement(By.xpath("//a[text()='Add ']")).click();
		driver.findElement(By.id("name")).sendKeys(exName+ran);
		//step-7:Save the exam
		driver.findElement(By.id("btnSubmit")).click();
		//step-8:Logout from application
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Ingenious Developer1']")).click();
		driver.findElement(By.linkText("Sign out")).click();
		//step-9:close the browser
		driver.close();
	}

}
