package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddExamPage {
	WebDriver driver;
	@FindBy(xpath="//span[@class='glyphicon glyphicon-plus']")
	private WebElement addBtn;
	@FindBy(id="name")
	private WebElement exmNmTxBx;
	@FindBy(id="btnSubmit")
	private WebElement submitBtn;
	@FindBy(xpath="//div[@id='insert_Success']/descendant::span[@class='glyphicon glyphicon-remove']")
	private WebElement msgCloseBtn;
	
	public AddExamPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getExmNmTxBx() {
		return exmNmTxBx;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getMsgCloseBtn() {
		return msgCloseBtn;
	}
	
	public void createExam(String exmNm)
	{
		addBtn.click();
		exmNmTxBx.sendKeys(exmNm);
		submitBtn.click();
	}

}
