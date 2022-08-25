package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id="email")
	private WebElement unTbx;
	@FindBy(id="password")
	private WebElement pwdTbx;
	@FindBy(id="btnSubmit")
	private WebElement lgBtn;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	public WebElement getUnTbx() {
		return unTbx;
	}

	public WebElement getPwdTbx() {
		return pwdTbx;
	}

	public WebElement getLgBtn() {
		return lgBtn;
	}
	
	public void loginToApp(String un,String pwd)
	{
		unTbx.sendKeys(un);
		pwdTbx.sendKeys(pwd);
		lgBtn.click();
	}
	

}
