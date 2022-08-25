package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubjectRoutingPage {
	WebDriver driver;
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-plus']")
	private WebElement addBtn;
	@FindBy(xpath="//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")
	private WebElement msgCloseBtn;
	@FindBy(xpath="//input[@class='form-control input-sm']")
	private WebElement searchTxBx;
	
	public SubjectRoutingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getAddBtn() {
		return addBtn;
	}



	public WebElement getMsgCloseBtn() {
		return msgCloseBtn;
	}



	public WebElement getSearchTxBx() {
		return searchTxBx;
	}



	public String verifySubRoutCrtOrNot(String sNm,String grNm)
	{
		String actCheck = driver.findElement(By.xpath("//table[@id='example1']/tbody/tr/td[text()='"+sNm+" ']/../td[text()='"+grNm+"']")).getText();
		return actCheck;
	}

}
