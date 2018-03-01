package Assertions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Base.TestBase;

public class SelectorAssertions extends TestBase{


	public SelectorAssertions()
	{
		
	}
	
	public void clickByXpath(String xpath)
	{
		DriverAssertions.dr.findElement(By.xpath(xpath)).click();
	}
	
	public WebElement findElementByXpath(String xpath)
	{
		return DriverAssertions.dr.findElement(By.xpath(xpath));
	}
	
	public List<WebElement> findElementsByXpath(String xpath)
	{
		return DriverAssertions.dr.findElements(By.xpath(xpath));
	}
	
	public void sendKeysByXpath(String xpath, String value)
	{
		DriverAssertions.dr.findElement(By.xpath(xpath)).sendKeys(value);;
	}
		
	public void keyPerformEnter(WebElement element)
	{
		element.sendKeys(Keys.ENTER);
	}
	
	public WebElement findElementById(String id)
	{
		return DriverAssertions.dr.findElement(By.id(id));
	}
	
	public void sendKeysById(String id, String value)
	{
		DriverAssertions.dr.findElement(By.id(id)).sendKeys(value);;
	}
	
	

}
