package mmtPageLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
	
	
	/*****
	 * As suggested by StackOverflow, does not seem to work
	 * require a detail look
	 */
	public  void scrollHorizontally() {

	      int  y = driver.manage().window().getSize().height / 2;
	      System.out.println(y);
	      int start_x = (int) (driver.manage().window().getSize().width * 0.2);
	      System.out.println(start_x);
	      int end_x = (int) (driver.manage().window().getSize().width * 0.8);
	      System.out.println(end_x);
	        TouchAction dragNDrop = new TouchAction(driver)
	                        .press(PointOption.point(start_x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
	                        .moveTo(PointOption.point(end_x, y))
	                        .release();
	        dragNDrop.perform();
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
	
	public void getTravelBlogDetails(String Name) {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(driver.findElements(By.xpath("//android.widget.TextView[@text='"+Name+"']")).isEmpty()) {
			firstScroll();
		}else {
			driver.findElement(By.xpath("//android.widget.TextView[@text='"+Name+"']")).click();	
		}
		
		while(driver.findElements(By.xpath("//android.widget.TextView[@text='"+Name+"']")).isEmpty()) {
			System.out.println("Still not visible.");
			nextScroll();
		}
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+Name+"']")).click();	
		String BlogTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+Name+"']"))).getText();
		Assert.assertEquals(BlogTitle, Name);
	}
	
	public void firstScroll() {
		WebElement eleOne = driver.findElement(By.xpath
				("//android.support.v4.view.ViewPager//android.widget.LinearLayout[1]"));
		
		WebElement eleTwo = driver.findElement(By.xpath("//android.support.v4.view.ViewPager"
				+ "//android.widget.LinearLayout[2]"));
		TouchAction t = new TouchAction(driver);
		t.longPress(longPressOptions().withElement(element(eleTwo)).withDuration(ofSeconds(6)))
		.moveTo(element(eleOne)).release().perform();
	}
	
	public void nextScroll() {
		WebElement eleOne2 = driver.findElement(By.xpath
				("//android.support.v4.view.ViewPager//android.widget.LinearLayout[2]"));
		WebElement eleTwo2 = driver.findElement(By.xpath("//android.support.v4.view.ViewPager"
				+ "//android.widget.LinearLayout[3]"));
		TouchAction t = new TouchAction(driver);
		t.longPress(longPressOptions().withElement(element(eleTwo2)).withDuration(ofSeconds(6)))
		.moveTo(element(eleOne2)).release().perform();
	}
	
}
