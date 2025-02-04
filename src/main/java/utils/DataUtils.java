package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import constants.FileConstants;

public class DataUtils {
	public static FileInputStream fileinputstream;
	
	
	/**
	 * To read the login test data file
	 * 
	 * @param key provide key to get the value
	 * @return value of the key passed
	 * @throws IOException
	 */
	public static String readLoginTestData(String key) throws IOException {
		fileinputstream = new FileInputStream(FileConstants.LOGIN_TESTDATA_FILE_PATH);
		
		Properties p = new Properties();
		p.load(fileinputstream);
		return p.getProperty(key);
		
	}
	//NEW_ACCOUNT_EDIT_PAGE_DETAILS
	public static String readNewAccountEditDetails(String Key) throws IOException {
		fileinputstream=new FileInputStream(FileConstants.NEW_ACCOUNT_EDIT_PAGE_DETAILS);
		Properties prop=new Properties();
		prop.load(fileinputstream);
		return prop.getProperty(Key);
		
	}
//CREATE_NEW_VIEW_DATA
	public static String readCreateNewViewDetails(String Key) throws IOException {
		fileinputstream=new FileInputStream(FileConstants.CREATE_NEW_VIEW_DATA);
		Properties prop=new Properties();
		prop.load(fileinputstream);
		return prop.getProperty(Key);
		
	}
	//LEADS_PAGE_TEST_DATA_FILE_PATH
	public static String readLeadsPageData(String key) throws IOException {
		fileinputstream=new FileInputStream(FileConstants.LEADS_PAGE_TEST_DATA_FILE_PATH);
		Properties prop=new Properties();
		prop.load(fileinputstream);
		return prop.getProperty(key);
	}
	//CONTACTS_PAGE_TEST_DATA_FILE_PATH
	public static String readContactsPageData(String key) throws IOException {
		fileinputstream=new FileInputStream(FileConstants.CONTACTS_PAGE_TEST_DATA_FILE_PATH);
		Properties prop=new Properties();
		prop.load(fileinputstream);
		return prop.getProperty(key);
	}
	//Usermenu Drop down page
	public static String  readUsermenuDropdownPageData(String Key) throws IOException {
		fileinputstream=new FileInputStream(FileConstants.USERMENU_DROPDOWN_PAGE_TEST_DATA_PATH);
		Properties prop=new Properties();
		prop.load(fileinputstream);
		return prop.getProperty(Key);
	}	
		
		public static String readExtentPropertiesData(String key,String filepath) throws IOException {
			fileinputstream = new FileInputStream(filepath);
			
			Properties p = new Properties();
			p.load(fileinputstream);
			return p.getProperty(key);
}
}
