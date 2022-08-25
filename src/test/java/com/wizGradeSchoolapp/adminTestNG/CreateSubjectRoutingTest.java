package com.wizGradeSchoolapp.adminTestNG;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.wizGradeSchoolapp.genericLibraries.BaseClass;
import com.wizGradeSchoolapp.objectRepositoryUtility.AddSubjectRoutingPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.SubjectRoutingPage;
@Listeners(com.wizGradeSchoolapp.genericLibraries.ListnerImplementation.class)
public class CreateSubjectRoutingTest extends BaseClass{
	@Test(invocationCount=-1)
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
//		if(grNm.equals(actCheck)) {
//			System.out.println("verified-->PASS");
//		}
//		else
//		{
//			System.out.println("verified-->FAIL");
//		}
		
	}

}
