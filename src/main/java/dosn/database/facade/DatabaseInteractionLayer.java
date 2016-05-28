package dosn.database.facade;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import dosn.database.dao.FriendDao;
import dosn.database.dao.InterestDao;
import dosn.database.dao.PotentielServerDao;
import dosn.database.dao.UserDao;
import dosn.database.entities.Friend;
import dosn.database.entities.Interest;
import dosn.database.entities.PotentielServer;
import dosn.database.entities.User;
import dosn.database.entities.UserFriend;

/**
 * This class is a facade layer between other modules and the database. This
 * pattern is used byGUI, Search, Recommendation Module to access Database
 * 
 */

@Named("databaseInteractionLayer")
@Singleton
@javax.transaction.Transactional
public class DatabaseInteractionLayer {

	@Inject
	private UserDao userDao;

	@Inject
	InterestDao interestDao;

	@Inject
	PotentielServerDao potentielServerDao;

	@Inject
	FriendDao friendDao;

	public DatabaseInteractionLayer() {

	}

	@PostConstruct
	public void init() {

	}

	/**
	 * Return all Users if one or more interests match
	 * 
	 * @param interests
	 * @return
	 */
	public List<User> retrieveUsersByInterest(List<String> interests) {
		List<User> users;

		List<Interest> interestsList = interestDao.findByName(interests);
		users = userDao.findByInterests(interestsList);

		return users;

	}

	/**
	 * Returns the SimilarityScore for a User, based on searched Interests and
	 * friendLevel to Requesting Recommendation User
	 * 
	 * @param user
	 * @param interests
	 * @param friendLevel
	 * @return
	 */
	@Transactional(value = "dosnTransaction")
	public Double retriveSimilarityScore(User user, List<String> interests,
			Integer friendLevel) {
		if (user != null && interests != null && interests.size() > 0) {
			List<Interest> interestsList = interestDao.findByName(interests);

			Double similarityScore = 0.0;
			user = userDao.findByName(user.getUserName());
			Integer numInterests = user.getTInterests().size();
			Long numMatchingInterests = userDao.countMatchingInterests(user,
					interestsList, friendLevel);
			similarityScore = ((double) numMatchingInterests / (double) numInterests);
			// System.out.println("genereate Similarity Score: numMatching: " +
			// numMatchingInterests + ", numInterests: " + numInterests +
			// " , score:" +similarityScore);
			return similarityScore;
		}

		return null;
	}

	/**
	 * Return user matching one or more interests
	 * 
	 * @param interests
	 * @return
	 */
	public List<User> retrieveUsersByPublicInterests(List<String> interests) {
		List<User> users;

		List<Interest> interestsList = interestDao.findByName(interests);
		users = userDao.findByPublicInterests(interestsList);

		return users;

	}

	/**
	 * Return user matching one or more interests
	 * 
	 * @param interests
	 * @return
	 */
	public List<User> retrieveUsersByInterestAndFriendLevel(List<User> user,
			List<String> interests, Integer privacyLevel) {
		List<User> users;

		List<Interest> interestsList = interestDao.findByName(interests);
		users = userDao.findByUserAndInterestLevel(user, interestsList,
				privacyLevel);

		return users;

	}

	/**
	 * Return all Users by username
	 * 
	 * @param interests
	 * @return
	 */
	public List<User> retrieveUsersByUsername(String username) {
		return userDao.findLikeName(username);
	}

	/**
	 * Return all Potential Server from DB
	 * 
	 * @return
	 */
	public List<String> retrievePotentielServers() {

		List<String> urlList = new ArrayList<String>();

		for (PotentielServer server : potentielServerDao
				.findAllPotentielServer()) {
			String address = server.getServerAddress();
			if (!address.endsWith("/")) {
				address += "/";
			}
			urlList.add(address);
		}

		return urlList;
	}

	/**
	 * return max. number of server from DB Future Work: improve the selection
	 * of Server here
	 * 
	 * @param maxServers
	 *            This is the max number of servers to be returned
	 * @return
	 */
	public List<String> retrievePotentielServers(Integer maxServers) {
		List<String> urlList = new ArrayList<String>();
		List<PotentielServer> potentielServers = potentielServerDao
				.findRandomPotentielServers(maxServers);

		for (PotentielServer server : potentielServers) {
			urlList.add(server.getServerAddress());
		}

		return urlList;
	}

