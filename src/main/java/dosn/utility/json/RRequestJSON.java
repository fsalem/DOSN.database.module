package dosn.utility.json;

import java.util.List;
import java.util.UUID;



public class RRequestJSON {

	
	private String responseURI;
	private List<String> interests;
	private String msgID;
	private String userID;
	private Integer friendLevel;
	private Integer maxFriendLevel;
	private Integer maxResults;
	private Double minSimilarityScore;
	private Boolean includeFriends;
	
	
	
	public RRequestJSON(String responseURI,
			List<String> interests, String msgID, String userID,
			Integer friendLevel, Integer maxFriendLevel) {
		super();
		this.responseURI = responseURI;
		this.interests = interests;
		this.msgID = msgID;
		this.userID = userID;
		this.friendLevel = friendLevel;
		this.maxFriendLevel = maxFriendLevel;
	}
	
	
	
	
	public RRequestJSON(String responseURI, List<String> interests,
			String msgID, String userID, Integer friendLevel,
			Integer maxFriendLevel, Integer maxResults,
			Double minSimilarityScore, Boolean includeFriends) {
		super();
		this.responseURI = responseURI;
		this.interests = interests;
		this.msgID = msgID;
		this.userID = userID;
		this.friendLevel = friendLevel;
		this.maxFriendLevel = maxFriendLevel;
		this.maxResults = maxResults;
		this.minSimilarityScore = minSimilarityScore;
		this.includeFriends = includeFriends;
	}




	public String getResponseURI() {
		return responseURI;
	}
	public void setResponseURI(String responseURI) {
		this.responseURI = responseURI;
	}
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Integer getFriendLevel() {
		return friendLevel;
	}
	public void setFriendLevel(Integer friendLevel) {
		this.friendLevel = friendLevel;
	}
	public Integer getMaxFriendLevel() {
		return maxFriendLevel;
	}
	public void setMaxFriendLevel(Integer maxFriendLevel) {
		this.maxFriendLevel = maxFriendLevel;
	}

	@Override
	public String toString() {
		return "RRequestJSON [responseURI="
				+ responseURI + ", interests=" + interests + ", msgID=" + msgID
				+ ", userID=" + userID + ", friendLevel=" + friendLevel
				+ ", maxFriendLevel=" + maxFriendLevel + "]";
	}


	public Integer getMaxResults() {
		return maxResults;
	}


	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}


	public Double getMinSimilarityScore() {
		return minSimilarityScore;
	}


	public void setMinSimilarityScore(Double minSimilarityScore) {
		this.minSimilarityScore = minSimilarityScore;
	}


	public Boolean getIncludeFriends() {
		return includeFriends;
	}


	public void setIncludeFriends(Boolean includeFriends) {
		this.includeFriends = includeFriends;
	}
	
	
	
	
}
