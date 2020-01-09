package mmtPageLogic;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class mmtHotelBookingPageFunctions extends mmtObjectRepo {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	public Logger log;
	
	public mmtHotelBookingPageFunctions(AppiumDriver<MobileElement> driver,WebDriverWait wait, Logger log ) {
		this.driver=driver;
		this.wait=wait;
		this.log=log;
	}
	
	public void setHotelDestination(String desName) {
		log.info("Waiting for City to be clickable.");
		wait.until(ExpectedConditions.elementToBeClickable(hotelDestination)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchCity)).sendKeys(desName);
		log.info("Entered Name of the city.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Wait completed. Clicking on the first suggestion.");
		wait.until(ExpectedConditions.elementToBeClickable(firstSuggestion)).click();
	}
	
	public void setCheckInDate(String MonYear, String Date) {
		log.info("Waiting for checkin date to be clickable.");
		wait.until(ExpectedConditions.elementToBeClickable(checkInDate)).click();
		
		//Shift the focus to desired month
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.makemytrip:id/cv_pager\"))"
				+ ".scrollIntoView(new UiSelector().textContains(\""+MonYear+"\")"
				+ ".instance(0))"));
		log.info("Moved the focus to desired month for selecting the checkin Date.");
		
		TouchAction t = new TouchAction(driver);
		
		//Shift the focus to particular date from the month and keep scrolling until date is visible
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).isEmpty()) {
			log.info("Searching for Checkin Date. Scrolling Again.");
			t.press(PointOption.point(0, 1200)).waitAction().moveTo(PointOption.point(0,600)).release().perform();
		}
		
		//Click on Date
		log.info("Out of While loop. Checkin date found. Clicking.");
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).click();
		
		driver.findElement(okButton).click();
		log.info("Successfully selected the checkin Date." );
	}
	
	public void setCheckOutDate(String MonYear, String Date) {
		wait.until(ExpectedConditions.elementToBeClickable(checkOutDate)).click();
		log.info("Checkout date is now clickable.");
		//Shift the focus to desired month
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.makemytrip:id/cv_pager\"))"
				+ ".scrollIntoView(new UiSelector().textContains(\""+MonYear+"\")"
				+ ".instance(0))"));
		log.info("Moved the focus to desired month for selecting the checkout Date.");
		TouchAction t = new TouchAction(driver);
		//Shift the focus to particular date from the month and keep scrolling until date is visible
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).isEmpty()) {
			log.info("Searching for Checkout Date. Scrolling Again.");
			t.press(PointOption.point(0, 1200)).waitAction().moveTo(PointOption.point(0,600)).release().perform();
		}
		
		//Click on Date
		log.info("Out of While loop. Checkin date found. Clicking.");
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).click();
		if(!driver.findElements(By.xpath("//android.widget.Toast")).isEmpty()) {
			driver.findElement(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
					+ "/parent::android.widget.LinearLayout"
					+ "/parent::android.widget.LinearLayout"
					+ "/parent::android.widget.LinearLayout"
					+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).click();
			log.info("Clicked on date again to check if any toast message is visible.");
			String errorMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
			log.error("Cannot select this date. Error Message : "+errorMessage);
		}
		driver.findElement(okButton).click();
		log.info("Successfully selected the Checkout date.");
	}
	
	public void setNumberOfAdults(String numberofAdultsParam) {
		Integer numberofAdults = Integer.parseInt(numberofAdultsParam);
		wait.until(ExpectedConditions.elementToBeClickable(numberOfAdults)).click();
		if(numberofAdults>2) {
			int i=2;
			while(i<numberofAdults) {
				log.info("Adding adult guest for hotel booking.");
				wait.until(ExpectedConditions.elementToBeClickable(addAdults)).click();
				if(!driver.findElements(By.xpath("//android.widget.Toast")).isEmpty()) {
					log.error("Can't add more guests. Error Message : "+driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name"));
					break;
				}
				i++;
			}
		}
		else {
			log.info("No configuration required for adults.");
		}
		
		driver.findElement(DoneButton).click();
		log.info("Added all the required Adult guest for the booking.");	
	}
	
	public void childrenConfiguration(String numberofChildParam) {
		Integer numberofChild = Integer.parseInt(numberofChildParam);
		wait.until(ExpectedConditions.elementToBeClickable(numberOfChildren)).click();
		if(numberofChild>0) {
			int i=0;
			while(i<numberofChild) {
				log.info("Adding child guest in hotel booking.");
				wait.until(ExpectedConditions.elementToBeClickable(addChild)).click();
				i++;
			}
		}
		else {
			log.info("No configuration required for children.");
		}
		
		driver.findElement(DoneButton).click();
		log.info("Added children in the hotel booking");
	}
	
	public void clickOnSearchHotels() {
		wait.until(ExpectedConditions.elementToBeClickable(searchHotelsButton)).click();
		log.info("Clicked on Search Hotels button.");
	}
	
	public void closeBanner() {
		log.info("Looking for mmt assurance banner. Once found will close it.");
		if(!wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mmtAssuredBanner)).isEmpty()) {
			driver.findElement(mmtAssuredBanner).click();
			log.info("Found the MMT Assurance banner. Closing it.");
		}
	}

	public void closeHelper() {
		if(!wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helperPopUp)).isEmpty()) {
			driver.findElement(helperPopUp).click();
			log.info("Closing the helper text blocking the view of search results.");
		}
		
	}
	
	public void getToastMessage() {
		String errorMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		System.out.println("Toast Message is : "+errorMessage);
	}
}
