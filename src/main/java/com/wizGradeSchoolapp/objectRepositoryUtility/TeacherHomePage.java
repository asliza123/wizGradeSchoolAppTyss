package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherHomePage {
	WebDriver driver;
	@FindBy(xpath="//span[text()='Exam']")
	private WebElement exmLink;
	@FindBy(xpath="//a[text()=' My Student Exam Marks']")
	private WebElement myStdExmMrks;
	@FindBy(xpath="//a[text()=' My Student Exam Marks History']")
	private WebElement myStdExmMrksHistroy;
	@FindBy(xpath="//a[text()='Exam Timetable']")
	private WebElement examTimTbl;
	@FindBy(xpath = "//span[@class='hidden-xs']")
	private WebElement logoutlnk;
	@FindBy (xpath="//a[text()='Sign out']")
	private WebElement logoutBtn;
	@FindBy (xpath="//span[text()='Timetable']")
	private WebElement TimetablDrpDwnBtn;
	@FindBy (xpath="//a[text()='All Timetable']")
	private WebElement allTTLink;
	
	public TeacherHomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getExmLink() {
		return exmLink;
	}

	public WebElement getMyStdExmMrks() {
		return myStdExmMrks;
	}

	public WebElement getMyStdExmMrksHistroy() {
		return myStdExmMrksHistroy;
	}

	public WebElement getExamTimTbl() {
		return examTimTbl;
	}

	public WebElement getLogoutlnk() {
		return logoutlnk;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}

	public WebElement getTimetablDrpDwnBtn() {
		return TimetablDrpDwnBtn;
	}

	public WebElement getAllTTLink() {
		return allTTLink;
	}
	
	

}
