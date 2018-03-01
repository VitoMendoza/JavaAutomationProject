package Pages;

import org.openqa.selenium.WebElement;

import Assertions.*;
import TestCases.FindFreelancer;

public class MainPage //extends TestBase 
{
	SelectorAssertions selector;
	
//	Constructor
	public MainPage()
	{
		selector = new SelectorAssertions();
	}
	
	
//	  # Step to choose Find Freelancers option in the search field.
//	  #
//	  # Click on down arrow and click on Find Freelancers option.
//	  # Log action.
	  public void select_search_freelancer(int stepNum)
	  {
		try
		{
			System.out.println (stepNum + ". Focus onto 'Find freelancers'.");
			selector.clickByXpath("//*[@id=\"visitor-nav\"]/div[1]/form/div/div/div[2]/span");	
			selector.clickByXpath("//*[@id=\"visitor-nav\"]/div[1]/form/div/ul/li[1]/a");
		}
	    catch (Exception e)
	    {
		    System.out.println("- Error: please check this step or update it.");
		    System.out.println(e.getMessage());
		    System.out.println(e.getStackTrace());
	    }
	  }

//	  # Step used to press enter in to search field.
//	  #
//	  # ID for search field is defined as "q".
	  public void press_search_submit(WebElement element)
	  {
		  selector.keyPerformEnter(element);
	  }


//	  # Insert keyword in to search field and submit.
//	  #
//	  # I save the keyword in a global variable to use later.
//	  # Log action.
	  public void insert_text_search_field(String value, int stepNum)
	  {
		  System.out.println (stepNum + ". Insert '"+ value +"' into the search input right from the dropdown and submit it (press enter).");
		  WebElement element = selector.findElementById("q");
		  selector.sendKeysById("q", value);
		  selector.keyPerformEnter(element);
		  FindFreelancer.globalKeyword = value;
	  }

}