	/**
	 * To add the non existing potentiel servers
	 * 
	 * @param potentielServers
	 */
	public void addNonExistingPotentielServers(List<String> potentielServers) {

		List<String> existingServer = retrievePotentielServers();
		for (String serverURL : potentielServers) {
			if (!serverURL.endsWith("/")) {
				serverURL += "/";
			}
			if (!existingServer.contains(serverURL)) {
				PotentielServer server = new PotentielServer();
				server.setServerAddress(serverURL);
				server.setServerScore(0.0);
				potentielServerDao.addServer(server);
			}
		}
	}

	/**
	 * Check if the user is exist or not
	 * 
	 * @param username
	 * @param password
	 * @return This method will return null if there is no user with this data
	 *         otherwise, it will return the userId
	 */
	public String isUserExist(String username, String password) {
		// TODO
		return userDao.findByNameAndPassword(username, password).getUser_GID();

	}

	/**
	 * This method should return list of user's interests
	 * 
	 * @param userId
	 * @return List of interests
	 */

	public List<String> retrieveUserInterests(String userId) {
		User user = userDao.findByName(userId);
		List<Interest> interestList = interestDao.getAllInterestfromUser(user);
		List<String> resultList = new ArrayList<String>();
		for (Interest i : interestList) {
			resultList.add(i.getInterestName());
		}
		return resultList;
	}

	/**
	 * Retrieve all interests from a user
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(value = "dosnTransaction", readOnly = true)
	public List<String> retrieveInterestsByUser(String userId) {
		User user = userDao.findByName(userId);
		Set<Interest> interestList = user.getTInterests();
		List<String> resultList = new ArrayList<String>();
		for (Interest i : interestList) {
			resultList.add(i.getInterestName());
		}
		return resultList;
	}

	/**
	 * This method should return list of friends of a certain user
	 * 
	 * @param userId
	 * @return List of friends' names
	 */
	@Transactional(value = "dosnTransaction", readOnly = true)
	public List<String> retrieveFriendNames(String userId) {
		System.out.println("search for user: " + userId);
		User user = userDao.findByName(userId);
		List<String> resultList = new ArrayList<String>();

		if (user != null) {
			Set<UserFriend> friendList = user.getTUserFriends();

			for (UserFriend f : friendList) {
				resultList.add(f.getTFriend().getFriendName());
			}
		}
		return resultList;

	}

	/**
	 * Retrive List of Server, where Friends of User are placed
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(value = "dosnTransaction", readOnly = true)
	public List<String> retrieveFriendServer(String userId) {
		System.out.println("search for user: " + userId);
		User user = userDao.findByName(userId);
		List<String> resultList = new ArrayList<String>();

		if (user != null) {
			Set<UserFriend> friendList = user.getTUserFriends();

			for (UserFriend f : friendList) {
				resultList.add(f.getTFriend().getFriendLocation());
			}
		}
		return resultList;

	}

	/**
	 * step 1: retrieve the friend object based on userId step 2: retrieve all
	 * User, that are friends with him/her
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(value = "dosnTransaction", readOnly = true)
	public List<String> retriveFriendsByUser(String userId) {
		List<String> resultList = new ArrayList<String>();
		Friend user = friendDao.findByName(userId);
		List<User> friendList = friendDao.findFriendsOfUser(user);
		for (User friend : friendList) {
			resultList.add(friend.getUserName());
		}
		return resultList;
	}

	/**
	 * this method is used to retrieve a list of Entry<User,FriendOfUser> based
	 * on a List of User the Results can be used to propagate the requests
	 * 
	 * @param users
	 * @return
	 */
	@Transactional(value = "dosnTransaction", readOnly = true)
	public List<Entry<String, String>> retriveFriendsOfFriend(List<String> users) {
		List<Entry<String, String>> resultList = new ArrayList<>();
		for (String user : users) {
			List<String> friendOfUser = retrieveFriendNames(user);
			if (friendOfUser != null) {
				for (String friendOfFriend : friendOfUser) {
					Entry<String, String> userFriendPair = new AbstractMap.SimpleEntry<>(
							user, friendOfFriend);
					if (!resultList.contains(userFriendPair)) {
						resultList.add(userFriendPair);
					}
				}
			}
		}

		return resultList;
	}

	/**
	 * retrive All Friends from a User
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(value = "dosnTransaction", readOnly = true)
	public String retriveServerByFriendName(String userId) {
		Friend friend = friendDao.findByName(userId);
		if (friend != null) {
			return friend.getFriendLocation();
		}
		return null;

	}

}
