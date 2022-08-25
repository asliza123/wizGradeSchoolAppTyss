package com.wizGradeSchoolapp.generic;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * contains all reusable Actions of WebDriver
 * @author Asmita
 *
 */
public class WebdriverUtilityLibraries {
	/**
	 * it's for maximizing the browser
	 * @param driver 
	 */
	public void maximizeTheWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * it's an implicitly wait always wait for element in DOM document and release the control if element available
	 * @param driver
	 */
	public void waitForElementInDom(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	/**
	 * it's an explicitly wait always wait for page to be loaded and available in GUI
	 * @param driver
	 * @param partailPageURL
	 */
	public void waitForPage(WebDriver driver, String partailPageURL)
	{
		WebDriverWait wait=new WebDriverWait(driver, IConstants.Explicitly_TIMEOUT);
		wait.until(ExpectedConditions.urlContains(partailPageURL));
	}
	/**
	 * it's an explicitly wait always wait for page to be loaded and available in GUI
	 * @param driver
	 * @param partailPageURL
	 */
	public void waitForPage(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, IConstants.Explicitly_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * it's an explicitly wait always wait for page to be loaded and clickable in GUI
	 * @param driver
	 * @param 
	 */
	public void waitForElementClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, IConstants.Explicitly_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * used to switch to any window based on window title
	 * @param driver
	 */
	public void switchToWindow(WebDriver driver, String partialWindowTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String wID = it.next();
			driver.switchTo().window(wID);
			String currentWindowTitle=driver.getTitle();
	if(currentWindowTitle.contains(partialWindowTitle))
	{
		break;
	}
		}
	}
	
	/**
	 * used to switch to alert window & click on ok button
	 * @param driver
	 */
	public void switchToAlertWindowAndAccept(WebDriver driver, String expectedMsg){
		  Alert alt = driver.switchTo().alert();
		  if(alt.getText().trim().equalsIgnoreCase(expectedMsg.trim()))
		  {
			  System.out.println("Alert message is verified");
		  }else {
			  System.out.println("Alert message is not verified");
		  }
		alt.accept();
	}
	

	/**
	 * used to switch to alert window & click on cancel button
	 * @param driver
	 */
	public void switchToAlertWindowAndCancel(WebDriver driver, String expectedMsg){
		  Alert alt = driver.switchTo().alert();
		  if(alt.getText().equals(expectedMsg))
		  {
			  System.out.println("Alert message is verified");
		  }else {
			  System.out.println("Alert message is not verified");
		  }
		alt.dismiss();
	}
	/**
	 * used switch to frame window based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * used switch to frame window based on id or name or attribute
	 * @param driver
	 * @param id_name_attribute
	 */
	public void switchToFrame(WebDriver driver, String id_name_attribue)
	{
		driver.switchTo().frame(id_name_attribue);
	}
	/**
	 * used to select the value from the drop down based on index
	 * @param driver
	 * @param index
	 */
	public void select(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * used to select the value from the drop down based on value/option available in GUI
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	/**
	 * used to place mouse cursor on specified element
	 * @param driver
	 * @param element 
	 */
	public void mouseOverOnElement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * used to right click on specified element
	 * @param driver
	 * @param element 
	 */
	public void rightClickOnElement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	/**
	 * @param driver
	 * @param javaScript
	 */
	public void executeJavaScript(WebDriver driver, String JavaScript) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeAsyncScript(JavaScript, null);
	}
	public void waitAndClick(WebElement element) throws InterruptedException
	{
		int count=0;
		while(count<20) {
			try {
				element.click();
				break;
			}
			catch(Throwable e) {
				Thread.sleep(1000);
				count++;
			}
		}
	}
	/**
	 * pass enter key appertain in to Browser
	 * @param driver
	 */
	public void passEnterKey(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
}
