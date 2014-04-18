package sk.fiit.theo.eclipseproc.filter;

import sk.fiit.theo.eclipseproc.actions.EclipseAction;

public class ValidActionsFilter implements ActionFilterIF {

	public static final long LIMIT = 100L;
	
	@Override
	public boolean filterAction(final EclipseAction action) {
		boolean result = true;
		if (action.getTimeSinceLastAction() <= LIMIT) {
			System.out.println(">>> ValidActionsFilter: Action rejected by filter, time since last action too low");
			result = false;
		}
		
		if (action.getPreviousAction() == 0) {
			System.out.println(">>> ValidActionsFilter: Action rejected by filter, first action after Eclipse startup");
			result = false;
		}
		
		return result;
	}
	
}
