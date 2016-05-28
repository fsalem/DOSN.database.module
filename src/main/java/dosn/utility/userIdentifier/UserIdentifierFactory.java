package dosn.utility.userIdentifier;

import java.util.List;

import dosn.database.entities.User;

/**
 * This interface represents Abstract Factory interface to create userIDs according to URI or SONIC 
 * 
 */
public interface UserIdentifierFactory {

	public List<String> buildUserIdentifiers(List<User> users);

	public String buildUserIdentifier(User user);
}
