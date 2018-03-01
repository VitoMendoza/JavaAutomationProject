package Pages;

import Assertions.DriverAssertions;
import Assertions.SelectorAssertions;
import Assertions.UpworkAssertions;
import Base.Freelancer;
import TestCases.FindFreelancer;

public class ResultProfilePage {
	SelectorAssertions selector;
	UpworkAssertions upworkAssertions;
	DriverAssertions driver;
	
	Freelancer freelancerProfile;
	
	public ResultProfilePage()
	{
		selector = new SelectorAssertions();
		driver = new DriverAssertions();
		upworkAssertions = new UpworkAssertions();
		freelancerProfile = new Freelancer();
	}
	
	
//	  # Verify if actual url is profile url
//	  #
//	  # Expect to get in to freelancer's profile.
//	  # Log action.
	  public void verify_profile_page_url(int stepNum)
	  {
		System.out.println(stepNum + ". Get into that freelancer's profile.");
	    if (driver.getCurrectUrl().contains(FindFreelancer.globalProfileUrl))
	    {
	    	System.out.println("- Yes, it is the profile page.");
	    }
	    else
	    {
	    	System.out.println("- It looks the url has change.");	    
	    }
	  }


//	  # This step was made to compare the actual profile with the stored profiles from search results
//	  #
//	  # Stored profiles should be saved in $variables["freelancersList"] and the actual browser page should be freelancer profile
//	  # @return stdout text
//	  # - true: Yes it is, the profile of #{name} contains the keyword '#{keyword}'.
//	  # - false: This profile does not contains the keyword '#{keyword}'.
//	  # - null: There's no saved data to compare.
	  public void compare_profile_with_results(int stepNum)
	  {
	    try
	    {
	      System.out.println(stepNum + " Check that each attribute value is equal to one of those stored inthe structure.");
	      Freelancer freelancer = new Freelancer();
	      

	      if (!selector.findElementsByXpath("//*[@id='optimizely-header-container-default']/div[1]/div[1]/div/div[2]/h2/span/span").isEmpty())
	      {

	    	freelancer.name = selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[1]/div[1]/div/div[2]/h2/span/span").getText();
	    	freelancer.jobTitle = selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[2]/div[1]/h3/span/span[1]").getText();
	    	freelancer.overview = selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[2]/div[2]/o-profile-overview/div/p").getText().replace("\n\n", "\n").replace("\\n", " ").replace("\n", " ").replace("  "," "); 
	    	freelancer.country= selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[1]/div[1]/div/div[2]/div/fe-profile-map/span/ng-transclude/strong[2]").getText();

//	        # Getting all skills from freelancer profile
	        int skillListLength = selector.findElementsByXpath("//*[@id='oProfilePage']/div[1]/div/cfe-profile-skills-integration/div/div/section/div/up-skills-public-viewer/div/a").size();
	        String skills = "";
	        for (int i = 1; i <= skillListLength; i++) {
	          String newSkills = "";
	          newSkills = selector.findElementByXpath("//*[@id='oProfilePage']/div[1]/div/cfe-profile-skills-integration/div/div/section/div/up-skills-public-viewer/div/a[" + i + "]").getText();;
	          skills = upworkAssertions.merge_skills(skills, newSkills);
	        }
	        freelancer.skills = skills;

//	        # There is 2 different cases to get the Rate and Earned from the profile page
	        if (!selector.findElementsByXpath("//*[@id='optimizely-header-container-default']/div[3]/ul/li[1]/div[1]/div/h3/cfe-profile-rate/span/span").isEmpty())
	        {
	          freelancer.rate =  selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[3]/ul/li[1]/div[1]/div/h3/cfe-profile-rate/span/span").getText();
	          freelancer.earned =  selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[3]/ul/li[2]/h3/span").getText();
	        }
	        else
	        {
	          freelancer.rate = selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[4]/ul/li[1]/div[1]/div/h3/cfe-profile-rate/span/span").getText();		            	
	          freelancer.earned = selector.findElementByXpath("//*[@id='optimizely-header-container-default']/div[4]/ul/li[2]/h3/span").getText();
	        }
	      }
	      else
	      {
	        freelancer.name = selector.findElementByXpath("//*[@id='main']/div[2]/div/div/div[3]/div[1]/div[1]/section[1]/div/div/div[1]/div[1]/h2").getText();	        											
	        freelancer.jobTitle= selector.findElementByXpath("//*[@id='main']/div[2]/div/div/div[3]/div[1]/div[1]/section[1]/div/div/div[1]/div[1]/h4").getText();
	        freelancer.overview = selector.findElementByXpath("//*[@id='main']/div[2]/div/div/div[3]/div[1]/div[1]/section[2]/div/span/span[2]").getText().replace("\n\n", "\n").replace("\\n", " ").replace("\n", " ").replace("  "," ");     
	        freelancer.country = selector.findElementByXpath("//*[@id='main']/div[2]/div/div/div[3]/div[1]/div[1]/section[1]/div/div/div[2]/span[3]").getText();
	        freelancer.skills = "";
	        freelancer.rate = selector.findElementByXpath("//*[@id='main']/div[2]/div/div/div[3]/div[1]/div[1]/section[1]/div/div/div[1]/div[2]/h3/span[1]").getText();
	        freelancer.earned = selector.findElementByXpath("//*[@id='main']/div[2]/div/div/div[3]/div[2]/ul/li[6]/span").getText();
	      }

	      freelancer.containsKeyword = null;

//	      # Saving profile information in to $variables["FreelancerProfile"] to be use it later
	      freelancerProfile = freelancer;

//	      # Comparing actual profile and profiles from saved freelancersList
	      if (upworkAssertions.compare_profile(FindFreelancer.globalFreelancerList, freelancerProfile ))
	    	  System.out.println("- Actual profile is EQUAL to one of the stored profiles.");
	      else
	    	  System.out.println("- Actual profile is NOT EQUAL to one of the stored profiles.");
	      
	    }
	    catch (Exception e)
	    {
	      System.out.println("- Update this step.");
	      System.out.println(e.getMessage());
	      System.out.println(e.getLocalizedMessage());
	    }

	  }


//	  # This step was made to search the keyword in the freelancer profile
//	  #
//	  # @return stdout text
//	  # - true: Yes it is, the profile of #{name} contains the keyword '#{keyword}'.
//	  # - false: This profile does not contains the keyword '#{keyword}'.
//	  # - null: There's no saved data to compare.
	  public void search_keywork_on_profile(int stepNum)
	  {
	    try
	    {
	      System.out.println(stepNum + " I check whether at least one attribute contains the keyword.");
//	      keyword = $variables['keyword']
	      String name = freelancerProfile.name;
	      String keyword = FindFreelancer.globalKeyword;
//	      # Searching keyword in $variables["FreelancerProfile"]
	      if (upworkAssertions.verify_keyword(freelancerProfile, keyword))
	          System.out.println("- Yes it is, the profile of '" + name + "' contains the keyword '" + keyword + "'.");
	      else
	    	  System.out.println("- This profile of '" + name + "' does not contains the keyword '" + keyword + "'.");
	       
	    }
	    catch( Exception e)
	    {
	  	      System.out.println("- Update this step.");
	  	      System.out.println(e.getMessage());
	  	      System.out.println(e.getStackTrace());
	    }
	  }
	

}
