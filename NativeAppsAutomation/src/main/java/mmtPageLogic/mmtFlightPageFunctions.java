package mmtPageLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class mmtFlightPageFunctions extends mmtObjectRepo {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	public mmtFlightPageFunctions(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	
	public void enterFromCity(String departureCity) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(fromCity)).click();
		wait.until(ExpectedConditions.elementToBeClickable(CityTextBox)).click();
		driver.findElement(CityTextBox).sendKeys(departureCity);
		try {
			Thread.sleep(3000);
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
			Thread.sleep(3000);
		}
		catch(Exception e) {
			
		}
		driver.findElement(CityFirstSuggestion).click();
		
	}
	
	/***
	 * Needs to be updated taking user input and Scrolling the calendar
	 * @param Date
	 */
	public void selectDate(String Date) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(departureDate)).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		driver.findElement(By.id("com.makemytrip:id/calendar_day")).click();
		driver.findElement(okButton).click();
	}
	
	public void clickOnSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton)).click();
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(flightOptionsAssertion)).isDisplayed(),true);
	}
	
	
}
