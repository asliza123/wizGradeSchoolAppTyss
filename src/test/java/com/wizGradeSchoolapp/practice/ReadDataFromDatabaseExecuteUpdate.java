package com.wizGradeSchoolapp.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabaseExecuteUpdate {

	public static void main(String[] args) throws SQLException {
		//step-1: create object for practice
		Driver driver=new Driver();
		//step-2: register the driver to jdbc
		DriverManager.registerDriver(driver);
		//step-3: establish the connection - provide database name
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet38", "root", "root");
		//step-4: create the statements
		Statement state = con.createStatement();
		//step-5: execute the queries
		String query="insert into empdetails values(39,'Sasmita',7205513484,'dtvkihub');";
		int result = state.executeUpdate(query);
		System.out.println(result);
		//step-6: validate the data
		if(result==1)
		{
			System.out.println("data added successfully");
		}
		else
		{
			System.out.println("data not added");
		}
		//step-7: close the connection
		con.close();
		System.out.println("connection closed");
	
	}

}
