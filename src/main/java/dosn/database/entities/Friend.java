package dosn.database.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the T_FRIEND database table.
 * 
 */
@Entity
@Table(name="T_FRIEND")
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="friend_id", unique=true, nullable=false)
	private String friendId;

	@Column(length=100)
	private String friend_GID;

	@Column(name="friend_location", length=100)
	private String friendLocation;

	@Column(name="friend_name", nullable=false, length=45)
	private String friendName;

	//bi-directional many-to-one association to UserFriend
	@OneToMany(mappedBy="TFriend")
	private Set<UserFriend> TUserFriends;

    public Friend() {
    }

	public String getFriendId() {
		return this.friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getFriend_GID() {
		return this.friend_GID;
	}

	public void setFriend_GID(String friend_GID) {
		this.friend_GID = friend_GID;
	}

	public String getFriendLocation() {
		return this.friendLocation;
	}

	public void setFriendLocation(String friendLocation) {
		this.friendLocation = friendLocation;
	}

	public String getFriendName() {
		return this.friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public Set<UserFriend> getTUserFriends() {
		return this.TUserFriends;
	}

	public void setTUserFriends(Set<UserFriend> TUserFriends) {
		this.TUserFriends = TUserFriends;
	}
	
}