package dosn.utility.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import dosn.database.entities.PotentielServer;

public class RResponseJSON {

	private List<UserRecommendationJSON> users;
	private List<String> potentielServers;
	private List<String> visitedServers;
	private String messageUID;
	
	
	public RResponseJSON(){
		super();
		this.users = new ArrayList<UserRecommendationJSON>();
		this.potentielServers = new ArrayList<String>();
		this.visitedServers = new ArrayList<String>();
		this.messageUID = "";
	}
	
	public RResponseJSON(String messageUID){
		super();
		this.users = new ArrayList<UserRecommendationJSON>();
		this.potentielServers = new ArrayList<String>();
		this.visitedServers = new ArrayList<String>();
		this.messageUID = messageUID;
	}
	
	public RResponseJSON(List<UserRecommendationJSON> users,
			List<String> potentielServers, List<String> visitedServers,
			String messageUID) {
		super();
		this.users = users;
		this.potentielServers = potentielServers;
		this.visitedServers = visitedServers;
		this.messageUID = messageUID;
	}
	
	public List<UserJSON> getUserJSON(Integer maxResults,Double minSimilarityScore,Boolean includeFriends){
		List<UserJSON> userJSON = new ArrayList<UserJSON>();
		Collections.sort(users);
		
		for(UserRecommendationJSON user:users){
			if(user.getSimilarityScore()>=minSimilarityScore){
				userJSON.add(new UserJSON(user.getUserId(), user.getUsername()));
				if(userJSON.size()==maxResults){
					continue;
				}
			}
		}
		return userJSON;
		
	}
	
	public List<String> buildPotentialServer(){
		List<String> newPotentialServer = new ArrayList<String>();
		for(String server:potentielServers){
			if(!visitedServers.contains(server) && !newPotentialServer.contains(server)){
				newPotentialServer.add(server);
			}
		}
		return newPotentialServer;
	}
	
	public List<String> buildRandomPotentialServer(Integer number){
		List<String> newPotentialServer = new ArrayList<String>();
		List<String> randomPotentialServer = new ArrayList<String>();
		Random ran = new Random();
		
		for(String server:potentielServers){
			if(!visitedServers.contains(server) && !newPotentialServer.contains(server)){
				newPotentialServer.add(server);
			}
		}
		


		while(!newPotentialServer.isEmpty()){
			int randomInt;
			if(newPotentialServer.size()>1){
				randomInt = ran.nextInt(newPotentialServer.size()-1);
			}else{
				randomInt = 0;
			}	
				
			String server = newPotentialServer.get(randomInt);
			if(!randomPotentialServer.contains(server)){
				randomPotentialServer.add(server);
				newPotentialServer.remove(server);
			}
	
			if(randomPotentialServer.size() == number){
				break;
			}
		}
		
	
		
		
		return randomPotentialServer;
	}
	
	public  void mergeResponse(RResponseJSON response){
		if(response !=null){
			
			//megerUser
			mergeUser(response.getUsers());
			
			//mergePotentialServer
			mergePotentialServer(response.getPotentielServers());
			
			//mergeVisitedServer
			mergeVisitedServer(response.getVisitedServers());
			
			if(messageUID == null || messageUID.isEmpty()){
				messageUID = response.getMessageUID();
			}
			
			
		}
		
	}
	
	public void mergeVisitedServer(List<String> serverToMerge){
		for(String server :serverToMerge){
			if(!visitedServers.contains(server)){
				visitedServers.add(server);
			}
		}
	}
	
	public void mergePotentialServer(List<String> serverToMerge){
		for(String server :serverToMerge){
			if(!potentielServers.contains(server)){
				potentielServers.add(server);
			}
		}
	}
	
	public void mergeUser(List<UserRecommendationJSON> usersToMerge){
		for(UserRecommendationJSON user:usersToMerge){
			//merge User
			if(!users.contains(user)){
				users.add(user);
			}else{
				//update SimilarityScore if needed
				if(users.get(users.indexOf(user)).getSimilarityScore() < user.getSimilarityScore()){
					users.get(users.indexOf(user)).setSimilarityScore(user.getSimilarityScore());
				}
			}
		}
	}
	
	
	public List<UserRecommendationJSON> getUsers() {
		return users;
	}
	public void setUsers(List<UserRecommendationJSON> users) {
		this.users = users;
	}
	public List<String> getPotentielServers() {
		return potentielServers;
	}
	public void setPotentielServers(List<String> potentielServers) {
		this.potentielServers = potentielServers;
	}
	public List<String> getVisitedServers() {
		return visitedServers;
	}
	public void setVisitedServers(List<String> visitedServers) {
		this.visitedServers = visitedServers;
	}
	public String getMessageUID() {
		return messageUID;
	}
	public void setMessageUID(String messageUID) {
		this.messageUID = messageUID;
	}

	@Override
	public String toString() {
		return "RResponseJSON [users=" + users + ", potentielServers="
				+ potentielServers + ", visitedServers=" + visitedServers
				+ ", messageUID=" + messageUID + "]";
	}

	
	
	
	
}
