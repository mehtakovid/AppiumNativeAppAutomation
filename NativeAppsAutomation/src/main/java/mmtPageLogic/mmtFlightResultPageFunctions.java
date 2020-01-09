package mmtPageLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class mmtFlightResultPageFunctions extends mmtObjectRepo {
	
	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	public Logger log;
	
	public mmtFlightResultPageFunctions(AppiumDriver<MobileElement> driver, WebDriverWait wait, Logger log) {
		this.driver=driver;
		this.wait=wait;
		this.log=log;
	}
	
	public Map<String,String> flightDetails(String depCity, String arrCity){
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstSearchResultVisibility));
		String depCityInResults = driver.findElement(departureCityValidation).getText();
		Assert.assertEquals(depCityInResults, depCity);
		String arrCityInResults = driver.findElement(arrivalCityValidation).getText();
		Assert.assertEquals(arrCityInResults, arrCity);	
		System.out.println("Results are shown for Correct Destination as provided by user.");
		HashMap<String, String> flightDetails = new HashMap<String, String>();
		List<String> flightDetailsList = new ArrayList<String>();
		List<MobileElement> depTime = driver.findElements(departureTime);
		List<MobileElement> arrTime = driver.findElements(arrivalTime);
		List<MobileElement> prices = driver.findElements(flightPrice);
		for(int i=0;i<3;i++) {
			flightDetailsList.add(depTime.get(i).getText());
			flightDetailsList.add(arrTime.get(i).getText());
			flightDetailsList.add(prices.get(i).getText().substring(1));
			
		}
		System.out.println(flightDetailsList);
		int i=0;
		while(i<flightDetailsList.size()) {
			System.out.println("Adding flight details into Map");
		int k=i+1;
		int j= i+2;
		flightDetails.put(flightDetailsList.get(i)+" - "+flightDetailsList.get(k)+" ", flightDetailsList.get(j));
		i=j+1;
		}
		
		return flightDetails;
	}
	
	public void totalFlightCost() {
		wait.until(ExpectedConditions.elementToBeClickable(firstSearchResultVisibility)).click();
		String fareWithoutInsurance = wait.until(ExpectedConditions.visibilityOfElementLocated(totalFare)).getText().substring(6);
		driver.findElement(selectInsurance).click();
		String fareWithInsurance = wait.until(ExpectedConditions.visibilityOfElementLocated(totalFare)).getText().substring(6);
		System.out.println("The total fare of the cheapest flight for all passengers will be INR "+fareWithoutInsurance+" without insurance and INR "+fareWithInsurance+" with insurance.");
	}

}
