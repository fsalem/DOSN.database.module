package dosn.database.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the T_USER database table.
 * 
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	private String userId;

	@Column(name = "user_email", nullable = false, length = 45)
	private String userEmail;

	@Column(length = 100)
	private String user_GID;

	@Column(name = "user_name", nullable = false, length = 45)
	private String userName;

	@Column(name = "user_password", nullable = false, length = 45)
	private String userPassword;

	@Column(name = "profile_hidden", nullable = false, columnDefinition = "TINYINT", length = 1)
	private Boolean profileHidden;

	// bi-directional many-to-one association to UserFriend
	@OneToMany(mappedBy = "TUser")
	private Set<UserFriend> TUserFriends;

	// bi-directional many-to-many association to Interest
	@ManyToMany
	@JoinTable(name = "T_USER_INTEREST", joinColumns = { @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "interest_id", nullable = false) })
	private Set<Interest> TInterests;

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUser_GID() {
		return this.user_GID;
	}

	public void setUser_GID(String user_GID) {
		this.user_GID = user_GID;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Boolean getProfileHidden() {
		return profileHidden;
	}

	public void setProfileHidden(Boolean profileHidden) {
		this.profileHidden = profileHidden;
	}

	public Set<UserFriend> getTUserFriends() {
		return this.TUserFriends;
	}

	public void setTUserFriends(Set<UserFriend> TUserFriends) {
		this.TUserFriends = TUserFriends;
	}

	public Set<Interest> getTInterests() {
		return this.TInterests;
	}

	public void setTInterests(Set<Interest> TInterests) {
		this.TInterests = TInterests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result
				+ ((user_GID == null) ? 0 : user_GID.hashCode());
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
		User other = (User) obj;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (user_GID == null) {
			if (other.user_GID != null)
				return false;
		} else if (!user_GID.equals(other.user_GID))
			return false;
		return true;
	}

}