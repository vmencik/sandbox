package cz.vmencik.sandbox.nullcheck;

interface AuthorizedUser {
	User getUser();
}

interface User {
	Group getGroup();
}

interface Group {
	String getName();
}

public class NullcheckJava {
	
	public String getGroupName1(AuthorizedUser authUser) {
		if (authUser == null) {
			return null;
		}
		User user = authUser.getUser();
		if (user == null) {
			return null;
		}
		Group group = user.getGroup();
		if (group == null) {
			return null;
		}
		return group.getName();
	}
	
	public String getGroupName2(AuthorizedUser authUser) {
		if (authUser != null) {
			User user = authUser.getUser();
			if (user != null) {
				Group group = user.getGroup();
				if (group != null) {
					return group.getName();
				}
			}
		}
		return null;
	}
}