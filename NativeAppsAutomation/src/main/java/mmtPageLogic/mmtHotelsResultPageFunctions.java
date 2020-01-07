package mmtPageLogic;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class mmtHotelsResultPageFunctions extends mmtObjectRepo {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	public mmtHotelsResultPageFunctions(AppiumDriver<MobileElement> driver,WebDriverWait wait ) {
		this.driver=driver;
		this.wait=wait;
	}
	
	public void selectPremiumCategory() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TouchAction t = new TouchAction(driver);
		while(driver.findElements(premiumCategory).isEmpty()) {
		t.press(PointOption.point(1400, 540))
		.waitAction().moveTo(PointOption.point(300, 540))
		.release().perform();
		}
		driver.findElement(premiumCategory).click();
	}
	
	public void sortByHighPriceToLowPrice() {
		wait.until(ExpectedConditions.elementToBeClickable(sortAndFilter)).click();
		wait.until(ExpectedConditions.elementToBeClickable(sort_highToLow)).click();
		
	}
	
	public HashMap<String,String> fetchHighestPriceHotel() {
		List<MobileElement> hotelnames = driver.findElements(hotelNames);
		List<MobileElement> hotelprices = driver.findElements(hotelPrices);
		HashMap<String,String> result = new HashMap<String,String>();
		
		for(int i=0;i<2;i++) {
			result.put(hotelnames.get(i).getText(), hotelprices.get(i).getText().substring(1));
		}
		
		return result;
		
	}
}
