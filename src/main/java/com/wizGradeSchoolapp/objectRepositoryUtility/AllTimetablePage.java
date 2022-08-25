package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

public class AllTimetablePage {
	WebDriver driver;
	
	@FindBy(id="grade")
	private WebElement grdDrpDwnBtn;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement submBtn;
	
	public AllTimetablePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getGrdDrpDwnBtn() {
		return grdDrpDwnBtn;
	}

	public WebElement getSubmBtn() {
		return submBtn;
	}
	public WebdriverUtilityLibraries wLib=new WebdriverUtilityLibraries();
	public void selectGrd(String grd)
	{
		wLib.select(grdDrpDwnBtn, grd);
		submBtn.click();
	}

	public String verifyTimeTable(String subNm)
	{
		String actualSub = driver.findElement(By.xpath("//table[@id='example1']/descendant::td[contains(text(),'"+subNm+"')]")).getText();
		return actualSub;
	}

}
