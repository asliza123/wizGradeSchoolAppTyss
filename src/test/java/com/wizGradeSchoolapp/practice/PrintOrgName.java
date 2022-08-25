package com.wizGradeSchoolapp.practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintOrgName {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://rmgtestingserver:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		String expectOrg = "asliza";
		int rowCount=2;
		 List<WebElement> orgName = driver.findElements(By.xpath("//table[@class='lvt small']/descendant::a[@title='Organizations']"));
		for(int i=0;i<orgName.size();i++)
		{
			rowCount++;
			String name = orgName.get(i).getText();
			System.out.println(name);
			if(name.equals(expectOrg))
			{
				driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr["+rowCount+"]/td/input[@type='checkbox']")).click();
				driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr["+rowCount+"]/td/a[text()='del']")).click();
				driver.switchTo().alert().accept();
			}
		}
		driver.close();
	}
	}


