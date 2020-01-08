package mmtTestCases;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

public class BaseClassMMT {
	
	public static AppiumDriver<MobileElement> driver;
	public static WebDriverWait wait;
	
	@BeforeMethod
	public void setupAndroidDriver() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
			caps.setCapability("deviceName", "AndroidDevice");
			caps.setCapability("platformName", "Android");
			caps.setCapability("automationName", "UiAutomator2");
			caps.setCapability("appPackage", "com.makemytrip");
			caps.setCapability("appActivity", "com.mmt.travel.app.home.ui.SplashActivity");
			caps.setCapability("app", "C:\\Users\\komehta\\git\\AppiumNativeAppAutomation\\NativeAppsAutomation\\com.makemytrip.7.0.6.apk");
		
		driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		wait = new WebDriverWait(driver,30);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
