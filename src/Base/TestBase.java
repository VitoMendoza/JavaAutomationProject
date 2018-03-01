package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	 WebDriver dr;
	
	 
	
	
	public void defineBrowser(String browser, WebDriver driver)
	{
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
			dr = new ChromeDriver();
			break;
		case "firefox":
		    System.setProperty("webdriver.firefox.driver",".\\Drivers\\geckodriver.exe");
		    dr = new FirefoxDriver();

		default:
			break;
		}
	}
	
	public void navigate(String url) 
	{		
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
	
	public void close()
	{
		dr.quit();
	}
	
	public void wait(int milisec) throws Exception
	{
		dr.wait(milisec);
	}
	
	

}
