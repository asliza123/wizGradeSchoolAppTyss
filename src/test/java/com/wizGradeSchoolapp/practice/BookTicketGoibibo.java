package com.wizGradeSchoolapp.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookTicketGoibibo {

	public static void main(String[] args) throws AWTException {
		int date=28;
		String monthAndYear="October 2022";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		Actions a=new Actions(driver);
		a.moveByOffset(10, 10).click().perform();
		driver.findElement(By.xpath("//span[text()='From']/following-sibling::p[text()='Enter city or airport']")).click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("blr");
		driver.findElement(By.xpath("//span[@class='autoCompleteTitle ' and contains(text(),'Bengaluru, India')]")).click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("bbi");
		driver.findElement(By.xpath("//span[@class='autoCompleteTitle ' and contains(text(),'Bhubaneswar, India')]")).click();
		//driver.findElement(By.xpath("//div[@class='sc-lbhJGD bHgpcs']/descendant::div[text()='August 2022']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='31']")).click();
		/*WebElement nxtBtn = driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']"));
		nxtBtn.click();
		nxtBtn.click();
		nxtBtn.click();
		nxtBtn.click();
		nxtBtn.click();*/
		String arrowXpath="//span[@class='DayPicker-NavButton DayPicker-NavButton--next']";
		String dateXpath="//div[text()='"+monthAndYear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']";
		for(;;)
		{
			try
			{
				driver.findElement(By.xpath(dateXpath)).click();
				break;
			}
			catch(Exception e) {
				driver.findElement(By.xpath(arrowXpath)).click();
			}
		}
		//driver.findElement(By.xpath("//div[text()='October 2022']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='31']")).click();
		//driver.findElement(By.xpath("//div[text()='February 2023']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='28']")).click();
		driver.findElement(By.xpath("//span[@class='fswTrvl__done']")).click();
		driver.findElement(By.xpath("//a[@class='sc-dtMgUX daltrV']")).click();
		driver.findElement(By.xpath("//span[@class='sc-fHeRUh jHgPBA']")).click();
		int y = driver.findElement(By.xpath("//div[text()='Preferred Airlines']")).getLocation().getY();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,"+y+")");
		driver.findElement(By.xpath("//div[@class='flexCol']/descendant::span[text()='IndiGo']/../preceding-sibling::span/*[name()='svg']/*[name()='path']")).click();
		//driver.findElement(By.xpath("(//button[text()='VIEW FARES'])[1]")).click();
		//driver.findElement(By.xpath("//div[text()='Stops']"));
	}

}
