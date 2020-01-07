package mmtTestCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import mmtPageLogic.mmtFlightBookingPageFunctions;
import mmtPageLogic.mmtFlightResultPageFunctions;
import mmtPageLogic.mmtHomePageFunctions;
import mmtPageLogic.mmtHotelBookingPageFunctions;
import mmtPageLogic.mmtHotelsResultPageFunctions;

public class TestCases extends BaseClassMMT {
		
	
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
		Object[][] data = new Object[2][8];
		data[0][0] = "Pune";
		data[0][1] = "Delhi";
		data[0][2] = "June 2020";
		data[0][3] = "29";
		data[0][4] = 3;
		data[0][5] = 2;
		data[0][6] = 1;
		data[0][7] = "Economy Class";
		data[1][0] = "Pune";
		data[1][1] = "Chennai";
		data[1][2] = "June 2020";
		data[1][3] = "29";
		data[1][4] = 3;
		data[1][5] = 2;
		data[1][6] = 1;
		data[1][7] = "Economy Class";
		return data;
	}
	
	@Test(dataProvider="flightBookingDataProvider",enabled=false)
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
	
	
	/*****
	 * Test Method Name : Validate that User can scroll to Rails option
	 * Description - User should be able to scroll through Widget options to
	 * check rails booking option
	 * Author - Kovid Mehta
	 * Date - 12/28/2019
	 ***/
	@Test(enabled=false)
	public void scrollToRails() {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		TS1.checkForUnwantedPopUps();
		TS1.openRailBooking();
			
	}
	
	/**
	 * Test Method Name : Validate Hotel Booking in MMT and finding Premium Hotels
	 * Description : Run a hotel booking scenario on MMT and find the Premium hotel deals
	 * Author - Kovid Mehta
	 * Date - 12/28/2019
	 */
	
	@DataProvider
	public Object[][] HotelBookingDataProvider(){
		Object[][] data = new Object[2][7];
		data[0][0] = "Bangalore";
		data[0][1] = "May 2020";
		data[0][2] = "21";
		data[0][3] = "May 2020";
		data[0][4] = "29";
		data[0][5] = 3;
		data[0][6] = 1;
		data[1][0] = "Delhi";
		data[1][1] = "November 2020";
		data[1][2] = "29";
		data[1][3] = "December 2020";
		data[1][4] = "3";
		data[1][5] = 2;
		data[1][6] = 0;
		return data;
		
	}
	@Test(dataProvider ="HotelBookingDataProvider",enabled=true)
	public void getPriciestPremiumHotelDetails(String bookingCity, String CheckInMonYear,
			String checkinDate, String checkoutMonYear, String checkoutDate,
			int numberOfAdults, int children) {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		mmtHotelBookingPageFunctions TS2 = new mmtHotelBookingPageFunctions(driver,wait);
		mmtHotelsResultPageFunctions TS3 = new mmtHotelsResultPageFunctions(driver, wait);
		TS1.checkForUnwantedPopUps();
		TS1.clickOnHotels();
		TS2.setHotelDestination(bookingCity);
		TS2.setCheckInDate(CheckInMonYear, checkinDate);
		TS2.setCheckOutDate(checkoutMonYear, checkoutDate);
		TS2.setNumberOfAdults(numberOfAdults);
		TS2.childrenConfiguration(children);
		TS2.clickOnSearchHotels();
		TS2.closeBanner();
		TS2.closeHelper();
		TS3.selectPremiumCategory();
		TS3.sortByHighPriceToLowPrice();
		System.out.println("The two Priciest hotels for this search are : " +TS3.fetchHighestPriceHotel());
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
	
	@Test(dataProvider="travelBlogDataProvider",enabled=false)
	public void getNumberOfTravelBlogsAndClickOnSpecificOne(String blogName) {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		TS1.checkForUnwantedPopUps();
		TS1.getTravelBlogDetails("Top 5 Luxury Beach Resorts in India");
	}
	
	

}

