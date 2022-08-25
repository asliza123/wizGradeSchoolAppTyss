package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class mySonsExamPage {
	WebDriver driver;
	@FindBy(id="exam")
	private WebElement exmDrpDwnBtn;
	
	public mySonsExamPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getExmDrpDwnBtn() {
		return exmDrpDwnBtn;
	}
	
	public String verifyExam(String exmNm)
	{
		String actualExm = driver.findElement(By.xpath("//select[@id='exam']/option[text()='"+exmNm+"']")).getText();
		return actualExm;
	}
}
