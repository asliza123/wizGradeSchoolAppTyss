package com.wizGradeSchoolapp.adminTestNG;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.wizGradeSchoolapp.genericLibraries.BaseClass;
import com.wizGradeSchoolapp.objectRepositoryUtility.AddExamPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.ParentHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.TeacherExamTimetablePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.TeacherHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.mySonsExamPage;

@Listeners(com.wizGradeSchoolapp.genericLibraries.ListnerImplementation.class)
public class AddExamAndVeifyByTeacherAndParentTest extends BaseClass{

	@Test
	public void creatExamTest() throws IOException {
		//step-1: read the common data
		String AUN = fLib.getPropertyKeyValue("adminusername");
		String APWD = fLib.getPropertyKeyValue("adminpassword");
		String TUN = fLib.getPropertyKeyValue("teacherusername");
		String TPWD = fLib.getPropertyKeyValue("teacherpassword");
		String PUN = fLib.getPropertyKeyValue("parentusername");
		String PPWD = fLib.getPropertyKeyValue("parentpassword");
		
		//step-2:Read all the data from the excel sheet
		int random=jLib.getRandomNumber();
		String examName = eLib.getExcelData("Sheet1", 3, 2)+random;
		
		//step-3: login as admin
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(AUN, APWD);
		
		//step-5:Navigate to Exam Link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getExmLink().click();
		ahp.getCrtExm().click();
		
		//step-6:Create exam with mandatory inputs
		AddExamPage aep=new AddExamPage(driver);
		aep.createExam(examName);
		aep.getMsgCloseBtn().click();
		//step-7: Admin Logout from the application
		ahp.logoutFrmApp();
		
		//step-8:verify with teacher 
		lp.loginToApp(TUN, TPWD);
		
		TeacherHomePage thp=new TeacherHomePage(driver);
		thp.getExmLink().click();
		thp.getExamTimTbl().click();
		TeacherExamTimetablePage tettp=new TeacherExamTimetablePage(driver);
		String actualEx=tettp.verifyExam(examName);
		System.out.println(actualEx);
	//	Assert.assertTrue(actualEx.contains("Term"));
		Assert.assertEquals(actualEx,examName);
		System.out.println("exam verified in teacher");
//		if(actualEx.equals(examName))
//		{
//			System.out.println("Exam "+examName+" created");
//		}
//		else
//		{
//			System.out.println("Exam "+examName+" not created");
//		}
		//Teacher Logout from the application
		thp.getLogoutlnk().click();
		thp.getLogoutBtn().click();

		//verify with parent module
		lp.loginToApp(PUN, PPWD);
		ParentHomePage php=new ParentHomePage(driver);
		php.getExmLink().click();
		php.getMySonsExmTT().click();
		
		mySonsExamPage msep=new mySonsExamPage(driver);
		
		String actualExm = msep.verifyExam(examName);
		
		System.out.println(actualExm);
		
		Assert.assertEquals(actualExm, examName);
		System.out.println("exam verified in parent");
//		if(actualExm.equals(examName))
//		{
//			System.out.println("Exam "+actualExm+" created");
//		}
//		else
//		{
//			System.out.println("Exam "+actualExm+" not created");
//		}

	}
}

