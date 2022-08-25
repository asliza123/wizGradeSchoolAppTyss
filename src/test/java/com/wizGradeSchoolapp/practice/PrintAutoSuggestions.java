package com.wizGradeSchoolapp.practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintAutoSuggestions {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.name("q")).sendKeys("iphone");
		List<WebElement> allSugg = driver.findElements(By.xpath("//span[contains(text(),'iphone')]"));
		int count = allSugg.size();
		for(int i=0;i<count;i++)
		{
			String text = allSugg.get(i).getText();
			if(i==(count-1))
			{
				allSugg.get(i).click();
			}
		}
		driver.close();
	}

}
