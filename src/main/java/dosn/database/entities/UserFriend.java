package dosn.database.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the T_USER_FRIEND database table.
 * 
 */
@Entity
@Table(name="T_USER_FRIEND")
public class UserFriend implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFriendPK id;

	@Column(name="friendship_date")
	private Timestamp friendshipDate;

	//bi-directional many-to-one association to Friend
    @ManyToOne
	@JoinColumn(name="friend_id", nullable=false, insertable=false, updatable=false)
	private Friend TFriend;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="user_id", nullable=false, insertable=false, updatable=false)
	private User TUser;

    public UserFriend() {
    }

	public UserFriendPK getId() {
		return this.id;
	}

	public void setId(UserFriendPK id) {
		this.id = id;
	}
	
	public Timestamp getFriendshipDate() {
		return this.friendshipDate;
	}

	public void setFriendshipDate(Timestamp friendshipDate) {
		this.friendshipDate = friendshipDate;
	}

	public Friend getTFriend() {
		return this.TFriend;
	}

	public void setTFriend(Friend TFriend) {
		this.TFriend = TFriend;
	}
	
	public User getTUser() {
		return this.TUser;
	}

	public void setTUser(User TUser) {
		this.TUser = TUser;
	}
	
}