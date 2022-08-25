package com.wizGradeSchoolapp.adminTestNG;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.wizGradeSchoolapp.genericLibraries.BaseClass;
import com.wizGradeSchoolapp.objectRepositoryUtility.AddTimetablePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.AdminHomePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.AllTimetablePage;
import com.wizGradeSchoolapp.objectRepositoryUtility.ClassroomPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.LoginPage;
import com.wizGradeSchoolapp.objectRepositoryUtility.TeacherHomePage;
@Listeners(com.wizGradeSchoolapp.genericLibraries.ListnerImplementation.class)

public class CreateTimetableAsAdminAndVerifyAsTeachersTest extends BaseClass{
	@Test
	public void AddTimetableTest() throws IOException {
		//step-1:Read all the common data
		
		String AUN = fLib.getPropertyKeyValue("adminusername");
		String APWD = fLib.getPropertyKeyValue("adminpassword");
		String TUN = fLib.getPropertyKeyValue("teacherusername");
		String TPWD = fLib.getPropertyKeyValue("teacherpassword");
		
		//step-2:Read all the data from the excel sheet
		String clsNm = eLib.getExcelData("Sheet1", 5, 2)+jLib.getRandomNumber();
		String clsCount = eLib.getExcelData("Sheet1", 5, 3);
		String grd = eLib.getExcelData("Sheet1", 5, 4);
		String dayNm = eLib.getExcelData("Sheet1", 5, 5);
		String subNm = eLib.getExcelData("Sheet1", 5, 6);
		String teNm = eLib.getExcelData("Sheet1", 5, 7);
		String startTm = eLib.getExcelData("Sheet1", 5, 8);
		String endTm = eLib.getExcelData("Sheet1", 5, 9);
		
		//step-4:Admin Login to application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(AUN, APWD);
		
		//step-5:Navigate to classroom Link
		AdminHomePage ahp=new AdminHomePage(driver);
		ahp.getClsRoomLink().click();

		//step-6:create a classroom
		ClassroomPage crp=new ClassroomPage(driver);
		crp.createClsRoom(clsNm, clsCount);
		crp.getMsgRemoveBtn().click();
		
		//step-7:Navigate to timetable link
		ahp.getTimetanleLink().click();
		
		//step-8:select a grade
		AddTimetablePage atp=new AddTimetablePage(driver);
		atp.selectGrade(grd);
		
		//step-9:create a timetable
		atp.getAddBtn().click();
		atp.addTimetable(dayNm,subNm,teNm,clsNm,startTm,endTm);
		atp.getRmvMsgBtn().click();
		
		//step-10: Admin Logout from the application
		ahp.logoutFrmApp();
		
		//step-11: Teacher login to application
		lp.loginToApp(TUN, TPWD);
		
		//step-12:Navigate to timetable link
		TeacherHomePage thp=new TeacherHomePage(driver);
		thp.getTimetablDrpDwnBtn().click();
		thp.getAllTTLink().click();
		
		//step-13: Verify timetable created or not
		AllTimetablePage alltp=new AllTimetablePage(driver);
		alltp.selectGrd(grd);
		String actualSub = alltp.verifyTimeTable(subNm);
		Assert.assertEquals(actualSub, subNm);
		System.out.println("timetable created");
//		if(actualSub.contains(subNm))
//		{
//			System.out.println("Sucessfully Done");
//		}
//		else
//		{
//			System.out.println("Not Done");
//		}
		
	}

}
