package mmtPageLogic;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import static io.appium.java_client.touch.offset.PointOption.point;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.experitest.appium.SeeTestClient;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

public class mmtFlightBookingPageFunctions extends mmtObjectRepo {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	public mmtFlightBookingPageFunctions(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	
	public void enterFromCity(String departureCity) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(fromCity)).click();
		wait.until(ExpectedConditions.elementToBeClickable(CityTextBox)).click();
		driver.findElement(CityTextBox).sendKeys(departureCity);
		try {
			Thread.sleep(10000);
		}
		catch(Exception e) {
			
		}
		driver.findElement(CityFirstSuggestion).click();
		
	}
	
	public void enterToCity(String arrivalCity) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(toCity)).click();
		wait.until(ExpectedConditions.elementToBeClickable(CityTextBox)).click();
		driver.findElement(CityTextBox).sendKeys(arrivalCity);
		try {
			Thread.sleep(10000);
		}
		catch(Exception e) {
			
		}
		driver.findElement(CityFirstSuggestion).click();
		
	}
	
	/****Documentation
	 * Now We have three approaches for Scrolling in Total
	 * 1. Element to Element Scroll using TouchAction Class(Works on horizontal Scroll as well)
	 * 2. Co-ordinates scroll using Touch Action Class
	 * 3. Using AndroidUiAutomator and UI Scrollable (Applicable Only when searching for visible text)
	 */
	
	/***
	 * Needs to be updated taking user input and Scrolling the calendar
	 * @param Date
	 */
	public void selectDate(String MonYear, String Date) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(departureDate)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TouchAction t = new TouchAction(driver);
		
		/****
		 * Approach 1 - Element to Element Scroll
		 */
//		WebElement eleOne = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']"));
//		WebElement eleTwo = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='2']"));
//		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+MonYear+"']")).isEmpty()) {
//			System.out.println("Inside While. Scroll Again");
//			t.longPress(longPressOptions().withElement(element(eleTwo)).withDuration(ofSeconds(6)))
//			.moveTo(element(eleOne)).release().perform();
//		}
		
		
		
		/****
		 * Approach 3 : Uses visible text to 
		 * keep scrolling until text is found
		 */
		
		//This moves the focus to desired month 
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.makemytrip:id/returnCalendarView\"))"
				+ ".scrollIntoView(new UiSelector()"
				+ ".textContains(\""+MonYear+"\")"
				+ ".instance(0))"));
		System.out.println("Moved the focus to desired month");
		
//		System.out.println("Let's see the power of seetest.");
//		SeeTestClient seetest = new SeeTestClient(driver);
//		seetest.click("NATIVE", "xpath=//*[@text='4']", 0, 5);
//		System.out.println("Click using seetest");
//		seetest.swipeWhileNotFound("Down", 0, 10000, "NATIVE", "xpath=//android.widget.TextView[@text='"+MonYear+"']/parent::android.widget.LinearLayout/parent::android.widget.LinearLayout/following-sibling::android.widget.LinearLayout/android.widget.CheckedTextView[@text='"+Date+"']", 1, 1000, 10, true);
//		System.out.println("Did it work ?");
//		
		
		/**
		 * Approach 2 :
		 * Scrolls the co-ordinates until
		 * element is found
		 */
			
		//Scrolls till it finds the date 
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/following-sibling::android.widget.LinearLayout"
				+ "/android.widget.CheckedTextView[@text='"+Date+"']")).isEmpty()) {
			
			System.out.println("Element not found, Will Continue to scroll.");
			t.press(PointOption.point(0, 1200))
			.waitAction().moveTo(PointOption.point(0, 600))
			.waitAction().release().perform();
		}
		
		System.out.println("Out of While Loop. Element Found. Click to be attempted.");
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/following-sibling::android.widget.LinearLayout"
				+ "/android.widget.CheckedTextView[@text='"+Date+"']")).click();
		driver.findElement(okButton).click();
	}
	
	public void clickOnSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton)).click();
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(flightOptionsAssertion)).isDisplayed(),true);
	}
	
	public void numberOfTraveller(int Adults, int Children, int infants) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(passengerConfig)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Logic to Configure Adult Passengers
		if(Adults>1) {
			int i=1;
			while(i<Adults) {
				System.out.println("Adding One Adult Passenger.");
				driver.findElement(addAdultPassenger).click();
				i++;
			}
		}
		else {
			System.out.println("No Adult Configuration Required.");
		}
		
		if(Children>0) {
			int i=0;
			while(i<Children) {
				System.out.println("Adding One Child Passenger");
				driver.findElement(addChildPassenger).click();
				i++;
			}
		}
		else {
			System.out.println("No Child Configuration Required.");
		}
		
		if(infants>0) {
			int i=0;
			while(i<infants) {
				System.out.println("Adding One Infant Passenger");
				driver.findElement(addInfantPassenger).click();
				i++;
			}
		}
		else {
			System.out.println("No Infant Configuration Required.");
		}
		
		driver.findElement(OkButton_PassengerConfig).click();
	}
	
	public void travelClassPreference(String travelClass) {
		wait.until(ExpectedConditions.elementToBeClickable(travelClassPreference)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(travelClass.equalsIgnoreCase("Economy Class")) {
			driver.findElement(economyClass).click();
		}
		else if(travelClass.equalsIgnoreCase("Premium Economy")) {
			driver.findElement(premiumEconomy).click();
		}
		else if(travelClass.equalsIgnoreCase("Business Class")) {
			driver.findElement(businessClass).click();
		}
	}
}
