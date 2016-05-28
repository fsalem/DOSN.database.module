package dosn.utility.json;

public class UserRecommendationJSON implements Comparable<UserRecommendationJSON>{

	
	private String userId;
	private String username;
	private Double similarityScore;
	
	
	
	public UserRecommendationJSON(){
		super();
	}
	
	public UserRecommendationJSON(String userId, String username,
			Double similarityScore) {
		super();
		this.userId = userId;
		this.username = username;
		this.similarityScore = similarityScore;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getSimilarityScore() {
		return similarityScore;
	}
	public void setSimilarityScore(Double similarityScore) {
		this.similarityScore = similarityScore;
	}
	
	
	
	
	@Override
	public String toString() {
		return "UserRecommendationJSON [userId=" + userId + ", username="
				+ username + ", similarityScore=" + similarityScore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((similarityScore == null) ? 0 : similarityScore.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRecommendationJSON other = (UserRecommendationJSON) obj;
		if (similarityScore == null) {
			if (other.similarityScore != null)
				return false;
		} else if (!similarityScore.equals(other.similarityScore))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int compareTo(UserRecommendationJSON o) {
		if(this.getSimilarityScore()< o.getSimilarityScore()){
			return -1;
		}else{
			return 1;
		}
	}
	
	
}
