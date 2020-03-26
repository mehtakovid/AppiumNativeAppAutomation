package mmtTestCases;

import org.testng.annotations.Test;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import mmtPageLogic.mmtHomePageFunctions;

public class MiscTestCases extends BaseClassMMT {
		
	public static Logger log = getLogger(MiscTestCases.class);
	
	/*****
	 * Test Method Name : Validate that User can scroll to Rails option
	 * Description - User should be able to scroll through Widget options to
	 * check rails booking option
	 * Author - Kovid Mehta
	 * Date - 12/28/2019
	 ***/
	
	@Test(priority=2,enabled=true)
	public void scrollToRails() {
		log.info("Execution of Test Case 2 Starts.");
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait,log);
		TS1.checkForUnwantedPopUps();
		TS1.openRailBooking();
		log.info("Execution of Test Case 2 Ends.");
	}
	
	
	/***
	 * Test Method Name : Validate that blogs widget is scrollable and user can access travel blogs
	 * Description : Scroll through travel blogs on Home Screen and open user provided Blog
	 * Author - Kovid Mehta
	 * Date - 1/5/2020
	 */
	@DataProvider
	public Object[][] travelBlogDataProvider(){
		Object[][] data = new Object[2][1];
		data[0][0] = "Top 5 Luxury Beach Resorts in India";
		data[1][0] = "Dubai, 10 Incredible Things to do on Your First Visit";
		return data;
	}
	@DataProvider
	public Object[][] readExcelDataForTravelBlogs() throws EncryptedDocumentException, IOException{
		return DataReader.readDataFromExcel("..//TestDataSheet.xlsx", "TravelBlogData");
	}
	
	@Test(priority=4,dataProvider="travelBlogDataProvider",enabled=true)
	public void getNumberOfTravelBlogsAndClickOnSpecificOne(String blogName) {
		log.info("Execution of Test Case 4 Starts.");
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait,log);
		TS1.checkForUnwantedPopUps();
		TS1.getTravelBlogDetails("Top 5 Luxury Beach Resorts in India");
		log.info("Execution of Test Case 4 Ends.");
	}
	

}

