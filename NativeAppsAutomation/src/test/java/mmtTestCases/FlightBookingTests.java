package mmtTestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mmtPageLogic.mmtFlightBookingPageFunctions;
import mmtPageLogic.mmtFlightResultPageFunctions;
import mmtPageLogic.mmtHomePageFunctions;

public class FlightBookingTests extends BaseClassMMT{
	
	/****
	 * Test Method Name : Validate that User can book flights on MMT and observe the results
	 * Description : Run the flight Booking Scenario on MMT with User provided input
	 * and returns three Cheapest flight options with price and timing
	 * Author - Kovid Mehta
	 * Date - 12/28/2019
	 * @return
	 */
	
	@DataProvider
	public Object[][] flightBookingDataProvider(){
		Object[][] data = new Object[1][8];
		data[0][0] = "Pune";
		data[0][1] = "Delhi";
		data[0][2] = "June 2020";
		data[0][3] = "29";
		data[0][4] = 3;
		data[0][5] = 2;
		data[0][6] = 1;
		data[0][7] = "Economy Class";
		return data;
	}
	@DataProvider
	public Object[][] readExcelDataForFlightBooking() throws EncryptedDocumentException, IOException{
		return DataReader.readDataFromExcel("..//TestDataSheet.xlsx","FlightBookingData");
	}
	
	@Test(priority=1,dataProvider="flightBookingDataProvider",enabled=false)
	public void flightBooking(String fromCity, String toCity, String MonYear, String Date,
			int Adults, int children, int infants, String travelPreference) {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		mmtFlightBookingPageFunctions TS2 = new mmtFlightBookingPageFunctions(driver,wait);
		mmtFlightResultPageFunctions TS3 = new mmtFlightResultPageFunctions(driver,wait);
		TS1.checkForUnwantedPopUps();
		TS1.clickOnFlights();
		TS2.enterFromCity(fromCity);
		TS2.enterToCity(toCity);
		TS2.selectDate(MonYear, Date);
		TS2.numberOfTraveller(Adults, children, infants);
		TS2.travelClassPreference(travelPreference);
		TS2.clickOnSearch();
		System.out.println("The first three cheapest flights are : "+TS3.flightDetails(fromCity, toCity));
		TS3.totalFlightCost();
	}
	

}
