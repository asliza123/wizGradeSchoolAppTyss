package com.wizGradeSchoolapp.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wizGradeSchoolapp.generic.WebdriverUtilityLibraries;

public class AddSubjectRoutingPage {
	WebDriver driver;
	@FindBy(id="grade")
	private WebElement grdDrpDwnBtn;
	@FindBy(id="subject")
	private WebElement subDrpDwnBtn;
	@FindBy(id="teacher")
	private WebElement teacherDrpDwnBtn;
	@FindBy(id="fee")
	private WebElement feeTxtBx;
	@FindBy(id="btnSubmit")
	private WebElement submitBtn;
	
	public AddSubjectRoutingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getGrdDrpDwnBtn() {
		return grdDrpDwnBtn;
	}

	public WebElement getSubDrpDwnBtn() {
		return subDrpDwnBtn;
	}

	public WebElement getTeacherDrpDwnBtn() {
		return teacherDrpDwnBtn;
	}

	public WebElement getFeeTxtBx() {
		return feeTxtBx;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	WebdriverUtilityLibraries wul=new WebdriverUtilityLibraries();
	public void createSubRouting(String grNm, String sNm, String tNm, String fees)
	{
		wul.select(grdDrpDwnBtn, grNm);
		wul.select(subDrpDwnBtn, sNm);
		wul.select(teacherDrpDwnBtn, tNm);
		feeTxtBx.sendKeys(fees);
		submitBtn.click();
	}

}
