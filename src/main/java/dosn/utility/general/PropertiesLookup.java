package dosn.utility.general;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains static methods for accessing details.properties file 
 */
public class PropertiesLookup {
	private static String userProfileUri = null;
	private static String serverUrl = null;
	private static Boolean isFollowSonic = null;
	private static String searchAndPropagateByUsername = null;
	private static String searchAndPropagateByInterests = null;
	private static String searchByUsername = null;
	private static String searchByInterests = null;
	private static String recommendationAndPropagateUri = null;
	private static String recommendationUri = null;
	private static String recommendationStartURI = null;
	private static String guiSearchResponseURI = null;
	private static String guiRecommendationResponseURI = null;
	private static Integer maxHops = null;
	private static Integer maxRequests = null;
	private static Integer maxRuntime = null;
	private static String searchInUserName = null;
	private static String searchInInterests = null;
	
	
	public static String getUserProfileUri() {
		if (userProfileUri == null) {
			userProfileUri = getPropertyValue("user.profile.uri");
		}
		return userProfileUri;
	}

	public static String getServerUrl() {
		if (serverUrl == null) {
			serverUrl = getPropertyValue("server.url");
		}
		return serverUrl;
	}

	public static String getSearchAndPropagateByUsernameUri() {
		if (searchAndPropagateByUsername == null) {
			searchAndPropagateByUsername = getPropertyValue("search.users.searchPropagate.uri");
		}
		return searchAndPropagateByUsername;
	}

	public static String getSearchAndPropagateByInterestsUri() {
		if (searchAndPropagateByInterests == null) {
			searchAndPropagateByInterests = getPropertyValue("search.interests.searchPropagate.uri");
		}
		return searchAndPropagateByInterests;
	}

	public static String getSearchByUsernameUri() {
		if (searchByUsername == null) {
			searchByUsername = getPropertyValue("search.users.search.uri");
		}
		return searchByUsername;
	}

	public static String getSearchByInterestsUri() {
		if (searchByInterests == null) {
			searchByInterests = getPropertyValue("search.interests.search.uri");
		}
		return searchByInterests;
	}

	public static String getRecommendationAndPropagateUri() {
		if (recommendationAndPropagateUri == null) {
			recommendationAndPropagateUri = getPropertyValue("recommendation.recommendPropagate.uri");
		}
		return recommendationAndPropagateUri;
	}

	public static String getRecommendationUri() {
		if (recommendationUri == null) {
			recommendationUri = getPropertyValue("recommendation.recommend.uri");
		}
		return recommendationUri;
	}
	
	public static String getRecommendationStartURI() {
		if (recommendationStartURI == null) {
			recommendationStartURI = getPropertyValue("recommendation.recommendStart.uri");
		}
		return recommendationStartURI;
	}
	
	public static String getGuiSearchResponseUri() {
		if (guiSearchResponseURI == null) {
			guiSearchResponseURI = getPropertyValue("gui.search.response");
		}
		return guiSearchResponseURI;
	}
	
	public static String getGuiRecommendationResponseUri() {
		if (guiRecommendationResponseURI == null) {
			guiRecommendationResponseURI = getPropertyValue("gui.recommend.response");
		}
		return guiRecommendationResponseURI;
	}

	public static Integer getMaxHops() {
		if (maxHops == null) {
			maxHops = Integer.parseInt(getPropertyValue("max.hops"));
		}
		return maxHops;
	}

	public static Boolean isServerFollowSonic() {
		if (isFollowSonic != null)
			return isFollowSonic;
		isFollowSonic = Boolean
				.parseBoolean(getPropertyValue("server.follow_sonic"));
		return isFollowSonic;
	}

	private static String getPropertyValue(String propertyName) {
		Properties propertyFile = new Properties();
		try {
			propertyFile.load(PropertiesLookup.class
					.getResourceAsStream("/details.properties"));
			return propertyFile.getProperty(propertyName);
		} catch (FileNotFoundException e) {
			System.err.println("details.properties is not exist!!!");
		} catch (IOException e) {
			System.err
					.println("details.properties can't be loaded as properties file!!!");
		}
		return propertyFile.getProperty(propertyName);
	}

	public static String getSearchInInterests() {
		if(searchInInterests == null){
			searchInInterests = getPropertyValue("search.in.interests");
		}
		return searchInInterests;
	}

	public static String getSearchInUserName() {
		if(searchInUserName == null){
			searchInUserName = getPropertyValue("search.in.username");
		}
		
		return searchInUserName;
	}

	public static Integer getMaxRequests() {
		if(maxRequests == null){
			maxRequests = Integer.parseInt(getPropertyValue("maxrequest.hop"));
		}
		return maxRequests;
	}

	public static Integer getMaxRuntime() {
		if(maxRuntime == null){
			maxRuntime = Integer.parseInt(getPropertyValue("search.recommendation.timeout"));
		}
		return maxRuntime;
	}

	

	


}
