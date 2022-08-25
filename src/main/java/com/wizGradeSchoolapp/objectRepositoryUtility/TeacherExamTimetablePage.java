package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherExamTimetablePage {
	WebDriver driver;
	@FindBy(id="exam")
	private WebElement exmDrpDwnBtn;
	
	public TeacherExamTimetablePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String verifyExam(String exmNm)
	{
		String actualEx= driver.findElement(By.xpath("//select[@id='exam']/option[text()='"+exmNm+"']")).getText();
		return actualEx;
	}

}
