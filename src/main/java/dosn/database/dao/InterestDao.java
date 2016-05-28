package dosn.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;




import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dosn.database.entities.Interest;
import dosn.database.entities.User;

/**
 * This class is responsible for database interaction with T_INTEREST table 
 */

@Repository
public class InterestDao {
	
	public InterestDao() {
	
	}
	
	@PersistenceContext(unitName="DOSN.database.module")
	private EntityManager entityManager;
	
	/**
	 * add user
	 * @param interest
	 */
	@Transactional(value="dosnTransaction",readOnly=false)
	public void addInterest(Interest interest){
		entityManager.persist(interest);
	}
	
	/**
	 * delete User
	 * @param interest
	 */
	@Transactional(value="dosnTransaction",readOnly=false)
	public void deleteInterest(Interest interest){
		entityManager.remove(interest);
	}
	
	/**
	 * return all interests
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=false)
	public List<Interest> getAllInterest(){
		Query query = entityManager.createQuery("SELECT i from Interest i");
		return query.getResultList();
	}
	
	/**
	 * return all interests from user
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<Interest> getAllInterestfromUser(User user){
		if(user!=null){
			Query query = entityManager.createQuery("SELECT i from Interest i where :user IN i.TUsers");
			query.setParameter("user", user);
			return query.getResultList();
		}else{
			return null;
		}
		
	}
	
	/**
	 * find matching Interests based on the name
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<Interest> findByName(String name){
		Query query = entityManager.createQuery("SELECT i from Interest i where i.interestName like :name");
		query.setParameter("name", name);
		return query.getResultList();
	}
	

	/**
	 * find all Interests, name matches one of the names in the List 
	 * @param interests 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<Interest> findByName(List<String> interests) {
		if(interests!=null && interests.size()>0){
			Query query = entityManager.createQuery("SELECT i from Interest i where i.interestName IN (:interestsList)");
			query.setParameter("interestsList", interests);
			return query.getResultList();
		}else{
			return null;
		}
	}
	
}
