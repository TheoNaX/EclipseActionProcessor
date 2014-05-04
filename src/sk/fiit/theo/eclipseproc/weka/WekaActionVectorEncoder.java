package sk.fiit.theo.eclipseproc.weka;

import sk.fiit.theo.eclipseproc.actions.ActionType;
import sk.fiit.theo.eclipseproc.actions.EclipseAction;

/**
 * 
 * @author Tomas
 *
 * This class encodes action parameters 
 * Considers only previous action, not task context
 * It is designed as comparison to task context
 *
 */
public class WekaActionVectorEncoder {
	
	public String encodeEclipseActionForWeka(final EclipseAction action) {
		final StringBuilder sb = new StringBuilder();
		
		encodeBasicParams(action, sb);
		
		encodeLinesParams(action, sb);
		
		final String context = getContextForWeka(action);
		sb.append(context);
		
		return sb.toString();
	}
	
	private String getContextForWeka(final EclipseAction action) {
		if (action.isContextChange()) {
			return "context-change";
		} else {
			return "no-context-change";
		}
	}
	
	private void encodeBasicParams(final EclipseAction action, final StringBuilder sb) {
		final String wekaActionType = ActionType.getActionTypeForWeka(action.getActionType());
		final String lastAtionType = ActionType.getActionTypeForWeka(action.getPreviousAction());
		final String timeSinceLast = String.valueOf(action.getTimeSinceLastAction());
		
		sb.append("'");
		sb.append(wekaActionType);
		sb.append("',");
		
		sb.append("'");
		sb.append(lastAtionType);
		sb.append("',");
		
		sb.append(timeSinceLast);
		sb.append(",");
		
		sb.append(action.getPackageDistanceFromLastAction());
		sb.append(",");
		
		sb.append(getIntValueFromBoolean(action.isSameProject()));
		sb.append(",");
		
	}
	
	private void encodeLinesParams(final EclipseAction action, final StringBuilder sb) {
		sb.append(action.getMostRecentFileChanges().getAddedLines());
		sb.append(",");
		sb.append(action.getMostRecentFileChanges().getChangedLines());
		sb.append(",");
		sb.append(action.getMostRecentFileChanges().getDeletedLines());
		sb.append(",");
	}
	
	private int getIntValueFromBoolean(final boolean bValue) {
		if (bValue) {
			return 1;
		} else {
			return 0;
		}
	}

}
