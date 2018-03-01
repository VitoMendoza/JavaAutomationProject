package TestCases;

import java.util.ArrayList;
import java.util.List;

import Assertions.DriverAssertions;
import Base.Freelancer;
import Pages.MainPage;
import Pages.ResultProfilePage;
import Pages.SearchResultsPage;

public class FindFreelancer {
	
	public static Freelancer globalFreelancer = new Freelancer();
	public static List<Freelancer> globalFreelancerList = new ArrayList<Freelancer>();
	public static String globalKeyword = "";
	public static String globalProfileUrl = "https://www.upwork.com/o/profiles/users";
		
	DriverAssertions driverAssertion;
	MainPage mainPage;
	SearchResultsPage resultsPage;
	ResultProfilePage profilePage;
	
	public FindFreelancer()
	{
		driverAssertion = new DriverAssertions();
		mainPage = new MainPage();
		resultsPage = new SearchResultsPage();
		profilePage = new ResultProfilePage();
	}
	
	public void find_freelancer(String browser, String keyword) throws Exception
	{
//		  main_page = MainPage.new
//        search_results_page = SearchResultsPage.new
//        result_profile_page = ResultProfilePage.new
//        browser_steps = BrowserSteps.new
//
        System.out.println ("- Test '001 Find Freelancer' Started..."); 
		driverAssertion.runBrowser(browser);
		driverAssertion.clearCookies();
		driverAssertion.navigate("https://www.upwork.com", 3);
		mainPage.select_search_freelancer(4);
		mainPage.insert_text_search_field(keyword, 5);
		Thread.sleep(2000);
		resultsPage.save_search_results(6);
		resultsPage.search_keyword_in_results(7);
		resultsPage.getin_random_result(8);
		Thread.sleep(2000);
		profilePage.verify_profile_page_url(9);
		profilePage.compare_profile_with_results(10);
		profilePage.search_keywork_on_profile(11);
		Thread.sleep(2000);
		driverAssertion.saveScreenShot();
		driverAssertion.quit();
        System.out.println ("- Test Finished Successfully!"); 


	}


}
