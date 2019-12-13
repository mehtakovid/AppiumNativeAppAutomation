package mmtPageLogic;

import org.openqa.selenium.By;

public class mmtObjectRepo {

	By popUpClose = By.id("com.makemytrip:id/cross_onboarding");
	//By popUpClose = By.xpath("//*[@text='SKIP']");
	By HomeButton = By.xpath("//android.widget.TextView[@text='Trips']");
	By flightsIcon = By.xpath("//android.widget.TextView[@text='Flights']");
	
	//FlightBookingPage
	By fromCity = By.id("com.makemytrip:id/froCityLayout");
	By CityTextBox = By.id("com.makemytrip:id/city_edit_text");
	By CityFirstSuggestion = By.id("com.makemytrip:id/cityPickerRow");
	By toCity = By.id("com.makemytrip:id/toCityLayout");
	By departureDate = By.id("com.makemytrip:id/depDateLayout");
	By okButton = By.id("com.makemytrip:id/calOK");
	By SearchButton = By.xpath("//*[@text='SEARCH']");
	By flightOptionsAssertion = By.xpath("//*[@text='Zero Cancellation']");

	By Rails = By.xpath("//*[@text='Rails']");
	By RailsAssertion = By.xpath("//*[@text='Rails']");
	
	
}
