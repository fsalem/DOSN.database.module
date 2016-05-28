package dosn.database.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the T_INTEREST database table.
 * 
 */
@Entity
@Table(name="T_INTEREST")
public class Interest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="interest_id", unique=true, nullable=false)
	private String interestId;

	@Column(name="interest_name", nullable=false, length=45)
	private String interestName;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="TInterests")
	private Set<User> TUsers;

    public Interest() {
    }

	public String getInterestId() {
		return this.interestId;
	}

	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}

	public String getInterestName() {
		return this.interestName;
	}

	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}

	public Set<User> getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(Set<User> TUsers) {
		this.TUsers = TUsers;
	}
	
}