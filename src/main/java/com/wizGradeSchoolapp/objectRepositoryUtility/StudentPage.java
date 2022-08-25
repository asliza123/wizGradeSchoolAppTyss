package com.wizGradeSchoolapp.objectRepositoryUtility;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

public class StudentPage extends WebdriverUtilityLibraries{
	WebDriver driver;
	@FindBy(id="index_number")
	private WebElement inNumTxBx;
	@FindBy(id="full_name")
	private WebElement fulNmTxBx;
	@FindBy(id="i_name")
	private WebElement iNmTxBx;
	@FindBy(id="address")
	private WebElement addTxBx;
	@FindBy(id="email")
	private WebElement mailTxBx;
	@FindBy(id="phone")
	private WebElement phnTxBx;
	@FindBy(id="b_date")
	private WebElement dobTxBx;
	@FindBy(id="gender")
	private WebElement gndrDrpDwnBtn;
	@FindBy(id="fileToUpload")
	private WebElement fileUpBtn;
	
	@FindBy(id="g_full_name")
	private WebElement grdFulNmTxBx;
	@FindBy(id="g_i_name")
	private WebElement grdINmTxBx;
	@FindBy(id="g_address")
	private WebElement grdAddTxBx;
	@FindBy(id="g_email")
	private WebElement grdMailTxBx;
	@FindBy(id="g_phone")
	private WebElement grdPhnTxBx;
	@FindBy(id="g_b_date")
	private WebElement grdDobTxBx;
	@FindBy(id="g_gender")
	private WebElement grdGendrDrpDwnBtn;
	@FindBy(id="g_fileToUpload")
	private WebElement grdFileUpBtn;
	@FindBy(id="btnSubmit")
	private WebElement submitBtn;
	@FindBy(id="grade")
	private WebElement grdDrpDwnBtn;
	@FindBy(xpath="//table[@class='table ']/tbody/tr/td[text()='Subject 2']/parent::tr/td/input[@id='checkbox']")
	private WebElement subchkBx;
	@FindBy(xpath="//table[@class='table ']/tbody/tr/td[text()='Subject 7 ']/parent::tr/td/input[@id='checkbox']")
	private WebElement subjchkBx;
	@FindBy(id="btnSubmit1")
	private WebElement smtBtn;
	@FindBy(xpath="//div[@class='msk-heading']/descendant::span[@class='glyphicon glyphicon-remove']")
	private WebElement msgCloseBtn;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement selectBtn;
	@FindBy(xpath="//input[@class='form-control input-sm']")
	private WebElement searchTxBx;

	public StudentPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getInNumTxBx() {
		return inNumTxBx;
	}
	public WebElement getFulNmTxBx() {
		return fulNmTxBx;
	}
	public WebElement getiNmTxBx() {
		return iNmTxBx;
	}
	public WebElement getAddTxBx() {
		return addTxBx;
	}
	public WebElement getMailTxBx() {
		return mailTxBx;
	}
	public WebElement getPhnTxBx() {
		return phnTxBx;
	}
	public WebElement getDobTxBx() {
		return dobTxBx;
	}
	public WebElement getGndrDrpDwnBtn() {
		return gndrDrpDwnBtn;
	}
	public WebElement getFileUpBtn() {
		return fileUpBtn;
	}
	public WebElement getGrdFulNmTxBx() {
		return grdFulNmTxBx;
	}
	public WebElement getGrdINmTxBx() {
		return grdINmTxBx;
	}
	public WebElement getGrdAddTxBx() {
		return grdAddTxBx;
	}
	public WebElement getGrdMailTxBx() {
		return grdMailTxBx;
	}
	public WebElement getGrdPhnTxBx() {
		return grdPhnTxBx;
	}
	public WebElement getGrdDobTxBx() {
		return grdDobTxBx;
	}
	public WebElement getGrdGendrDrpDwnBtn() {
		return grdGendrDrpDwnBtn;
	}
	public WebElement getGrdFileUpBtn() {
		return grdFileUpBtn;
	}
	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	public WebElement getGrdDrpDwnBtn() {
		return grdDrpDwnBtn;
	}

	public WebElement getSubchkBx() {
		return subchkBx;
	}

	public WebElement getSubjchkBx() {
		return subjchkBx;
	}

	public WebElement getSmtBtn() {
		return smtBtn;
	}

	public WebElement getMsgCloseBtn() {
		return msgCloseBtn;
	}

	public WebElement getSelectBtn() {
		return selectBtn;
	}

	public WebElement getSearchTxBx() {
		return searchTxBx;
	}
	
	public void studentDetails(String inNum, String flNm, String iName, String add, String mail, String phn, String dob, String gender, String file)
	{
		inNumTxBx.sendKeys(inNum);
		fulNmTxBx.sendKeys(flNm);
		iNmTxBx.sendKeys(iName);
		addTxBx.sendKeys(add);
		mailTxBx.sendKeys(mail);
		phnTxBx.sendKeys(phn);
		dobTxBx.sendKeys(dob);
		select(gndrDrpDwnBtn, gender);		
		File f1=new File(file);
		String AbsolutePath = f1.getAbsolutePath();
		fileUpBtn.sendKeys(AbsolutePath);
	}
	
	public void guardianDetails(String flNm, String iName, String add, String mail, String phn, String dob, String gender, String file)
	{
		grdFulNmTxBx.sendKeys(flNm);
		grdINmTxBx.sendKeys(iName);
		grdAddTxBx.sendKeys(add);
		grdMailTxBx.sendKeys(mail);
		grdPhnTxBx.sendKeys(phn);
		grdDobTxBx.sendKeys(dob);
		select(grdGendrDrpDwnBtn, gender);
		File f1=new File(file);
		String grdAbsolutePath = f1.getAbsolutePath();
		grdFileUpBtn.sendKeys(grdAbsolutePath);
	}
	
	public void selectGrade(String grdNm)
	{
		select(grdDrpDwnBtn, grdNm);
	}
	public void chooseSub()
	{
		waitForElementClickable(driver, subchkBx);
		subchkBx.click();
		waitForElementClickable(driver, subjchkBx);
		subjchkBx.click();
		smtBtn.click();
	}

	public String verifyStudentDetails(String stdNameWI)
	{
		String actual = driver.findElement(By.xpath("//table[@id='example1']/tbody/tr/td/a[text()='"+stdNameWI+"']")).getText();
		return actual;
	}

}
