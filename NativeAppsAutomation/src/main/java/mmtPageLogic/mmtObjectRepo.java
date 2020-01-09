package mmtPageLogic;

import org.openqa.selenium.By;

public class mmtObjectRepo {

	By popUpClose = By.id("com.makemytrip:id/cross_onboarding");
	//By popUpClose = By.xpath("//*[@text='SKIP']");
	By HomeButton = By.xpath("//android.widget.TextView[@text='Trips']");
	By flightsIcon = By.xpath("//android.widget.TextView[@text='Flights']");
	By hotelsIcon = By.xpath("//android.widget.TextView[@text='Hotels']");
	By travelBlogHeading = By.xpath("//android.widget.TextView[@text='Travel Blogs']");

	//FlightBookingPage
	By fromCity = By.id("com.makemytrip:id/froCityLayout");
	By CityTextBox = By.id("com.makemytrip:id/city_edit_text");
	By CityFirstSuggestion = By.id("com.makemytrip:id/cityPickerRow");
	By toCity = By.id("com.makemytrip:id/toCityLayout");
	By departureDate = By.id("com.makemytrip:id/depDateLayout");
	By okButton = By.id("com.makemytrip:id/calOK");
	By SearchButton = By.xpath("//*[@text='SEARCH']");
	By flightOptionsAssertion = By.xpath("//*[@text='Zero Cancellation']");
	By passengerConfig = By.xpath("//android.widget.TextView[@text='ADULT']");
	By addAdultPassenger = By.id("com.makemytrip:id/adultAddLayout");
	By addChildPassenger = By.id("com.makemytrip:id/childAddLayout");
	By addInfantPassenger = By.id("com.makemytrip:id/infantAddLayout");
	By OkButton_PassengerConfig = By.xpath("//android.widget.TextView[@text='OK']");
	By travelClassPreference = By.id("com.makemytrip:id/classLayout");
	By economyClass = By.xpath("//android.widget.TextView[@text='ECONOMY CLASS']");
	By premiumEconomy = By.xpath("//android.widget.TextView[@text='PREMIUM ECONOMY']");
	By businessClass = By.xpath("//android.widget.TextView[@text='BUSINESS CLASS']");
	
	By firstSearchResultVisibility = By.id("com.makemytrip:id/flt_zc_list_item");
	By departureCityValidation = By.id("com.makemytrip:id/flight_listing_from_city");
	By arrivalCityValidation = By.id("com.makemytrip:id/flight_listing_to_city");
	By departureTime = By.id("com.makemytrip:id/df_flight_dep_time");
	By arrivalTime = By.id("com.makemytrip:id/df_flight_ret_time");
	By flightPrice = By.id("com.makemytrip:id/df_flight_price");
	
	
	By totalFare = By.id("com.makemytrip:id/fare_text");
	By selectInsurance = By.id("com.makemytrip:id/ivInsuranceSelectOption");
	By Rails = By.xpath("//*[@text='Rails']");
	By RailsAssertion = By.xpath("//*[@text='Rails']");
	
	//HotelBookingPage
	By hotelDestination = By.id("com.makemytrip:id/rlDestinationBlock");
	By searchCity = By.id("com.makemytrip:id/tvAutoCompleteSearch");
	By firstSuggestion = By.id("com.makemytrip:id/rlAutocompleteListItem");
	By checkInDate = By.id("com.makemytrip:id/rlCheckInDate");
	By checkOutDate = By.id("com.makemytrip:id/rlCheckoutBlock");
	By numberOfRooms = By.id("com.makemytrip:id/rlNoOfRooms");
	By numberOfAdults = By.id("com.makemytrip:id/rlAdultNumbers");
	By numberOfChildren = By.id("com.makemytrip:id/rlChildBlock");
	By addAdults = By.id("com.makemytrip:id/ivadultadd");
	By addChild = By.id("com.makemytrip:id/ivchildadd");
	By DoneButton = By.id("com.makemytrip:id/btn_done");
	By searchHotelsButton = By.id("com.makemytrip:id/BtnSearch");
	By premiumCategory = By.xpath("//android.widget.TextView[@text='PREMIUM']");
	By sortAndFilter = By.id("com.makemytrip:id/sort_filter");
	By sort_highToLow = By.xpath("//android.widget.TextView[@text=' High to Low']");
	By hotelNames = By.id("com.makemytrip:id/tvHotelName");
	By hotelPrices = By.id("com.makemytrip:id/tvHotelDiscountedPrice");
	By mmtAssuredBanner = By.id("com.makemytrip:id/tvSkipNow");
	By helperPopUp = By.id("com.makemytrip:id/close_helper");
}
