package mmtPageLogic;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class mmtHotelsResultPageFunctions extends mmtObjectRepo {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	public Logger log;
	
	public mmtHotelsResultPageFunctions(AppiumDriver<MobileElement> driver,WebDriverWait wait, Logger log ) {
		this.driver=driver;
		this.wait=wait;
		this.log=log;
	}
	
	public void selectPremiumCategory() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Wait completed. Searching for premium.");
		TouchAction t = new TouchAction(driver);
		while(driver.findElements(premiumCategory).isEmpty()) {
		t.press(PointOption.point(1400, 540))
		.waitAction().moveTo(PointOption.point(300, 540))
		.release().perform();
		log.info("Scrolling for Premium category.");
		}
		
		driver.findElement(premiumCategory).click();
		log.info("clicked on Premium category for hotels.");
	}
	
	public void sortByHighPriceToLowPrice() {
		log.info("Looking for sort and filter option.");
		wait.until(ExpectedConditions.elementToBeClickable(sortAndFilter)).click();
		wait.until(ExpectedConditions.elementToBeClickable(sort_highToLow)).click();
		log.info("Adjusted filter to sort hotels from price high to low.");
		
	}
	
	public HashMap<String,String> fetchHighestPriceHotel() {
		List<MobileElement> hotelnames = driver.findElements(hotelNames);
		List<MobileElement> hotelprices = driver.findElements(hotelPrices);
		HashMap<String,String> result = new HashMap<String,String>();
		log.info("Adding the results in the map.");
		for(int i=0;i<2;i++) {
			result.put(hotelnames.get(i).getText(), hotelprices.get(i).getText().substring(1));
		}
		log.info("resulting the result for the priciest hotels.");
		return result;
		
	}
}
