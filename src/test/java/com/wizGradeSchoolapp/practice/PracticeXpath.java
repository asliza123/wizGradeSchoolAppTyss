package com.wizGradeSchoolapp.practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeXpath {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.icc-cricket.com/homepage");
		driver.findElement(By.xpath("//nav[@class='main-navigation__desktop-list js-desktop-nav']/descendant::button[@class='linked-list__dropdown-label js-dropdown-btn' and contains(text(),'Rankings')]")).click();
		driver.findElement(By.xpath("//nav[@class='main-navigation__desktop-list js-desktop-nav']/descendant::button[@class='linked-list__dropdown-label js-dropdown-btn' and contains(text(),'Rankings')]/following-sibling::ul[@class='linked-list__dropdown']//descendant::button[contains(text(),'Women')]/following-sibling::ul[@class='linked-list__dropdown']/descendant::a[contains(text(),'Team Rankings')]")).click();
		String actualTitle = driver.getTitle();
		Thread.sleep(6000);
		String expTitle = "ICC Women's ODI Team Ranking";
		if(actualTitle.contains(expTitle))
		{
			System.out.println("Right Page");
		}
		else {
			System.out.println("Wrong page");
		}
		/*driver.get("https://olympics.com/");
		int y = driver.findElement(By.xpath("//h2[contains(text(),'Featured')]")).getLocation().getY();
		JavascriptExecutor j=(JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,"+y+")");
		String gold = driver.findElement(By.xpath("//span[text()='Kaylee MCKEOWN']/ancestor::li/descendant::span[@class='result-medal result-medal--gold']")).getText();
		System.out.println(gold);
		*/
		/*driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//div/a[text()='Mobiles']")).click();
		int y = driver.findElement(By.xpath("//span[text()='Brands']")).getLocation().getY();
		JavascriptExecutor j=(JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,"+y+")");
		driver.findElement(By.xpath("//span[@class='a-size-base a-color-base' and text()='OnePlus']/ancestor::li/descendant::i")).click();
		*/
		/*driver.get("https://www.makemytrip.com/");
		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
		driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")).click();
		driver.findElement(By.xpath("//span[contains(@class,'bgProperties icon20 overlayCrossIcon')]")).click();
		driver.findElement(By.xpath("//p[text()='Popular Filters']/parent::div[@class='filtersOuter']/descendant::span[@class='filterName' and @title='SpiceJet']/ancestor::div[contains(@class,'makeFlex hrtlCenter flexOne gap')]/descendant::span[@class='box']/span")).click();
		*/
		driver.close();
	}

}
