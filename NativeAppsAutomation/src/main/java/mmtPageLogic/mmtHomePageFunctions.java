package mmtPageLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class mmtHomePageFunctions extends mmtObjectRepo {
	
	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	
	public mmtHomePageFunctions(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	
	public void clickOnHomePage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomeButton)).click();
	}
	
	public void clickOnFlights() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(flightsIcon)).click();
	}
	
	public void clickOnHotels() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(hotelsIcon)).click();
	}
	
	public void checkForUnwantedPopUps() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!driver.findElements(popUpClose).isEmpty()) {
			driver.findElement(popUpClose).click();
		}
		else {
			System.out.println("No PopUp.");
		}
		
		
	}
	
	public void openRailBooking() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TouchAction t = new TouchAction(driver);
		while(driver.findElements(Rails).isEmpty()){
			t.press(PointOption.point(1200,200)).waitAction().moveTo(PointOption.point(300,200)).release().perform();
		}		
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(RailsAssertion)).isDisplayed(),true);
		
	}
	
	
	public void getTravelBlogDetails(String Name) {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TouchAction t = new TouchAction(driver);
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+Name+"']")).isEmpty()) {
			t.press(PointOption.point(1200,1900)).waitAction().moveTo(PointOption.point(800, 1900)).release().perform();
		}
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+Name+"']")).click();	
		String BlogTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+Name+"']"))).getText();
		Assert.assertEquals(BlogTitle, Name);
	}
	
}
