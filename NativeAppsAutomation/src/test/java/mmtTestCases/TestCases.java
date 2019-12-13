package mmtTestCases;

import org.testng.annotations.Test;

import mmtPageLogic.mmtFlightPageFunctions;
import mmtPageLogic.mmtHomePageFunctions;

public class TestCases extends BaseClassMMT {
		
	@Test(enabled=false)
	public void flightBooking() {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		mmtFlightPageFunctions TS2 = new mmtFlightPageFunctions(driver,wait);
		TS1.checkForUnwantedPopUps();
		TS1.clickOnFlights();
		TS2.enterFromCity("Pune");
		TS2.enterToCity("Delhi");
		TS2.clickOnSearch();
		
	}
	
	@Test
	public void bookRails() {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		TS1.checkForUnwantedPopUps();
		TS1.openRailBooking();
		
		
	}
	

}
