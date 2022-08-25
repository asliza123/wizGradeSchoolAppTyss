package com.wizGradeSchoolapp.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDataBaseExecuteQuery {
	public static void main(String[] args) throws SQLException {
		//step-1: create object of driver
		Driver driver=new Driver();
		//step-2: register the driver to jdbc
		DriverManager.registerDriver(driver);
		//step-3: establish the connection - provide database name
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet38", "root", "root");
		//step-4: create the statement
		Statement state = con.createStatement();
		//step-5: execute query- provide table name
		String query="select * from empdetails;";
		String expdata="asmita";
		ResultSet result = state.executeQuery(query);
		boolean flag = false;
		while(result.next())
		{
			String actdata=result.getString(2);
			//System.out.println(actdata);
			if(actdata.equalsIgnoreCase(expdata))
			{
				System.out.println(actdata);
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("Data present");
		}
		else
		{
			System.out.println("Data not present");
		}
		//step-6: validate the data
		
		//step-7: close the connection
		con.close();
	}
}
