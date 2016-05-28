package dosn.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dosn.database.entities.Interest;
import dosn.database.entities.User;

/**
 * This class is responsible for database interaction with T_USER table 
 */

@Repository
public class UserDao{

	@PersistenceContext(unitName="DOSN.database.module")
	private EntityManager entityManager;
	
	public UserDao() {
	}
	
	@Transactional(value="dosnTransaction",readOnly=false)
	public void addUser(User user){
		entityManager.persist(user);
	}
	
	@Transactional(value="dosnTransaction",readOnly=false)
	public void deleteUser(User user){
		entityManager.remove(user);
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<User> findAllUser(){
		Query query = entityManager.createQuery("SELECT u from User u");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public User findByName(String name){
		Query query = entityManager.createQuery("SELECT u from User u where u.userName = :name");
		query.setParameter("name", name);
		List<User> resultList = query.getResultList();
		if(resultList== null || resultList.size()>0){
			return resultList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<User> findLikeName(String name){
		Query query = entityManager.createQuery("SELECT u from User u where u.profileHidden = :profileHidden AND u.userName LIKE :name");
		query.setParameter("profileHidden", Boolean.FALSE);
		query.setParameter("name", '%'+name+'%');
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public User findByNameAndPassword(String name,String password){
		Query query = entityManager.createQuery("SELECT u from User u where u.userName = :name and u.userPassword = :password");
		query.setParameter("name", name);
		query.setParameter("password", password);
		List<User> resultList = query.getResultList();
		if(resultList== null || resultList.size()>0){
			return resultList.get(0);
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<User> findByInterests(List<Interest> interests){
		List<User> resultList = new ArrayList<User>();
		for(Interest interest:interests){
			Query query = entityManager.createQuery("select u from User u where u.profileHidden = :profileHidden AND :interest MEMBER OF u.TInterests",User.class);
			query.setParameter("profileHidden", Boolean.FALSE);
			query.setParameter("interest", interest);
			resultList.addAll(query.getResultList());
		}
		
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<User> findByUserAndInterestLevel(List<User> userList,List<Interest> interestList, Integer privacyLevel){
		List<User> resultList = new ArrayList<User>();
		
		if(userList!=null && interestList!=null && userList.size()>0 && interestList.size()>0){
			System.out.println("Search: u:" + userList.toString() + " i:" +interestList.toString());
			Query query = entityManager.createQuery("select u.user from UserInterestPrivacyLevel u where u.user.profileHidden = :profileHidden AND u.user IN (:userList) AND  u.interest IN (:interestList)   AND ((u.privacyLevel >= :privacyLevel) OR (u.privacyLevel = 0))",User.class);
			query.setParameter("profileHidden", Boolean.FALSE);
			query.setParameter("interestList", interestList);
			query.setParameter("userList", userList);
			query.setParameter("privacyLevel", privacyLevel);
	
			resultList.addAll(query.getResultList());
		}
		
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public List<User> findByPublicInterests(List<Interest> interests){
		List<User> resultList = new ArrayList<User>();
		Query query = entityManager.createQuery("select u.user from UserInterestPrivacyLevel u where u.user.profileHidden = :profileHidden AND u.interest IN (:interests)   AND u.privacyLevel = 0",User.class);
		query.setParameter("profileHidden", Boolean.FALSE);
		query.setParameter("interests", interests);
		resultList.addAll(query.getResultList());
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(value="dosnTransaction",readOnly=true)
	public Long countMatchingInterests(User user,List<Interest> interests,Integer privacyLevel){
		try{
		Query query = entityManager.createQuery("select count(*) from UserInterestPrivacyLevel u where u.user.profileHidden = :profileHidden AND u.user =:user AND u.interest IN (:interestList)   AND ((u.privacyLevel >= :privacyLevel) OR (u.privacyLevel = 0))",Long.class);
		query.setParameter("profileHidden", Boolean.FALSE);
		query.setParameter("interestList", interests);
		query.setParameter("user", user);
		query.setParameter("privacyLevel", privacyLevel);
		return (Long) query.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
