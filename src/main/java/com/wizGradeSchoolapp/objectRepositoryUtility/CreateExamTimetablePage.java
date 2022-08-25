package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wizGradeSchoolapp.generic.ExcelUtilitityLibraries;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

public class CreateExamTimetablePage extends WebdriverUtilityLibraries{
	WebDriver driver;
	@FindBy(id="grade")
	private WebElement grdDrpDwnBtn;
	@FindBy(id="exam")
	private WebElement exmDrpDwnBtn;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement sbmtBtn;
	@FindBy(xpath="//span[@class='glyphicon glyphicon-plus']")
	private WebElement addBtn;
	@FindBy(id="day")
	private WebElement dayDrpDwnBtn;
	@FindBy(id="subject")
	private WebElement subDrpDwnBtn;
	@FindBy(id="classroom")
	private WebElement clsDrpDwnBtn;
	@FindBy(id="start_time")
	private WebElement startTmTexBx;
	@FindBy(id="end_time")
	private WebElement endTmTexBx;
	@FindBy(id="btnSubmit")
	private WebElement submitBtn;
	@FindBy(xpath="//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")
	private WebElement popupClsBtn;
	
	public CreateExamTimetablePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getGrdDrpDwnBtn() {
		return grdDrpDwnBtn;
	}
	public WebElement getExmDrpDwnBtn() {
		return exmDrpDwnBtn;
	}
	public WebElement getSbmtBtn() {
		return sbmtBtn;
	}
	public WebElement getAddBtn() {
		return addBtn;
	}
	public WebElement getDayDrpDwnBtn() {
		return dayDrpDwnBtn;
	}
	public WebElement getSubDrpDwnBtn() {
		return subDrpDwnBtn;
	}
	public WebElement getClsDrpDwnBtn() {
		return clsDrpDwnBtn;
	}
	public WebElement getStartTmTexBx() {
		return startTmTexBx;
	}
	public WebElement getEndTmTexBx() {
		return endTmTexBx;
	}
	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	public WebElement getpopupClsBtn() {
		return popupClsBtn;
	}
	
	public void selectDetails(String gdNm,String exmNm)
	{
		grdDrpDwnBtn.click();
		select(grdDrpDwnBtn, gdNm);
		exmDrpDwnBtn.click();
		select(exmDrpDwnBtn, exmNm);
		sbmtBtn.click();
	}
	
	public void createExmTT(String days,String subjects, String classrooms, String startTime, String endTime)
	{
		addBtn.click();
		select(dayDrpDwnBtn, days);
		select(subDrpDwnBtn, subjects);
		select(clsDrpDwnBtn, classrooms);
		startTmTexBx.sendKeys(startTime);
		endTmTexBx.sendKeys(endTime);
		submitBtn.click();
	}
	public String verifyText(String subject)
	{
		String actualSub= driver.findElement(By.xpath("//table[@id='example1']/descendant::td[contains(text(),'"+subject+"')]")).getText();
		return actualSub;
		
	}
	}
