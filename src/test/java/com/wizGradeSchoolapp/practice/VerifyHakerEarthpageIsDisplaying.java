package com.wizGradeSchoolapp.practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyHakerEarthpageIsDisplaying {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.vtiger.com/");
		Actions a=new Actions(driver);
		WebElement resource = driver.findElement(By.xpath("//a[contains(text(),'Resources')]"));
		a.moveToElement(resource).perform();
		driver.findElement(By.partialLinkText("Customers")).click();
		
		driver.manage().window().maximize();
		WebElement readStory = driver.findElement(By.linkText("READ FULL STORY"));
		a.doubleClick(readStory).perform();
		String t1 = driver.getTitle();
		String t2 = "HakerEarth Case Study";
		if(t1.contains(t2))
		{
			System.out.println("you are on the right page");
		}
		else
		{
			System.out.println("you are on the wrong page");
		}
		driver.quit();
	}

}
