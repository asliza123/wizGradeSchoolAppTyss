package com.wizGradeSchoolapp.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class FetchDataFromDatabasePractice {

	public static void main(String[] args) throws SQLException {
//create the object for DB driver class
		Driver dbDriver=new Driver();
		//register the driver to jdbc
		DriverManager.registerDriver(dbDriver);
		Connection connection = null;
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet38", "root", "root");
		Statement statement = connection.createStatement();
		statement.executeUpdate("create table EmpDetails(empId int(4) not null unique, empName varchar(30), phone_number int(10) not null unique, Address varchar(50));");
		System.out.println("data updated sucessfully");
		}
		finally {
		connection.close();
		System.out.println("connection closed");
		}
	}

}
