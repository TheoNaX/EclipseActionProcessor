package sk.fiit.theo.eclipseproc.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UserActionsList {
	
	private final List<UserAction> userActions = new ArrayList<UserAction>();
	
	
	public void addAction(final UserAction action) {
		this.userActions.add(action);
	}
	
	public void sortActions() {
		Collections.sort(this.userActions);
	}
	
	public List<UserAction> getUserActions() {
		return this.userActions;
	}

}
