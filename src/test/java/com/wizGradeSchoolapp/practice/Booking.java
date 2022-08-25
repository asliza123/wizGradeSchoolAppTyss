package com.wizGradeSchoolapp.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Booking {
	
	@Test(dataProvider = "bookingTicket_dataProvider")
	public void bookingTicketTest(String src, String dest, int tckt) {
		System.out.println("Source=>"+src+", Destination=>"+dest+" and No. of tickets=>"+tckt);
	}
@DataProvider
public Object[][] bookingTicket_dataProvider()
{
	Object book[][]= new Object[5][3];
	
	book[0][0]="Bangalore";
	book[0][1]="Rourkela";
	book[0][2]=10;
	
	book[1][0]="Bangalore";
	book[1][1]="Patna";
	book[1][2]=8;
	
	book[2][0]="Bangalore";
	book[2][1]="Vishakapatnam";
	book[2][2]=7;
	
	book[3][0]="Bangalore";
	book[3][1]="Mumbai";
	book[3][2]=6;
	
	book[4][0]="Bangalore";
	book[4][1]="Mysore";
	book[4][2]=9;
	
	return book;
	}
}
