package com.wizGradeSchoolapp.generic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;


public class DataBAseUtility {
	static Driver driver;
	static Connection connection;
	static ResultSet result;
	/**
	 * this method will perform the mysql database connection
	 * @throws SQLException
	 */
	public void connectionDB() throws SQLException{
		try {
			driver=new Driver();
			DriverManager.registerDriver(driver);
			connection=DriverManager.getConnection(IConstants.JDBC_URL_String,IConstants.JDBC_USERNAME,IConstants.JDBC_PASSWORD);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * this method will close the mysql database
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException{
		try {
			connection.close();
		}catch(Exception e)
		{
		}
	}
	/**
	 * this method will execute the query
	 * @param query
	 * @return
	 * @throws SQLException 
	 * @throws Throwable
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		result=connection.createStatement().executeQuery(query);
		return result;
	}
	/**
	 * this method will execute the query
	 * @param query
	 * @return
	 * @throws SQLException 
	 * @throws Throwable
	 */
	public int executeUpdate(String query) throws SQLException {
		int result=connection.createStatement().executeUpdate(query);
		return result;
	}
	/**
	 * this method will execute the query based on query and it will verify the data
	 * @param query
	 * @param columnName
	 * @param expectedData
	 * @return
	 * @throws SQLException 
	 * @throws Throwable
	 */
	public boolean executeQueryAndVerify(String query,int columnIndex,String expectedData) throws SQLException {
		boolean flag=false;
		result=connection.createStatement().executeQuery(query);
		while(result.next()) {
			if(result.getString(columnIndex).equals(expectedData)) {
				flag=true;
				break;
			}
		}
		if(flag) {
			System.out.println(expectedData+"==>Data is verified in the data base table");
		} else {
			System.out.println(expectedData+"==>Data is not verified in the data base table");
		}
		return flag;
	}
}
