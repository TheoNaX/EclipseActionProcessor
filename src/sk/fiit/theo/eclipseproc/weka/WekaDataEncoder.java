package sk.fiit.theo.eclipseproc.weka;

import sk.fiit.theo.eclipseproc.actions.ActionType;
import sk.fiit.theo.eclipseproc.actions.EclipseAction;

// TODO: check if all desired parameters encoded
public class WekaDataEncoder {
	
	public String encodeEclipseActionForWeka(final EclipseAction action, final boolean includeNewParams) {
		final StringBuilder sb = new StringBuilder();
		encodeBasicParams(action, sb);
		encodeLinesParams(action, sb);
		encodePackageDistancesParams(action, sb);
		encodeDurationParameters(action, sb);
		encodeSameActionsParams(action, sb);
		if (includeNewParams) {
			encodeWorkResourcesParams(action, sb);
		}
		
		final String context = getContextForWeka(action);
		sb.append(context);
		
		return sb.toString();
		
	}
	
	private void encodeWorkResourcesParams(final EclipseAction action, final StringBuilder sb) {
		sb.append(action.getTotalPackages());
		sb.append(",");
		sb.append(action.getPackageBefore());
		sb.append(",");
		sb.append(action.getTotalResources());
		sb.append(",");
		sb.append(action.getResourceBefore());
		sb.append(",");
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
		
		sb.append(action.getAverageFileChanges().getAddedLines());
		sb.append(",");
		sb.append(action.getAverageFileChanges().getChangedLines());
		sb.append(",");
		sb.append(action.getAverageFileChanges().getDeletedLines());
		sb.append(",");
	}
	
	private void encodePackageDistancesParams(final EclipseAction action, final StringBuilder sb) {
		sb.append(action.getPackageDistanceFromLastAction());
		sb.append(",");
		sb.append(action.getAveragePackageDistanceDiff());
		sb.append(",");
		sb.append(action.getAveragePackageDistanceDiffForAction());
		sb.append(",");
		sb.append(action.getMinPackageDistanceDiff());
		sb.append(",");
		sb.append(action.getMaxPackageDistanceDiff());
		sb.append(",");
	}
	
	private void encodeDurationParameters(final EclipseAction action, final StringBuilder sb) {
		sb.append(action.getAverageDurationDiff());
		sb.append(",");
		sb.append(action.getAverageDurationDiffForAction());
		sb.append(",");
		sb.append(action.getMinDurationDiff());
		sb.append(",");
		sb.append(action.getMaxDurationDiff());
		sb.append(",");
	}
	
	private void encodeSameActionsParams(final EclipseAction action, final StringBuilder sb) {
		sb.append(action.getSameActionsCountInContext());
		sb.append(",");
		sb.append(action.getSameActionsRatio());
		sb.append(",");
		sb.append(action.getTimeSinceLastSameAction());
		sb.append(",");
		sb.append(action.getSameActionsTransitionsCount());
		sb.append(",");
		sb.append(action.getSameActionsTransitionsRatio());
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
