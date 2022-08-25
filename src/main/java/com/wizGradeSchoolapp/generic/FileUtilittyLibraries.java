package com.wizGradeSchoolapp.generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * its contains external file specific libraries
 * @author Asmita
 *
 */
public class FileUtilittyLibraries {
/**
 * its used return the value from the property file based on key 
 * @param key
 * @return value
 * @throws IOException 
 * @throws throwable
 */
	public String getPropertyKeyValue(String Key) throws IOException {
		FileInputStream fis=new FileInputStream(IConstants.PROPERTYFILEPATH);
		Properties p=new Properties();
		p.load(fis);
		String value = p.getProperty(Key);
		return value;
	}
}
