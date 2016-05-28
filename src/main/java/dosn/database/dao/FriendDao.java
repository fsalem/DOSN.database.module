package dosn.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dosn.database.entities.Friend;
import dosn.database.entities.User;

/**
 * This class is responsible for database interaction with T_USER_FRIEND table 
 */

@Repository
public class FriendDao {

	@PersistenceContext(unitName="DOSN.database.module")
	private EntityManager entityManager;
	
	
	public FriendDao() {
	}
	
	/**
	 * return Friend based on Name or null if no match
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public Friend findByName(String name){
		Query query = entityManager.createQuery("SELECT f from Friend f where f.friendName like :name");
		query.setParameter("name", name);
		List<Friend> resultList = query.getResultList();
		if(resultList!=null && resultList.size()>0){
			return resultList.get(0);
		}
		
		return null;
	}
	
	/**
	 * return List of User, who have the specified user as friend
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<User> findFriendsOfUser(Friend user){
		Query query = entityManager.createQuery("SELECT f.TUser from UserFriend f where f.TFriend = :user");
		query.setParameter("user", user);
		List<User> resultList = query.getResultList();
		return resultList;
	}
	
}
