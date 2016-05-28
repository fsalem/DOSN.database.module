package dosn.database.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the T_USER_FRIEND database table.
 * 
 */
@Embeddable
public class UserFriendPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", unique=true, nullable=false)
	private String userId;

	@Column(name="friend_id", unique=true, nullable=false)
	private String friendId;

    public UserFriendPK() {
    }
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFriendId() {
		return this.friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFriendPK)) {
			return false;
		}
		UserFriendPK castOther = (UserFriendPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.friendId.equals(castOther.friendId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.friendId.hashCode();
		
		return hash;
    }
}