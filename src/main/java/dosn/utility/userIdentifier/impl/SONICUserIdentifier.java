package dosn.utility.userIdentifier.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dosn.database.entities.User;
import dosn.utility.userIdentifier.UserIdentifierFactory;

/**
 * Implementation of UserIdentifierFactory using GlobalUserID used by SONIC
 *
 */

@Scope(value="prototype")
@Component
public class SONICUserIdentifier implements UserIdentifierFactory {

	@Override
	public List<String> buildUserIdentifiers(List<User> users) {
		if (null == users)
			return null;
		List<String> usersUri = new ArrayList<String>();
		for (User user:users){
			usersUri.add(user.getUser_GID());
		}
		return usersUri;
	}

	@Override
	public String buildUserIdentifier(User user) {
		if (null == user)
			return null;
		String usersUri = user.getUser_GID();

		return usersUri;
	}

}
