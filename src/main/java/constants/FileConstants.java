package constants;



import utils.CommonUtils;

public class FileConstants {

	public static final String LOGIN_TESTDATA_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\LoginTestData.properties";
	public static final String SCREENSHOTS_FOLDER_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\ScreenShots\\"+CommonUtils.getTimeStamp()+".PNG";
	public static final String REPORTS_FILE_PATH=
			System.getProperty("user.dir")+"\\test-output\\SparkReports\\ExtentSparkReport"+CommonUtils.getTimeStamp()+".html";
	public static final String FILE_UPLOAD_PATH=System.getProperty("user.dir")+"\\src\\main\\resources\\log4j2.xml";
	public static final String NEW_ACCOUNT_EDIT_PAGE_DETAILS=System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\newAccountEditPageDetails.properties";		
	public static final String CREATE_NEW_VIEW_DATA=System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\CreateNewViewData.properties";
	public static final String LEADS_PAGE_TEST_DATA_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\leadsPageTestData.properties";
	public static final String CONTACTS_PAGE_TEST_DATA_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\contactsPageData.properties";
    public static final String USERMENU_DROPDOWN_PAGE_TEST_DATA_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\fileUploadData.properties";
}
