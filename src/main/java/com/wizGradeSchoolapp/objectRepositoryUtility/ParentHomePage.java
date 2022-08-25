package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParentHomePage {
	WebDriver driver;
	@FindBy(xpath="//span[text()='Exam']")
	private WebElement exmLink;
	@FindBy(xpath="//a[contains(text(),'s Exam Marks')]")
	private WebElement mySonsExmMrk;
	@FindBy(xpath="//a[contains(text(),'s  Exam Marks History')]")
	private WebElement mySonsExmMrkHistory;
	@FindBy(xpath="//a[contains(text(),'s Exam Timetable')]")
	private WebElement mySonsExmTT;
	@FindBy(xpath = "//span[@class='hidden-xs']")
	private WebElement logoutlnk;
	@FindBy (xpath="//a[text()='Sign out']")
	private WebElement logoutBtn;
	
	public ParentHomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getExmLink() {
		return exmLink;
	}

	public WebElement getMySonsExmMrk() {
		return mySonsExmMrk;
	}

	public WebElement getMySonsExmMrkHistory() {
		return mySonsExmMrkHistory;
	}

	public WebElement getMySonsExmTT() {
		return mySonsExmTT;
	}

	public WebElement getLogoutlnk() {
		return logoutlnk;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	
	

}
