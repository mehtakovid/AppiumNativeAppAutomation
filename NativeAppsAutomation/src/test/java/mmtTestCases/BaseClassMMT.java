package mmtTestCases;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

public class BaseClassMMT {
	
	public static AppiumDriver<MobileElement> driver;
	public static WebDriverWait wait;
	
	
	private static boolean root=false;
	
	public static Logger getLogger(Class cls){
		if(root){
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure("log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}

	@Parameters({"deviceName","platformName", "SystemPort"})
	@BeforeMethod
	public void setupAppiumDriver(String deviceName, String platformName, String SystemPort) throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
			caps.setCapability("deviceName", deviceName);
			caps.setCapability("udid", deviceName);
			caps.setCapability("platformName", platformName);
			caps.setCapability("automationName", "UiAutomator2");
			caps.setCapability("appPackage", "com.makemytrip");
			caps.setCapability("appActivity", "com.mmt.travel.app.home.ui.SplashActivity");
			caps.setCapability("app", "C:\\Users\\komehta\\git\\AppiumNativeAppAutomation\\NativeAppsAutomation\\com.makemytrip.7.0.6.apk");
			caps.setCapability("unlockType", "fingerprint");
			caps.setCapability("unlockKey", "1");
			caps.setCapability("systemPort", SystemPort);
			driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			wait = new WebDriverWait(driver,30);
			
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public static void CaptureScreenshot(AppiumDriver<MobileElement> driver, String FilePath) throws Exception {
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(FilePath));
		
	}
	
}
