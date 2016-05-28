package org.DOSN.database.module;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import dosn.utility.general.Helper;

public class Test extends TestCase {

//	public void testJSONRequest(){
//		List<InterestJSON> interests = new ArrayList<InterestJSON>();
//		interests.add(new InterestJSON("interest1"));
//		interests.add(new InterestJSON("interest2"));
//		interests.add(new InterestJSON("interest3"));
//		
//		SRRequestJSON requestJSON = new SRRequestJSON(interests, null);
//		System.out.println(Helper.getJSONRequest(json));
//	}
	public void testJSONResponse(){
		List<String> userIds =  new ArrayList<String>();
		userIds.add("id1");
		userIds.add("id2");
		userIds.add("id3");
		userIds.add("id5");
		List<String> usernames =  new ArrayList<String>();
		usernames.add("un1");
		usernames.add("un2");
		usernames.add("un3");
		usernames.add("un5");
		//System.out.println(Helper.buildJSONResponse(userIds, usernames,"htpp://server.id"));
	}
}
