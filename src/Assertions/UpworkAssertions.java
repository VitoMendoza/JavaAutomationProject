/**
 * 
 */
package Assertions;

import java.util.List;

import Base.Freelancer;

/**
 * @author HP
 *
 */
public class UpworkAssertions {
	
	Freelancer freelancer;
	
	public UpworkAssertions() {
		freelancer = new Freelancer();	
	}
	
	
	
//	   # Search keyword in a given freelancer profile
//	   #
//	   # @param freelancer [Hash] Freelancer profile information 
//	   # @param keyword [String] Keyword to search
//	   # @return result [Boolean]
//	   # - true: [Boolean] Keyword was found
//	   # - false: [Boolean] Keyword was not found
		public boolean verify_keyword( Freelancer freelancer_profile, String keyword ) {

			boolean result = false;
		
			if (freelancer_profile.name.toLowerCase().trim().contains(keyword.trim().toLowerCase())) 
			{
				result = true;
			}	
		
			if (freelancer_profile.jobTitle.toLowerCase().trim().contains(keyword.trim().toLowerCase())) 
			{
				result = true;
			}
		
			if (freelancer_profile.overview.toLowerCase().trim().contains(keyword.trim().toLowerCase())) 
			{
				result = true;
			}
		
			if (freelancer_profile.country.toLowerCase().trim().contains(keyword.trim().toLowerCase())) 
			{
				result = true;
		    }
		
			if (freelancer_profile.skills.toLowerCase().trim().contains(keyword.trim().toLowerCase())) 
			{
			    result = true;
			}
			
			if (freelancer_profile.rate.toLowerCase().trim().contains(keyword.trim().toLowerCase())) 
			{
				result = true;
			}
	
		    if (freelancer_profile.earned.toLowerCase().trim().contains(keyword.trim().toLowerCase())) 
		    {
		        result = true;
			}
	
		    if (result)
		    {
		        freelancer_profile.containsKeyword = true;
		    }else
		    {
		        freelancer_profile.containsKeyword = false;
		    }
	               
	        return result;
		}
		

//		   # Compare attributes between given freelancer profile and stored freelancer profiles.
//		   #
//		   # Using downcase() to efficiently compare the same text.
//		   # @param freelancersStored [Hash] Conteins 1..10 freelancer profiles
//		   # @param freelancerProfile [Hash] Given freelancer profile information
//		   # @return result [Boolean]
//		   # - true: [Boolean] The freelancer profile HAS coincidence
//		   # - false: [Boolean] The freelancer profile HAS NOT coincidence
		    public boolean compare_profile(List<Freelancer> freelancersStored, Freelancer freelancerProfile)
		    {
		        boolean result = true;
		        int i = 0;
		        while (i < freelancersStored.size())
		        {
		            result = true;

		            if (!freelancerProfile.name.toLowerCase().trim().contains(freelancersStored.get(i).name.toLowerCase().trim()))
		            {
		                result = false; 
		            }

		            if (!freelancerProfile.jobTitle.toLowerCase().trim().contains(freelancersStored.get(i).jobTitle.toLowerCase().trim()))
		            {
		                result = false;
		            }

		            if (!freelancerProfile.overview.toLowerCase().trim().contains(freelancersStored.get(i).overview.toLowerCase().trim().replace(" ...", "")))
		            {
		                result = false;

		            }
		            if (!freelancerProfile.country.toLowerCase().trim().contains(freelancersStored.get(i).country.toLowerCase().trim()))
		            {
		                result = false;
		            
		            }
		            if (!freelancerProfile.skills.toLowerCase().trim().contains(freelancersStored.get(i).skills.toLowerCase().trim()))
		            {
		                result = false;
		            }

		            if (!freelancerProfile.rate.toLowerCase().trim().contains(freelancersStored.get(i).rate.toLowerCase().trim()))
		            {
		                result = false;
		            }

		            if (!freelancerProfile.earned.toLowerCase().trim().contains(freelancersStored.get(i).earned.toLowerCase().trim()))
		            {
		                result = false;
		            }

		            if (result)
		                 i = i + freelancersStored.size();
		             

		            i = i + 1;

		    	}

		        return result;
		    }

		
		
		
//		   # Simple method to merge strings from freelancer skills
//		   #
//		   # @param skills [String] Skill list
//		   # @param newSkill [String] New skill to be added to the Skill list
//		   # @return skills [String] Updated Skill list 
		    public String merge_skills( String skills, String newSkill )
		    {
		        if (newSkill != "")
		        {
		            skills = skills.trim() + " " + newSkill.trim();
		        }

		        return skills;
			}

}
