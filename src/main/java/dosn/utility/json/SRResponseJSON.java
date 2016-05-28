package dosn.utility.json;

import java.util.ArrayList;
import java.util.List;

public class SRResponseJSON {

	private List<UserJSON> users;
	private List<InterestJSON> interests;
	private List<String> potentielServers;
	private String messageUID;

	public SRResponseJSON(String messageUID) {
		super();
		users = new ArrayList<UserJSON>();
		potentielServers = new ArrayList<String>();
		this.messageUID = messageUID;
	}

	public SRResponseJSON(List<UserJSON> users, String messageUID) {
		super();
		this.users = users;
		this.messageUID = messageUID;
	}
	
	public SRResponseJSON(String messageUID,List<InterestJSON> interests) {
		super();
		this.interests = interests;
		this.messageUID = messageUID;
	}

	public SRResponseJSON(List<UserJSON> users, List<String> potentielServers,
			String messageUID) {
		super();
		this.users = users;
		this.potentielServers = potentielServers;
		this.messageUID = messageUID;
	}

	public List<UserJSON> getUsers() {
		if (users == null)
			users = new ArrayList<UserJSON>();
		return users;
	}

	public void setUsers(List<UserJSON> users) {
		this.users = users;
	}

	public List<String> getPotentielServers() {
		if (potentielServers == null)
			potentielServers = new ArrayList<String>();
		return potentielServers;
	}

	public void setPotentielServers(List<String> potentielServers) {
		this.potentielServers = potentielServers;
	}

	public void addPotentielServer(String potentielServer) {
		getPotentielServers().add(potentielServer);
	}

	public void addUser(UserJSON user) {
		getUsers().add(user);
	}

	public String getMessageUID() {
		return messageUID;
	}

	public void setMessageUID(String messageUID) {
		this.messageUID = messageUID;
	}

	public List<InterestJSON> getInterests() {
		return interests;
	}

	public void setInterests(List<InterestJSON> interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		return "SRResponseJSON [users=" + users + ", interests=" + interests
				+ ", potentielServers=" + potentielServers + ", messageUID="
				+ messageUID + "]";
	}
	
	
	
}
