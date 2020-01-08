package mmtPageLogic;

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
	
	public mmtHotelBookingPageFunctions(AppiumDriver<MobileElement> driver,WebDriverWait wait ) {
		this.driver=driver;
		this.wait=wait;
	}
	
	public void setHotelDestination(String desName) {
		wait.until(ExpectedConditions.elementToBeClickable(hotelDestination)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchCity)).sendKeys(desName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(firstSuggestion)).click();
	}
	
	public void setCheckInDate(String MonYear, String Date) {
		wait.until(ExpectedConditions.elementToBeClickable(checkInDate)).click();
		
		//Shift the focus to desired month
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.makemytrip:id/cv_pager\"))"
				+ ".scrollIntoView(new UiSelector().textContains(\""+MonYear+"\")"
				+ ".instance(0))"));
		
		TouchAction t = new TouchAction(driver);
		//Shift the focus to particular date from the month and keep scrolling until date is visible
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).isEmpty()) {
			System.out.println("Searching for Checkin Date. Scrolling Again.");
			t.press(PointOption.point(0, 1200)).waitAction().moveTo(PointOption.point(0,600)).release().perform();
		}
		
		//Click on Date
		System.out.println("Out of While loop. Checkin date found. Clicking.");
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).click();
		
		driver.findElement(okButton).click();
		
	}
	
	public void setCheckOutDate(String MonYear, String Date) {
		wait.until(ExpectedConditions.elementToBeClickable(checkOutDate)).click();
		
		//Shift the focus to desired month
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.makemytrip:id/cv_pager\"))"
				+ ".scrollIntoView(new UiSelector().textContains(\""+MonYear+"\")"
				+ ".instance(0))"));
		
		TouchAction t = new TouchAction(driver);
		//Shift the focus to particular date from the month and keep scrolling until date is visible
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+MonYear+"']"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/parent::android.widget.LinearLayout"
				+ "/descendant::android.widget.CheckedTextView[@text='"+Date+"']")).isEmpty()) {
			System.out.println("Searching for Checkin Date. Scrolling Again.");
			t.press(PointOption.point(0, 1200)).waitAction().moveTo(PointOption.point(0,600)).release().perform();
		}
		
		//Click on Date
		System.out.println("Out of While loop. Checkin date found. Clicking.");
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
			String errorMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
			System.out.println("Cannot select this date. Error Message : "+errorMessage);
		}
		driver.findElement(okButton).click();
		
	}
	
	public void setNumberOfAdults(int numberofAdults) {
		wait.until(ExpectedConditions.elementToBeClickable(numberOfAdults)).click();
		if(numberofAdults>2) {
			int i=2;
			while(i<numberofAdults) {
				wait.until(ExpectedConditions.elementToBeClickable(addAdults)).click();
				if(!driver.findElements(By.xpath("//android.widget.Toast")).isEmpty()) {
					System.out.println("Can't add more guests. Error Message : "+driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name"));
					break;
				}
				i++;
			}
		}
		else {
			System.out.println("No configuration required for adults.");
		}
		
		driver.findElement(DoneButton).click();
			
	}
	
	public void childrenConfiguration(int numberofChild) {
		
		wait.until(ExpectedConditions.elementToBeClickable(numberOfChildren)).click();
		if(numberofChild>0) {
			int i=0;
			while(i<numberofChild) {
				wait.until(ExpectedConditions.elementToBeClickable(addChild)).click();
				i++;
			}
		}
		else {
			System.out.println("No configuration required for children.");
		}
		
		
		driver.findElement(DoneButton).click();
	}
	
	public void clickOnSearchHotels() {
		wait.until(ExpectedConditions.elementToBeClickable(searchHotelsButton)).click();
	}
	
	public void closeBanner() {
		
		if(!wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mmtAssuredBanner)).isEmpty()) {
			driver.findElement(mmtAssuredBanner).click();
		}
	}

	public void closeHelper() {
		if(!wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helperPopUp)).isEmpty()) {
			driver.findElement(helperPopUp).click();
		}
		
	}
	
	public void getToastMessage() {
		String errorMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		System.out.println("Toast Message is : "+errorMessage);
	}
}
