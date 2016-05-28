package dosn.utility.json;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SRRequestJSON {

	private List<InterestJSON> interests = null;
	private String username = null;
	private String senderServerAddress = null;
	private UUID messageUID = null;
	private Integer maxHops = null;

	public SRRequestJSON(UUID messageUID) {
		super();
		interests = new ArrayList<InterestJSON>();
		this.messageUID = messageUID;
	}

	public SRRequestJSON(List<InterestJSON> interests,
			String senderServerAddress, Integer maxHops, UUID messageUID) {
		super();
		this.interests = interests;
		this.senderServerAddress = senderServerAddress;
		this.maxHops = maxHops;
		this.messageUID = messageUID;
	}

	public SRRequestJSON(String username, String senderServerAddress,
			Integer maxHops, UUID messageUID) {
		super();
		this.username = username;
		this.senderServerAddress = senderServerAddress;
		this.maxHops = maxHops;
		this.messageUID = messageUID;
	}

	public List<InterestJSON> getInterests() {
		return interests;
	}

	public void setInterests(List<InterestJSON> interests) {
		this.interests = interests;
	}

	public void addInterest(InterestJSON interest) {
		getInterests().add(interest);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenderServerAddress() {
		return senderServerAddress;
	}

	public void setSenderServerAddress(String senderServerAddress) {
		this.senderServerAddress = senderServerAddress;
	}

	public UUID getMessageUID() {
		return messageUID;
	}

	public void setMessageUID(UUID messageUID) {
		this.messageUID = messageUID;
	}

	public Integer getMaxHops() {
		return maxHops;
	}

	public void setMaxHops(Integer maxHops) {
		this.maxHops = maxHops;
	}

}
