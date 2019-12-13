package mmtPageLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
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
	
	public void checkForUnwantedPopUps() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(popUpClose).click();
		
	}
	
	public void openRailBooking() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
//				+ ".resourceId(\"com.makemytrip:id/rvHomePageIcon\"))"
//				+ ".scrollIntoView(new UiSelector().textMatches(\"Gift Cards\")"
//				+ ".instance(0));"));
//		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
//				+ ".scrollable(true).instance(0)).scrollIntoView(new UiSelector()"
//				+ ".resourceId(\"com.makemytrip:id/rvHomePageIcon\")"
//				+ ".instance(3));"));
		
		WebElement eleOne = driver.findElement(By.xpath("//*[@text='Flights']"));
		WebElement eleTwo = driver.findElement(By.xpath("//*[@text='Cabs']"));
		TouchAction t = new TouchAction(driver);
		t.longPress(longPressOptions().withElement(element(eleTwo))
				.withDuration(ofSeconds(8))).moveTo(element(eleOne))
					.release().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(Rails)).click();
		
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(RailsAssertion)).isDisplayed(),true);
		
	}
	
	/****
	 * To be deprecated
	 */
	public void getGiftCardNumbers() {
		WebElement oneEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.View)[4]")));
		WebElement twoEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.View)[1]")));
		TouchAction t = new TouchAction(driver);
		t.longPress(longPressOptions().withElement(element(twoEle))
				.withDuration(ofSeconds(8))).moveTo(element(oneEle))
					.release().perform();
		
	
	}
	
}
