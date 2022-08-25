package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClassroomPage {
	WebDriver driver;
	@FindBy(id="name")
	private WebElement clsNameTxBx;
	@FindBy(id="student_count")
	private WebElement clsCountTxBx;
	@FindBy(id="btnSubmit")
	private WebElement submitBtn;
	@FindBy(xpath="//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")
	private WebElement msgRemoveBtn;
	public WebElement getClsNameTxBx() {
		return clsNameTxBx;
	}
	public WebElement getClsCountTxBx() {
		return clsCountTxBx;
	}
	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	public WebElement getMsgRemoveBtn() {
		return msgRemoveBtn;
	}
	
	public ClassroomPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createClsRoom(String clsNm, String clsCount) {
		clsNameTxBx.sendKeys(clsNm);
		clsCountTxBx.sendKeys(clsCount);
		submitBtn.click();
	}

}
