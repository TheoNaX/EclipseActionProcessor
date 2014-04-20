package sk.fiit.theo.eclipseproc.session;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.fiit.theo.eclipseproc.actions.EclipseAction;
import sk.fiit.theo.eclipseproc.filter.ActionFilterIF;
import sk.fiit.theo.eclipseproc.xmlparser.EclipseActionParser;

public class SessionActionProcessor {
	
	public static final int ACTIONS_PER_SESSION = 10;
	
	private final List<ActionFilterIF> filters = new ArrayList<ActionFilterIF>();
	private final EclipseActionParser parser = new EclipseActionParser();
	
	private int trueContextChange;
	private int falseNoContextChange;
	private int trueNoContextChange;
	private int falseContextChange;
	
	
	private final Map<String, UserActionsList> userLists = new HashMap<String, UserActionsList>();
	
	public void applyFilter(final ActionFilterIF filter) {
		this.filters.add(filter);
	}
	
	public void processAllActionFiles(final String directory) throws IOException {
		processAndSortActionFiles(directory);
		
		try {
			
			final Set<String> keySet = this.userLists.keySet();
			for (final String user : keySet) {
				final UserActionsList userActionsList = this.userLists.get(user);
				final List<UserAction> userActions = userActionsList.getUserActions();
				int counter = 1;
				UserAction previous = null;
				for (final UserAction action : userActions) {
					final boolean actionContext = action.getAction().isContextChange();
					if (counter % ACTIONS_PER_SESSION == 0 || (previous != null && UserActionsList.shouldBeNewContext(action, previous))) {
						if (actionContext) {
							this.trueContextChange++;
						} else {
							this.falseContextChange++;
						}
						counter = 1;
						previous = null;
						continue;
					} else {
						if (actionContext) {
							this.falseNoContextChange++;
						} else {
							this.trueNoContextChange++;
						}
					}
					previous = action;
					counter++;
				}
				
			}
			
			System.out.println("SESSION RESULTS, actions per session : " + ACTIONS_PER_SESSION);
			System.out.println("True context changes:" + this.trueContextChange);
			System.out.println("False no context changes: " + this.falseNoContextChange);
			System.out.println("True no context changes: " + this.trueNoContextChange);
			System.out.println("False context changes: " + this.falseContextChange);
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	private void processAndSortActionFiles(final String directory) {
		final File dir = new File(directory);
		int processed = 0;
		int accepted = 0, rejected = 0;
		
		try {
			final String[] fileNames = dir.list();
			
			for (int i=0; i<fileNames.length; i++) {
				processed++;
				final String fileName = fileNames[i];
				final EclipseAction action = this.parser.parseEclipseAction(dir.getAbsolutePath() + File.separator + fileName);
				if (filterAction(action)) {
					accepted++;
					final String userId = UserAction.getUserIdFromFileName(fileName);
					final UserAction userAction = new UserAction(action, fileName);
					
					if (!this.userLists.containsKey(userId)) {
						this.userLists.put(userId, new UserActionsList());
					} 
					this.userLists.get(userId).addAction(userAction);
				} else {
					rejected++;
				}
				
			}
			
			// Sort actions for all users
			sortActionsForUsers();
			
			final Set<String> keySet = this.userLists.keySet();
			for (final String user : keySet) {
				final UserActionsList list = this.userLists.get(user);
				System.out.println(list.getUserActions());
			}
			
			System.out.println("Processed actions: " + processed);
			System.out.println("Accepted actions: " + accepted + ", rejected actions: " + rejected);
			System.out.println("Number of users: " + this.userLists.size());
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sortActionsForUsers() {
		final Set<String> keySet = this.userLists.keySet();
		for (final String s: keySet) {
			final UserActionsList userActions = this.userLists.get(s);
			userActions.sortActions();
		}
	}
	

	private boolean filterAction(final EclipseAction action) {
		boolean result = true;
		for (final ActionFilterIF filter : this.filters) {
			if (!filter.filterAction(action)) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	public static void main(final String[] args) {
		final String directory = "e:\\DIPLOMKA\\testlogs";
		final SessionActionProcessor processor = new SessionActionProcessor();
		processor.processAndSortActionFiles(directory);
		
		
	}

}
