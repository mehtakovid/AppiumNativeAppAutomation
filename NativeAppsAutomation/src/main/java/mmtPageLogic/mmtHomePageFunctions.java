package mmtPageLogic;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class mmtHomePageFunctions extends mmtObjectRepo {
	
	public Logger log;
	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	
	public mmtHomePageFunctions(AppiumDriver<MobileElement> driver, WebDriverWait wait, Logger log) {
		this.driver=driver;
		this.wait=wait;
		this.log=log;
	}
	
	public void clickOnHomePage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomeButton)).click();
	}
	
	public void clickOnFlights() {
		log.info("Looking for flights icon");
		wait.until(ExpectedConditions.visibilityOfElementLocated(flightsIcon)).click();
		log.info("Clicked on Flights");
	}
	
	public void clickOnHotels() {
		log.info("Waiting for Hotel Icon's Visibility.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(hotelsIcon)).click();
		log.info("Clicked on Hotel Icon.");
	}
	
	public void checkForUnwantedPopUps() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Wait Completed. Looking for Popup.");
		if(!driver.findElements(popUpClose).isEmpty()) {
			driver.findElement(popUpClose).click();
			log.info("Found the popup. Closed the PopUp.");
		}
		else {
			log.info("No Popup Found.Execution will continue further.");
		}

	}
	
	public void openRailBooking() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Wait Completed. Will start scrolling for Rails option now.");
		TouchAction t = new TouchAction(driver);
		while(driver.findElements(Rails).isEmpty()){
			log.info("Scrolling for Rails");
			t.press(PointOption.point(1200,450)).waitAction().moveTo(PointOption.point(600,450)).release().perform();
		}
		log.info("Found Rails option as per logic.");
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(RailsAssertion)).isDisplayed(),true);
		
	}
	
	
	public void getTravelBlogDetails(String Name) {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Wait Completed. Looking for Travel Blog Header.");
		if(driver.findElements(travelBlogHeading).isEmpty()) {
			log.error("Travel Blog Section does not exist. Execution will be aborted");
			System.exit(1);
		}
		log.info("Travel Blog Header found. Scrolling will now begin for specific blog.");
		TouchAction t = new TouchAction(driver);
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+Name+"']")).isEmpty()) {
			t.press(PointOption.point(1200,1900)).waitAction().moveTo(PointOption.point(300, 1900)).release().perform();
			log.info("scrolling for specific Travel blog.");
		}
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+Name+"']")).click();	
		log.info("Clicked on specific travel blog.");
		String BlogTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+Name+"']"))).getText();
		Assert.assertEquals(BlogTitle, Name);
	}
	
}
