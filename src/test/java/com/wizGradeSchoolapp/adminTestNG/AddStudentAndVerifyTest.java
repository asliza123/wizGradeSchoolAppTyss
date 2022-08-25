package com.wizGradeSchoolapp.adminTestNG;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.wizGradeSchoolapp.genericLibraries.BaseClass;
import com.wizGradeSchoolapp.objectRepositoryUtility.AddSubjectRoutingPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.StudentPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.SubjectRoutingPage;

@Listeners(com.wizGradeSchoolapp.genericLibraries.ListnerImplementation.class)
public class AddStudentAndVerifyTest extends BaseClass{
	@Test(priority = 1)
	public void addSubjectRoutingTest() throws IOException {
		//step-1:Read all the common data
		String AUN = fLib.getPropertyKeyValue("adminusername");
		String APWD = fLib.getPropertyKeyValue("adminpassword");
		
		//step-2:Read all the data from the excel sheet
		String grNm = eLib.getExcelData("Sheet1", 9, 2);
		String sNm = eLib.getExcelData("Sheet1", 9, 3);
		String tNm = eLib.getExcelData("Sheet1", 9, 4);
		String fees = eLib.getExcelData("Sheet1", 9, 5);
		
		//step-4:Login to application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(AUN, APWD);
		
		//step-5:Navigate to Subject Routing Link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getSubRoutLnk().click();
		
		//step-6:Create subject Routing with mandatory inputs
		SubjectRoutingPage srp=new SubjectRoutingPage(driver);
		srp.getAddBtn().click();
		
		AddSubjectRoutingPage asrp=new AddSubjectRoutingPage(driver);
		asrp.createSubRouting(grNm,sNm,tNm,fees);
		srp.getMsgCloseBtn().click();
		srp.getSearchTxBx().sendKeys(sNm);
		String actCheck = srp.verifySubRoutCrtOrNot(sNm, grNm);
		Assert.assertEquals(actCheck, grNm);
		System.out.println("subject routing created");
	}
	
	@Test(priority = 2)
	public void createStudentTest() throws IOException {
		
		//step-1: read all common data
		String UN = fLib.getPropertyKeyValue("adminusername");
		String PWD = fLib.getPropertyKeyValue("adminpassword");
		String stdPh= fLib.getPropertyKeyValue("stdphoto");
		String grdPh= fLib.getPropertyKeyValue("grdphoto");
		
		//step-2: read all excel data
		int random=jLib.getRandomNumber();
		String stdIndx=eLib.getExcelData("Sheet1", 11, 2)+random;
		String stdFullNm=eLib.getExcelData("Sheet1", 11, 3);
		String stdNameWI=eLib.getExcelData("Sheet1", 11, 4);
		String stdAdd = eLib.getExcelData("Sheet1", 11, 5);
		String stdEml = random+eLib.getExcelData("Sheet1", 11, 6);
		String stdPhn = eLib.getExcelData("Sheet1", 11, 7);
		String stdDOB = eLib.getExcelData("Sheet1", 11, 8);
		String stdGndr = eLib.getExcelData("Sheet1", 11, 9);
		String grdFullNm= eLib.getExcelData("Sheet1", 11, 10);
		String grdNameWI= eLib.getExcelData("Sheet1", 11, 11);
		String grdAdd = eLib.getExcelData("Sheet1", 11, 12);
		String grdEml = random+eLib.getExcelData("Sheet1", 11, 13);
		String grdPhn = eLib.getExcelData("Sheet1", 11, 14);
		String grdDOB = eLib.getExcelData("Sheet1", 11, 15);
		String grdGndr = eLib.getExcelData("Sheet1", 11, 16);
		String grdNm = eLib.getExcelData("Sheet1", 11, 17);
		
		
		//step-4: login as admin
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(UN, PWD);
		
		//step-5: Navigate to Student link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getStdLink().click();
		
		//step-6: Navigate to student preview page
		ahp.getAddStdLink().click();
		
		//step-7: Create a student
		StudentPage sp=new StudentPage(driver);
		sp.studentDetails(stdIndx, stdFullNm, stdNameWI, stdAdd, stdEml, stdPhn, stdDOB, stdGndr, stdPh);
		sp.guardianDetails(grdFullNm, grdNameWI, grdAdd, grdEml, grdPhn, grdDOB, grdGndr, grdPh);
		sp.getSubmitBtn().click();
				
		//step-8: select grade for student
		sp.selectGrade(grdNm);
//		sp.getSubchkBx().click();
//		sp.getSubjchkBx().click();
//		sp.getSmtBtn().click();
		sp.chooseSub();
		sp.getMsgCloseBtn().click();
		
		//step-9: verify the created student
		ahp.getStdLink().click();
		ahp.getAllStdLink().click();
		sp.selectGrade(grdNm);
		sp.getSelectBtn().click();
		sp.getSearchTxBx().sendKeys(stdNameWI);
		String actual=sp.verifyStudentDetails(stdNameWI);
		Assert.assertEquals(actual, stdNameWI);
		System.out.println("student created");
//		if(stdNameWI.equals(actual)) {
//			System.out.println("Student is created");
//		}
//		else
//		{
//			System.out.println("student is not created");
//		}
//		
	}

}
