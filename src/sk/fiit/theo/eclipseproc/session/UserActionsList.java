package sk.fiit.theo.eclipseproc.session;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import sk.fiit.theo.eclipseproc.actions.EclipseAction;
import sk.fiit.theo.eclipseproc.xmlparser.EclipseActionParser;


public class UserActionsList {
	
	private final static int MINUTES_TIMEOUT = 180;
	
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
	
	public static boolean shouldBeNewContext(final UserAction action, final UserAction previous) {
		boolean result = false;
		final Date previousTimestamp = previous.getTimeStamp();
		final Date timeStamp = action.getTimeStamp();
		
		final Calendar previousCal = Calendar.getInstance();
		previousCal.setTime(previousTimestamp);
		previousCal.add(Calendar.MINUTE, MINUTES_TIMEOUT);
		
		final Calendar actualCal = Calendar.getInstance();
		actualCal.setTime(timeStamp);
		
		
		
		if (previousCal.before(actualCal)) {
			result = true;
		}
		
		return result;
		
	}
	
	public static void main(final String[] args) throws Exception {
		final EclipseActionParser parser = new EclipseActionParser();
		final File dir = new File("e:\\DIPLOMKA\\testlogs");
		final UserActionsList actionList = new UserActionsList();
		final String[] files = dir.list();
		for (final String fileName : files) {
			final EclipseAction action = parser.parseEclipseAction(dir.getAbsolutePath() + File.separator + fileName);
			final UserAction userAction = new UserAction(action, fileName);
			actionList.addAction(userAction);
		}
		actionList.sortActions();
		
		final List<UserAction> actions = actionList.getUserActions();
		for (final UserAction a : actions) {
			System.out.println(a.getFileName());
		}
		
		final EclipseAction action1 = parser.parseEclipseAction(dir.getAbsolutePath() + File.separator + "ACTION_20140418221035_00001_Tomas0148.xml");
		final UserAction uAction1 = new UserAction(action1, "ACTION_20140418221035_00001_Tomas0148.xml");
		
		final EclipseAction action2 = parser.parseEclipseAction(dir.getAbsolutePath() + File.separator + "ACTION_20140420130440_00039_Tomas0148.xml");
		final UserAction uAction2 = new UserAction(action2, "ACTION_20140420130440_00039_Tomas0148.xml");
		
		System.out.println(shouldBeNewContext(uAction2, uAction1));
	}

}
