package dosn.utility.userIdentifier.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dosn.database.entities.User;
import dosn.utility.general.PropertiesLookup;
import dosn.utility.userIdentifier.UserIdentifierFactory;

/**
 * Implementation for UserIdentifierFactory using Server URI
 *
 */

@Scope(value="prototype")
@Component
public class URIUserIdentifier implements UserIdentifierFactory {

	@Override
	public List<String> buildUserIdentifiers(List<User> users) {
		if (null == users)
			return null;
		String userProfileUrl = PropertiesLookup.getServerUrl()+PropertiesLookup.getUserProfileUri();
		List<String> usersUri = new ArrayList<String>();
		for (User user:users){
			usersUri.add(userProfileUrl+user.getUserId());
		}
		return usersUri;
	}

	@Override
	public String buildUserIdentifier(User user) {
		if (null == user)
			return null;
		String userProfileUrl = PropertiesLookup.getServerUrl()+PropertiesLookup.getUserProfileUri();
		String usersUri = userProfileUrl+user.getUserId();
		return usersUri;
	}
}
