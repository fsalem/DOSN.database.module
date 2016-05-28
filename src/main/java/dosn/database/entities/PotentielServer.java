package dosn.database.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the T_PSERVERS database table.
 * 
 */
@Entity
@Table(name = "T_PSERVERS")
public class PotentielServer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "server_address", nullable = false)
	private String serverAddress;

	@Column(name = "server_score")
	private Double serverScore = 0.0;

	public PotentielServer() {
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public Double getServerScore() {
		return serverScore;
	}

	public void setServerScore(Double serverScore) {
		this.serverScore = serverScore;
	}



	
}