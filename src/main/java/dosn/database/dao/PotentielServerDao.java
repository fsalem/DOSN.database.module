package dosn.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dosn.database.entities.PotentielServer;

/**
 * This class is responsible for database interaction with T_PSERVERS table
 */
@Repository
public class PotentielServerDao {

	@PersistenceContext(unitName = "DOSN.database.module")
	private EntityManager entityManager;

	public PotentielServerDao() {
	}

	/**
	 * add server to db
	 * 
	 * @param server
	 */
	@Transactional(value = "dosnTransaction", readOnly = false)
	public void addServer(PotentielServer server) {
		entityManager.persist(server);
	}

	/**
	 * delete server in db
	 * 
	 * @param server
	 */
	@Transactional(value = "dosnTransaction", readOnly = false)
	public void deleteServer(PotentielServer server) {
		server = entityManager.merge(server);
		entityManager.remove(server);
	}

	/**
	 * return all potential server from db
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value = "dosnTransaction", readOnly = true)
	public List<PotentielServer> findAllPotentielServer() {
		Query query = entityManager
				.createQuery("SELECT p from PotentielServer p");
		return query.getResultList();
	}

	/**
	 * return all potential server from db
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value = "dosnTransaction", readOnly = true)
	public List<PotentielServer> findRandomPotentielServers(Integer maxRows) {
		Query query = entityManager
				.createQuery("SELECT p from PotentielServer p ORDER BY random()");
		query.setMaxResults(maxRows);
		return query.getResultList();
	}

}
