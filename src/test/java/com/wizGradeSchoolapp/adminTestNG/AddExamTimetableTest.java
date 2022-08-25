package com.wizGradeSchoolapp.adminTestNG;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.wizGradeSchoolapp.genericLibraries.BaseClass;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.CreateExamTimetablePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
@Listeners(com.wizGradeSchoolapp.genericLibraries.ListnerImplementation.class)

public class AddExamTimetableTest extends BaseClass{
	@Test
	public void createExamTimetableTest() throws IOException {
		//step-1:Read all the common data
		String UN = fLib.getPropertyKeyValue("adminusername");
		String PWD = fLib.getPropertyKeyValue("adminpassword");
		//step-2:Read all the data from the excel sheet
		String grade = eLib.getExcelData("Sheet1", 1, 2);
		String exam = eLib.getExcelData("Sheet1", 1, 3);
		String days = eLib.getExcelData("Sheet1", 1, 4);
		String subjects = eLib.getExcelData("Sheet1", 1, 5);
		String classrooms = eLib.getExcelData("Sheet1", 1, 6);
		String startTime = eLib.getExcelData("Sheet1", 1, 7);
		String endTime = eLib.getExcelData("Sheet1", 1, 8);
		
		//step-4:Login to application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(UN, PWD);

		//step-5:Navigate to Exam Link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getExmLink().click();
		ahp.getExamTimTbl().click();
		CreateExamTimetablePage cet=new CreateExamTimetablePage(driver);
		cet.selectDetails(grade, exam);
		
		//step-6:Create exam timetable with mandatory inputs
		cet.createExmTT(days, subjects, classrooms, startTime, endTime);
		cet.getpopupClsBtn().click();
		
		//step-7:verify the exam timetable created or not
		//String actual = driver.findElement(By.xpath("//table[@id='example1']/descendant::td[contains(text(),'"+subjects+"')]")).getText();
		String actual = cet.verifyText(subjects);
		Assert.assertEquals(actual, subjects);
		System.out.println("exam timetable created");
//		if(actual.contains(subjects))
//		{
//			System.out.println("Exam timetable created-->PASS");
//		}
//		else
//		{
//			System.out.println("Exam timetable not created-->FAIL");
//		}
	}
}
