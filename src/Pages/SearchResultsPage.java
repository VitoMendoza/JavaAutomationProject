package Pages;

import java.util.ArrayList;
import java.util.List;

import Assertions.SelectorAssertions;
import Assertions.UpworkAssertions;
import Base.Freelancer;
import TestCases.FindFreelancer;

public class SearchResultsPage {
	
	SelectorAssertions selector;
	UpworkAssertions upworkAssertions;
	
	public SearchResultsPage()
	{
		selector = new SelectorAssertions();
		upworkAssertions = new UpworkAssertions();
	}
	
	
//	  # Step made to get and save search results from search results page.
//	  #
//	  # Getting and saving all attributes from the search results in $variables["freelancersList"].
//	  # Log action.
	  public void save_search_results(int stepNum)
	  {
	    System.out.println(stepNum + ". Save search results to compare.");
	    try
	    {
	      int listLength = selector.findElementsByXpath("//*[@id='oContractorResults']/div/section").size();
//	      # step 'I waitfor 1 seconds'
	      List<Freelancer> list = new  ArrayList<Freelancer>();
	      if (listLength>0)
	      {							
//	        # Getting all search results
	    	  for (int i = 1; i <= listLength; i++) {
	
	//	          # Getting freelancer one by one from the page
		          Freelancer freelancer = new Freelancer();
		          freelancer.name = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/h4/a").getText();
		          freelancer.jobTitle = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/div[1]/h4").getText();
		          freelancer.overview = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/div[3]/div/div/p").getText().replaceAll("  "," ");
		          freelancer.country = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/div[2]/div[4]/div/div/strong").getText();
	
	//	          # Getting all skills of each search result
		          int skillListLength = selector.findElementsByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/span").size();
		          String skills = "";
		          if (skillListLength>0)
		          {
		            if (skillListLength == 1)
		              skills = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/span/a/span").getText();
		            else 
		            {
			              for (int j = 1; j <= skillListLength; j++)
			              {
			
	//			                # There is 4 different cases to get the skills
				                if (!selector.findElementsByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/div/article/div[2]/div[4]/div/span["+j+"]/a/span/span").isEmpty())
				                {
				                  String newSkills = "";
				                  newSkills = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/div/article/div[2]/div[4]/div/span["+j+"]/a/span/span").getText();
				                  skills = upworkAssertions.merge_skills(skills, newSkills);
				                }
				                else if (!selector.findElementsByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/div/article/div[2]/div[4]/div/span["+j+"]/a/span/span[1]").isEmpty())
				                {
				                  String newSkills = "";
				                  newSkills = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/div/article/div[2]/div[4]/div/span["+j+"]/a/span/span[1]").getText();
				                  skills = upworkAssertions.merge_skills(skills, newSkills);
				                }
				                else if (!selector.findElementsByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/span["+j+"]/a/span[1]").isEmpty())
				                {
				                  String newSkills = "";
				                  newSkills = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/span["+j+"]/a/span[1]").getText();
				                  skills = upworkAssertions.merge_skills(skills, newSkills);
				                }
				                else
				                {
				                  String newSkills = "";
				                  newSkills = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/span["+j+"]/a/span").getText();
				                  skills = upworkAssertions.merge_skills(skills, newSkills);
				                }		
			          	  }	
	    	  		 }	
		            
		          }
	
		          freelancer.skills = skills;
		          freelancer.rate = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/div[2]/div[1]/div/strong").getText();
		          freelancer.earned = selector.findElementByXpath("//*[@id='oContractorResults']/div/section["+i+"]/div/article/div[2]/div[2]/div[2]/div/span/strong").getText();
	
		          freelancer.containsKeyword = null;
	
		          list.add(freelancer);
	    	  }

//	          # Saving search results list
	          FindFreelancer.globalFreelancerList = list;
	      }
	    }
	    catch (Exception e)
	    {
	      System.out.println("- Update this step.");
	      System.out.println(e.getMessage());
	      System.out.println(e.getStackTrace());
	    }
	  }



//	  # Step made to search the keywork in the search results.
//	  #
//	  # Create 2 Lists of results. Results which contains the keyword and results without the keyword.
//	  # Both lists are saved in $variables.
//	  # Log action.
	  public void search_keyword_in_results(int stepNum)
	  {
		System.out.println(stepNum + ". Make sure at least one attribute (title, overview, skills, etc) of each item (found freelancer) from parsed search results contains `<keyword>` Log in stdout which freelancers and attributes contain `<keyword>` and which do not.");
//	    try
//	    {
//	      # Structures to display results
	      List<Freelancer> matchedFreelancers = new ArrayList<Freelancer>();
	      List<Freelancer> otherFreelancer = new ArrayList<Freelancer>();

//	      # Searching keyword in the freelancers list and saving results in the result lists
	      int sizeList = FindFreelancer.globalFreelancerList.size();	
	      for (int i = 0; i < sizeList; i++) {
	        if (upworkAssertions.verify_keyword(FindFreelancer.globalFreelancerList.get(i), FindFreelancer.globalKeyword))
	        {
	        	FindFreelancer.globalFreelancerList.get(i).containsKeyword = true;
	        	matchedFreelancers.add(FindFreelancer.globalFreelancerList.get(i));
	        }
	        else
	        {
	        	FindFreelancer.globalFreelancerList.get(i).containsKeyword = false;
	        	otherFreelancer.add(FindFreelancer.globalFreelancerList.get(i));
	        }
	      }

	      System.out.println("****** Freelancers MATCHED with search text '" + FindFreelancer.globalKeyword + "' ******");
	      for (int i = 0; i < matchedFreelancers.size(); i++) {
	    	  System.out.println("{");
	    	  System.out.println("Index: " + i);
	    	  System.out.println("Name: " + matchedFreelancers.get(i).name);
	    	  System.out.println("JobTitle: " + matchedFreelancers.get(i).jobTitle);
	    	  System.out.println("Overview: " + matchedFreelancers.get(i).overview);
	    	  System.out.println("Skills: " + matchedFreelancers.get(i).skills);
	    	  System.out.println("Rate: " + matchedFreelancers.get(i).rate);
	    	  System.out.println("Earned: " + matchedFreelancers.get(i).earned);
	    	  System.out.println("ContainsKeyword: " + matchedFreelancers.get(i).containsKeyword);
	    	  System.out.println("}");
	      }
	      System.out.println("--------------------------------------------------"); 

	      System.out.println("****** Freelancers UNMATCHED with search text '" + FindFreelancer.globalKeyword + "' ******");
	      for (int i = 0; i < otherFreelancer.size(); i++) {
	    	  System.out.println("{");
	    	  System.out.println("Index: " + i);
	    	  System.out.println("Name: " + otherFreelancer.get(i).name);
	    	  System.out.println("JobTitle: " + otherFreelancer.get(i).jobTitle);
	    	  System.out.println("Overview: " + otherFreelancer.get(i).overview);
	    	  System.out.println("Skills: " + otherFreelancer.get(i).skills);
	    	  System.out.println("Rate: " + otherFreelancer.get(i).rate);
	    	  System.out.println("Earned: " + otherFreelancer.get(i).earned);
	    	  System.out.println("ContainsKeyword: " + otherFreelancer.get(i).containsKeyword);
	    	  System.out.println("}");
	      }	      
	      System.out.println("--------------------------------------------------"); 
//	    }
//	    catch (Exception e)
//		{
//		      System.out.println("- Update this step.");
//		      System.out.println(e.getMessage());
//		      System.out.println(e.getStackTrace());
//		}

	  }

//
//	  # Select random search result to get in
//	  #
//	  # Generate a random number between 1 and search results length.
//	  # Click on the element by random index.
//	  # Log action.
	  public void getin_random_result(int stepNum)
	  {
		System.out.println(stepNum + ". Click on random freelancer's title.");
	    try
	    {
	      int listLength = selector.findElementsByXpath("//*[@id='oContractorResults']/div/section").size();
	      int selectedIndex = (int) (Math.random() * listLength) + 1;
	      selector.clickByXpath("//*[@id='oContractorResults']/div/section[" + selectedIndex + "]");
	    }
	    catch (Exception e)
		{
	      System.out.println("- Update this step.");
	      System.out.println(e.getMessage());
	      System.out.println(e.getStackTrace());
		}
	  }

}
