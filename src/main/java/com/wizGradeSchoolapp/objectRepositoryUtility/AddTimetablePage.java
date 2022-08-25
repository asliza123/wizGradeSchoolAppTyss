package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

public class AddTimetablePage {
	WebDriver driver;
	@FindBy(id="grade")
	private WebElement grdDrpDwnList;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement subBtn;
	@FindBy(xpath="//a[@class='btn btn-success btn-sm pull-right']//span[@class='glyphicon glyphicon-plus']")
	private WebElement addBtn;
	@FindBy(id="day")
	private WebElement dayDrpDwnBtn;
	@FindBy(id="subject")
	private WebElement subDrpDwnBtn;
	@FindBy(id="teacher")
	private WebElement teacherDrpDwnBtn;
	@FindBy(id="classroom")
	private WebElement clsRoomDrpDwnBtn;
	@FindBy(id="start_time")
	private WebElement startTimeTxBx;
	@FindBy(id="end_time")
	private WebElement endTimeTxBx;
	@FindBy(id="btnSubmit")
	private WebElement submitBtn;
	@FindBy(xpath="//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")
	private WebElement rmvMsgBtn;
	
	public AddTimetablePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getGrdDrpDwnList() {
		return grdDrpDwnList;
	}

	public WebElement getSubBtn() {
		return subBtn;
	}

	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getDayDrpDwnBtn() {
		return dayDrpDwnBtn;
	}

	public WebElement getSubRoomDrpDwnBtn() {
		return subDrpDwnBtn;
	}

	public WebElement getTeacherDrpDwnBtn() {
		return teacherDrpDwnBtn;
	}

	public WebElement getClsRoomDrpDwnBtn() {
		return clsRoomDrpDwnBtn;
	}

	public WebElement getStartTimeTxBx() {
		return startTimeTxBx;
	}

	public WebElement getEndTimeTxBx() {
		return endTimeTxBx;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getRmvMsgBtn() {
		return rmvMsgBtn;
	}
	WebdriverUtilityLibraries wLib=new WebdriverUtilityLibraries();
	public void selectGrade(String grd)
	{
		WebElement gradeList = driver.findElement(By.id("grade"));
		wLib.select(gradeList, grd);
		subBtn.click();
	}
	public void addTimetable(String dayNm, String subNm, String teNm, String clsNm, String startTm, String endTm)
	{
		wLib.select(dayDrpDwnBtn, dayNm);
		
		wLib.select(subDrpDwnBtn, subNm);
		
		wLib.select(teacherDrpDwnBtn, teNm);
		
		wLib.select(clsRoomDrpDwnBtn, clsNm);
		
		startTimeTxBx.sendKeys(startTm);
		endTimeTxBx.sendKeys(endTm);
		submitBtn.click();
	}
}
