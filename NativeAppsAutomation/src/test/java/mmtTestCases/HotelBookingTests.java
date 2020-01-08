package mmtTestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mmtPageLogic.mmtHomePageFunctions;
import mmtPageLogic.mmtHotelBookingPageFunctions;
import mmtPageLogic.mmtHotelsResultPageFunctions;

public class HotelBookingTests extends BaseClassMMT {
	
	/**
	 * Test Method Name : Validate Hotel Booking in MMT and finding Premium Hotels
	 * Description : Run a hotel booking scenario on MMT and find the Premium hotel deals
	 * Author - Kovid Mehta
	 * Date - 12/28/2019
	 */
	
	@DataProvider
	public Object[][] HotelBookingDataProvider(){
		Object[][] data = new Object[1][7];
		data[0][0] = "Bangalore";
		data[0][1] = "May 2020";
		data[0][2] = "21";
		data[0][3] = "May 2020";
		data[0][4] = "29";
		data[0][5] = 3;
		data[0][6] = 1;
		return data;
		
	}
	
	@DataProvider
	public Object[][] readExcelDataForHotelBooking() throws EncryptedDocumentException, IOException{
		return DataReader.readDataFromExcel("..//TestDataSheet.xlsx", "HotelBookingData");
	}
	
	@Test(priority=3,dataProvider ="HotelBookingDataProvider",enabled=false)
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
	 * Test Method Name : bookingDateToastMessage & BookingAdultLimitToasMessage
	 * Description : Below two Test Cases deals with handling toast Messages
	 * when running Hotel Booking scenarios on MMT.
	 * Author - Kovid Mehta
	 * Date - 1/8/2019
	 */
	@DataProvider
	public Object[][] bookingDateToastMessagedataProvider(){
		Object[][] data = new Object[1][5];
		data[0][0] = "Kolkata";
		data[0][1] = "March 2020";
		data[0][2] = "3";
		data[0][3] = "June 2020";
		data[0][4] = "21";
		return data;
	}
	
	@DataProvider
	public Object[][] readExcelDataForBookingDateToastMessage() throws EncryptedDocumentException, IOException{
		return DataReader.readDataFromExcel("..//TestDataSheet.xlsx", "BookingDateToastMessageData");
	}
	
	@Test(priority=5,dataProvider="bookingDateToastMessagedataProvider",enabled=false)
	public void bookingDateToastMessage(String bookingCity, String CheckInMonYear,
			String checkinDate, String checkoutMonYear, String checkoutDate) {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		mmtHotelBookingPageFunctions TS2 = new mmtHotelBookingPageFunctions(driver,wait);
		TS1.checkForUnwantedPopUps();
		TS1.clickOnHotels();
		TS2.setHotelDestination(bookingCity);
		TS2.setCheckInDate(CheckInMonYear, checkinDate);
		TS2.setCheckOutDate(checkoutMonYear, checkoutDate);
	}
	
	@DataProvider
	public Object[][] BookingAdultLimitToastMessage(){
		Object[][] data = new Object[1][6];
		data[0][0] = "Kolkata";
		data[0][1] = "March 2020";
		data[0][2] = "3";
		data[0][3] = "March 2020";
		data[0][4] = "12";
		data[0][5] = 8;
		return data;
	}
	
	public Object[][] readExcelDataForGuestLimit() throws EncryptedDocumentException, IOException{
		return DataReader.readDataFromExcel("..//TestDataSheet.xlsx", "GuestLimitToastMessageData");
	}
	
	@Test(priority=6,dataProvider="BookingAdultLimitToastMessage",enabled=false)
	public void BookingAdultLimitToastMessage(String bookingCity, String CheckInMonYear,
			String checkinDate, String checkoutMonYear, String checkoutDate,
			int numberOfAdults) {
		mmtHomePageFunctions TS1 = new mmtHomePageFunctions(driver, wait);
		mmtHotelBookingPageFunctions TS2 = new mmtHotelBookingPageFunctions(driver,wait);
		TS1.checkForUnwantedPopUps();
		TS1.clickOnHotels();
		TS2.setHotelDestination(bookingCity);
		TS2.setCheckInDate(CheckInMonYear, checkinDate);
		TS2.setCheckOutDate(checkoutMonYear, checkoutDate);
		TS2.setNumberOfAdults(numberOfAdults);
		
	}


}
