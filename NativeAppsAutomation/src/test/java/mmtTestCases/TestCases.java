package mmtTestCases;

import org.testng.annotations.Test;

import mmtPageLogic.mmtFlightBookingPageFunctions;
import mmtPageLogic.mmtFlightResultPageFunctions;
import mmtPageLogic.mmtHomePageFunctions;
import mmtPageLogic.mmtHotelBookingPageFunctions;
import mmtPageLogic.mmtHotelsResultPageFunctions;

public class TestCases extends BaseClassMMT {
		
	
	/****
	 * Description : Below method allows user to search flights and returns first
	 * three cheapest flights with timings.
	 * @param departureCity, arrivalCity, date, Number of Passengers 
	 * and Class Preference
	 * author : Kovid Mehta on 1/4/2020
	 */
	@Test(enabled=false)
	public void flightBooking() {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		mmtFlightBookingPageFunctions TS2 = new mmtFlightBookingPageFunctions(driver,wait);
		mmtFlightResultPageFunctions TS3 = new mmtFlightResultPageFunctions(driver,wait);
		TS1.checkForUnwantedPopUps();
		TS1.clickOnFlights();
		TS2.enterFromCity("Pune");
		TS2.enterToCity("Delhi");
		TS2.selectDate("June 2020", "29");
		TS2.numberOfTraveller(3, 2, 1);
		TS2.travelClassPreference("Economy Class");
		TS2.clickOnSearch();
		System.out.println("The first three cheapest flights are : "+TS3.flightDetails("Pune", "Delhi"));
		TS3.totalFlightCost();
	}
	
	
	/*****
	 * Below method checks for Railway Tickets between two destinations and 
	 * lists down the prices of different tier on first three options
	 */
	@Test(enabled=false)
	public void bookRails() {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		TS1.checkForUnwantedPopUps();
		TS1.openRailBooking();
		
		
	}
	
	/**
	 * Below method finds the most premium hotel on a given destination
	 * 
	 */
	@Test(enabled=true)
	public void getPriciestPremiumHotelDetails() {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		mmtHotelBookingPageFunctions TS2 = new mmtHotelBookingPageFunctions(driver,wait);
		mmtHotelsResultPageFunctions TS3 = new mmtHotelsResultPageFunctions(driver, wait);
		TS1.checkForUnwantedPopUps();
		TS1.clickOnHotels();
		TS2.setHotelDestination("Bangalore");
		TS2.setCheckInDate("May 2020", "21");
		TS2.setCheckOutDate("May 2020", "29");
		TS2.setNumberOfAdults(3);
		TS2.childrenConfiguration(1);
		TS2.clickOnSearchHotels();
		TS2.closeBanner();
		TS2.closeHelper();
		TS3.selectPremiumCategory();
		TS3.sortByHighPriceToLowPrice();
		System.out.println("The two Priciest hotels for this search are : " +TS3.fetchHighestPriceHotel());
	}
	
	/***
	 * Below methods returns the count of number of travel blogs
	 * and clicks on a specific one
	 */
	@Test(enabled=false)
	public void getNumberOfTravelBlogsAndClickOnSpecificOne() {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		TS1.checkForUnwantedPopUps();
		TS1.getTravelBlogDetails("Top 5 Luxury Beach Resorts in India");
	}
	
	

}

