package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {
	WebDriver driver;
	@FindBy(xpath="//span[text()='Exam']")
	private WebElement exmLink;
	@FindBy(xpath="//a[text()='Create Exam']")
	private WebElement crtExm;
	@FindBy(xpath="//a[text()=' Exam Timetable']")
	private WebElement examTimTbl;
	@FindBy(xpath="//a[text()='Student Exam Marks']")
	private WebElement stdExmMrk;
	@FindBy(xpath="//a[text()='Student Exam Marks History']")
	private WebElement stdExmMrksHistroy;
	@FindBy(xpath = "//span[@class='hidden-xs']")
	private WebElement logoutlnk;
	@FindBy (xpath="//a[text()='Sign out']")
	private WebElement logoutBtn;
	@FindBy(xpath="//span[text()='Student']")
	private WebElement stdLink;
	@FindBy(xpath="//a[text()=' Add Student']")
	private WebElement addStdLink;
	@FindBy(xpath="//a[text()=' All Student']")
	private WebElement allStdLink;
	@FindBy(xpath="//a[text()=' Leave Student']")
	private WebElement leaveStdLink;
	@FindBy(xpath="//span[text()='Subject Routing']")
	private WebElement subRoutLnk;
	@FindBy (xpath="//span[text()='Classroom']")
	private WebElement clsRoomLink;
	@FindBy(xpath="//span[text()='Timetable']")
	private WebElement timetanleLink;
	
	public AdminHomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getExmLink() {
		return exmLink;
	}
	public WebElement getCrtExm() {
		return crtExm;
	}
	public WebElement getExamTimTbl() {
		return examTimTbl;
	}
	public WebElement getStdExmMrk() {
		return stdExmMrk;
	}
	public WebElement getStdExmMrksHistroy() {
		return stdExmMrksHistroy;
	}
	public WebElement getLogoutlnk() {
		return logoutlnk;
	}
	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	public WebElement getStdLink() {
		return stdLink;
	}
	public WebElement getAddStdLink() {
		return addStdLink;
	}
	public WebElement getAllStdLink() {
		return allStdLink;
	}
	public WebElement getLeaveStdLink() {
		return leaveStdLink;
	}

	public WebElement getSubRoutLnk() {
		return subRoutLnk;
	}
	
	public WebElement getClsRoomLink() {
		return clsRoomLink;
	}
	public WebElement getTimetanleLink() {
		return timetanleLink;
	}
	public void logoutFrmApp()
	{
		logoutlnk.click();
		logoutBtn.click();
	}
	
}
