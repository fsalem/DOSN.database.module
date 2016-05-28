package dosn.utility.general;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import dosn.utility.json.InterestJSON;
import dosn.utility.json.RRequestJSON;
import dosn.utility.json.RResponseJSON;
import dosn.utility.json.SRRequestJSON;
import dosn.utility.json.SRResponseJSON;
import dosn.utility.json.UserJSON;

/**
 * This class contains some helper functions which facilitate the development
 * like converting java object to JSON and vice versa
 */
public class Helper {

	private Helper() {
	}

	/**
	 * This method should be used only in Search and recommendation modules
	 * 
	 * @param json
	 * @return
	 */
	public static SRRequestJSON getJSONRequest(String json) {
		return new Gson().fromJson(json, SRRequestJSON.class);
	}

	/**
	 * This method should be used only in GUI module
	 * 
	 * @param json
	 * @return
	 */
	public static SRResponseJSON getJSONResponse(String json) {
		return new Gson().fromJson(json, SRResponseJSON.class);
	}

	/**
	 * 
	 * @param json
	 * @return
	 */
	public static RResponseJSON getJSONRecommendationRespone(String json) {
		return new Gson().fromJson(json, RResponseJSON.class);
	}

	/**
	 * This method is to build the JSON String which will be sent to the search
	 * and recommendation modules. This should be called from the GUI module
	 * only
	 * 
	 * @param interests
	 * @param maxHops
	 * @param messageId
	 * @return
	 */
	public static String buildJSONRequest(List<String> interests,
			Integer maxHops, UUID messageId) {
		if (interests == null)
			return null;
		List<InterestJSON> interestJSONs = new ArrayList<InterestJSON>();
		for (String interest : interests) {
			interestJSONs.add(new InterestJSON(interest));
		}
		SRRequestJSON requestJSON = new SRRequestJSON(interestJSONs,
				PropertiesLookup.getServerUrl(), maxHops, messageId);

		return new Gson().toJson(requestJSON);
	}

	/**
	 * 
	 * @param responseURI
	 * @param interests
	 * @param msgID
	 * @param userID
	 * @param friendLevel
	 * @param maxFriendLevel
	 * @return
	 */
	public static String buildJSONRecommendationRequest(String responseURI,
			List<String> interests, String msgID, String userID,
			Integer friendLevel, Integer maxFriendLevel) {

		RRequestJSON requestJSON = new RRequestJSON(responseURI, interests,
				msgID, userID, friendLevel, maxFriendLevel);

		return new Gson().toJson(requestJSON);
	}

	/**
	 * JSON for Recommendation Request
	 * 
	 * @param responseURI
	 * @param interests
	 * @param msgID
	 * @param userID
	 * @param friendLevel
	 * @param maxFriendLevel
	 * @param maxResults
	 * @param minSimilarityScore
	 * @param includeFriends
	 * @return
	 */
	public static String buildJSONRecommendationRequest(String responseURI,
			List<String> interests, String msgID, String userID,
			Integer friendLevel, Integer maxFriendLevel, Integer maxResults,
			Double minSimilarityScore, Boolean includeFriends) {

		RRequestJSON requestJSON = new RRequestJSON(responseURI, interests,
				msgID, userID, friendLevel, maxFriendLevel, maxResults,
				minSimilarityScore, includeFriends);

		return new Gson().toJson(requestJSON);
	}

	/**
	 * This method is to build the JSON String which will be sent to the search
	 * and recommendation modules. This should be called from the GUI module
	 * only
	 * 
	 * @param username
	 * @param maxHops
	 * @return
	 */
	public static String buildJSONRequest(String username, Integer maxHops,
			UUID messageId) {
		if (username == null)
			return null;
		SRRequestJSON requestJSON = new SRRequestJSON(username,
				PropertiesLookup.getServerUrl(), maxHops, messageId);
		return new Gson().toJson(requestJSON);
	}

	/**
	 * This method is to build the JSON String which will be sent to the GUI
	 * module. This should be called from the search and recommendation modules
	 * only
	 * 
	 * @param users
	 * @param messageUID
	 * @return
	 */
	public static String buildJSONResponse(List<UserJSON> users,
			String messageUID) {
		if (users == null)
			return null;
		SRResponseJSON jsonObject = new SRResponseJSON(messageUID);
		for (UserJSON userJSON : users) {
			jsonObject.addUser(userJSON);
		}
		return new Gson().toJson(jsonObject);
	}

	/**
	 * This method is to build the JSON String which will be sent to the GUI
	 * module. This should be called from the search and recommendation modules
	 * only
	 * 
	 * @param users
	 * @param potentielServers
	 * @param messageUID
	 * @return
	 */
	public static String buildJSONResponse(List<UserJSON> users,
			List<String> potentielServers, String messageUID) {
		if (users == null)
			return null;
		SRResponseJSON jsonObject = new SRResponseJSON(messageUID);
		for (UserJSON userJSON : users) {
			jsonObject.addUser(userJSON);
		}
		jsonObject.setPotentielServers(potentielServers);
		return new Gson().toJson(jsonObject);
	}

	/**
	 * Convert List of InterestJSON to List of Strings
	 * 
	 * @param interests
	 * @return
	 */
	public static List<String> convertInterestJSONListToString(
			List<InterestJSON> interests) {
		if (null == interests) {
			return null;
		}
		List<String> interestList = new ArrayList<String>();
		for (InterestJSON json : interests) {
			interestList.add(json.getInterest());
		}
		return interestList;
	}

}
