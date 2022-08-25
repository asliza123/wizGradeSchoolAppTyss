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

public class SearchAStudentByNameOrIdInAllStudentPage {

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
		String grNm = eul.getExcelData("Sheet1", 7, 2);
		String sNm = eul.getExcelData("Sheet1", 7, 3);
		
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
		
		//step-4:Login to application
		driver.findElement(By.id("email")).sendKeys(AUN);
		driver.findElement(By.id("password")).sendKeys(APWD);
		driver.findElement(By.id("btnSubmit")).click();

		//step-5:Navigate to Student Link
		driver.findElement(By.xpath("//span[text()='Student']")).click();
		driver.findElement(By.xpath("//a[text()=' All Student']")).click();
		
		//step-6:search student with valid mandatory inputs
		WebElement gradeList = driver.findElement(By.id("grade"));
		wul.select(gradeList, grNm);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		driver.findElement(By.xpath("//input[@class='form-control input-sm']")).sendKeys(sNm);
		String actualResult = driver.findElement(By.xpath("//table[@id='example1']/tbody/tr/td/a[text()='"+sNm+"']")).getText();
		if(sNm.equals(actualResult)) {
			System.out.println("Test Pass");
		}
		else
		{
			System.out.println("Test Fail");
		}
		//step-7:Logout from the application
		driver.findElement(By.xpath("//span[@class='hidden-xs']")).click();
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		driver.close();
	}

}
