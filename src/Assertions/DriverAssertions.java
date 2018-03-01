package Assertions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverAssertions {
	
    public static WebDriver dr;
	
	public DriverAssertions()
	{

	}
	
//	# Selenium::WebDriver::Chrome.driver_path = ""
//	# caps = Selenium::WebDriver::Remote::Capabilities.chrome(:chrome_options => {detach: true})
//	# $driver = Selenium::WebDriver.for :chrome, desired_capabilities: caps 
//	# driver.get ("https://www.upwork.com")
//
//	# Select one browser to run the test
//	# 
//	# This step is required to run any test. Make sure to have the driver for all browsers.
//	# It should be executed in Backgrond section.
//	# @param browser [String] Name of the browser using lowercase
	 public void runBrowser(String browser)
	 {
//	 	puts "1. Run '#{browser}'."
		 System.out.println (1 + ". Run '" + browser + "'.");

		 switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver","src\\Drivers\\chromedriver.exe");
				dr = new ChromeDriver();
				break;
			case "firefox":
			    System.setProperty("webdriver.firefox.driver","src\\Drivers\\geckodriver.exe");
			    dr = new FirefoxDriver();
			    break;
			default:
				break;
			}
		 maximize();
		 
//	 	if ("browser" == "chrome")
//	 		Selenium::WebDriver::Chrome.driver_path = "./Drivers/chromedriver.exe"
//			caps = Selenium::WebDriver::Remote::Capabilities.chrome(:chrome_options => {detach: true})
//			$driver = Selenium::WebDriver.for :chrome, desired_capabilities: caps 
//			
//		elsif "#{browser}" == "firefox"
//			Selenium::WebDriver::Firefox.driver_path = "./Drivers/geckodriver.exe"
//			caps = Selenium::WebDriver::Remote::Capabilities.firefox(:firefox_options => {detach: true})
//			$driver = Selenium::WebDriver.for :firefox, desired_capabilities: caps 
//			
//		elsif "#{browser}" == "explorer"
//			# Selenium::WebDriver::Chrome.driver_path = "../Drivers/chromedriver.exe"
//			caps = Selenium::WebDriver::Remote::Capabilities.explorer(:explorer_options => {detach: true})
//			$driver = Selenium::WebDriver.for :explorer, desired_capabilities: caps
//			
//		elsif "#{browser}" == "opera"
//			# Selenium::WebDriver::Chrome.driver_path = "../Drivers/chromedriver.exe"
//			caps = Selenium::WebDriver::Remote::Capabilities.opera(:opera_options => {detach: true})
//			$driver = Selenium::WebDriver.for :opera, desired_capabilities: caps
//			
//		elsif
//			caps = Selenium::WebDriver::Remote::Capabilities.poltergeist(:poltergeist_options => {detach: true})
//			$driver = Selenium::WebDriver.for :poltergeist, desired_capabilities: caps
//		    
//		end
//		# browser = Capybara.current_session.driver.browser
//		$driver.manage.window.maximize
	 }


//	# Clear or delete browser cache
//	# 
//	# Get the actual browser and clear or delete cookies, it depends web driver.
	public void clearCookies()
	{
//		puts "2. Clear browser cookies."
		System.out.println (2 + ". Clear browser cookies.");
		try
		{
			dr.manage().deleteAllCookies();
		}
		catch (Exception e)
		{
//			 print "Don't know how to clear cookies. Weird driver?";
		}
		
//		puts "- Cookies deleted"
	}

	public void navigate(String url, int stepNum) 
	{		
		System.out.println (stepNum + ". Go to '" + url + "'.");
		dr.navigate().to(url);		
	}
	
	public void maximize() 
	{
		dr.manage().window().maximize();
	}
	
	public void deleteCookies()
	{
		dr.manage().deleteAllCookies();
	}
	
	public void quit()
	{
		dr.quit();
	}
	
	public void wait(int milisec) throws Exception
	{
		dr.wait(milisec);
	}
	
	public void saveScreenShot() throws IOException
	{
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("src\\Screenshots\\screenshot.png"));
	}
	
	public String getCurrectUrl()
	{
		return dr.getCurrentUrl();
	}

}
