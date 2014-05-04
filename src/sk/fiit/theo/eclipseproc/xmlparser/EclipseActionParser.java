package sk.fiit.theo.eclipseproc.xmlparser;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import sk.fiit.theo.eclipseproc.actions.EclipseAction;
import sk.fiit.theo.eclipseproc.actions.FileChanges;

public class EclipseActionParser {

	public EclipseAction parseEclipseAction(final String fileName) {
		final EclipseAction action = new EclipseAction();
		
		final SAXBuilder builder = new SAXBuilder();
		File file;
		
		try {
			file = new File(fileName);
			
			final Document document = builder.build(file);
			final Element rootNode = document.getRootElement();
			
			
			parseBasicParameters(action, rootNode);
			parseFileChangesParameters(action, rootNode);
			parseDurationParameters(action, rootNode);
			parsePackageDistanceParameters(action, rootNode);
			parseSameActionsParameters(action, rootNode);
			parseWorkResourcesParams(action, rootNode);
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		return action;
	}

	private void parsePackageDistanceParameters(final EclipseAction action, final Element root) {
		final String averagePackageDistDiff = root.getChildText(ActionXML.AVERAGE_PACKAGE_DIST_DIFF);
		action.setAveragePackageDistanceDiff(Double.parseDouble(averagePackageDistDiff));
		
		final String averageDistAction = root.getChildText(ActionXML.AVERAGE_PACKAGE_DIST_DIF_ACTION);
		action.setAveragePackageDistanceDiffForAction(Double.parseDouble(averageDistAction));
		
		final String maxDistDiff = root.getChildText(ActionXML.MAX_PACKAGE_DIST_DIFF);
		action.setMaxPackageDistanceDiff(Integer.parseInt(maxDistDiff));
		
		final String minDistDiff = root.getChildText(ActionXML.MIN_PACKAGE_DIST_DIFF);
		action.setMinPackageDistanceDiff(Integer.parseInt(minDistDiff));
		
		
	}

	private void parseDurationParameters(final EclipseAction action, final Element root) {
		final String averageDurationDiff = root.getChild(ActionXML.AVERAGE_DURATION_DIFF).getText();
		action.setAverageDurationDiff(Long.parseLong(averageDurationDiff));
		
		final String averageDurationActionDiff = root.getChildText(ActionXML.AVERAGE_DURATION_DIFF_ACTION);
		action.setAverageDurationDiffForAction(Long.parseLong(averageDurationActionDiff));
		
		final String maxDurationDiff = root.getChildText(ActionXML.MAX_DURATION_DIFF);
		action.setMaxDurationDiff(Long.parseLong(maxDurationDiff));
		
		final String minDurationDiff = root.getChildText(ActionXML.MIN_DURATION_DIFF);
		action.setMinDurationDiff(Long.parseLong(minDurationDiff));
		
	}

	private void parseFileChangesParameters(final EclipseAction action, final Element root) {
		final int recentAdded = Integer.parseInt(root.getChildText(ActionXML.RECENT_LINES_ADDED));
		final int recentChanged = Integer.parseInt(root.getChildText(ActionXML.RECENT_LINES_CHANGED));
		final int recentDeleted = Integer.parseInt(root.getChildText(ActionXML.RECENT_LINES_DELETED));
		final FileChanges recent = new FileChanges(recentChanged, recentDeleted, recentAdded);
		action.setMostRecentFileChanges(recent);
		
		final int averageAdded = Integer.parseInt(root.getChildText(ActionXML.AVERAGE_LINES_ADDED));
		final int averageChanged = Integer.parseInt(root.getChildText(ActionXML.AVERAGE_LINES_CHANGED));
		final int averageDeleted = Integer.parseInt(root.getChildText(ActionXML.AVERAGE_LINES_DELETED));
		final FileChanges average = new FileChanges(averageChanged, averageDeleted, averageAdded);
		action.setAverageFileChanges(average);
		
		
	}
	
	private void parseSameActionsParameters(final EclipseAction action, final Element root) {
		final String timeSinceLastSame = root.getChildText(ActionXML.TIME_SINCE_LAST_SAME);
		action.setTimeSinceLastSameAction(Long.parseLong(timeSinceLastSame));
		
		final String sameActionsCount = root.getChildText(ActionXML.SAME_ACTIONS_COUNT);
		action.setSameActionsCountInContext(Integer.parseInt(sameActionsCount));
		
		final String sameActionsRatio = root.getChildText(ActionXML.SAME_ACTIONS_RATIO);
		action.setSameActionsRatio(Double.parseDouble(sameActionsRatio));
		
		final String sameTransitions = root.getChildText(ActionXML.SAME_ACTIONS_TRANSITIONS_COUNT);
		action.setSameActionsTransitionsCount(Integer.parseInt(sameTransitions));
		
		final String sameTransitionsRatio = root.getChildText(ActionXML.SAME_ACTIONS_TRANSITIONS_RATIO);
		action.setSameActionsTransitionsRatio(Double.parseDouble(sameTransitionsRatio));
	}

	private void parseBasicParameters(final EclipseAction action, final Element root) {
		final String actionType = root.getChild(ActionXML.ACTION_TYPE).getText();
		action.setActionType(actionType);
		
		final String previousAction = root.getChild(ActionXML.PREVIOUS_ACTION).getText();
		action.setPreviousAction(Integer.parseInt(previousAction));
		
		final String timeSinceLast = root.getChildText(ActionXML.TIME_SINCE_LAST);
		action.setTimeSinceLastAction(Long.parseLong(timeSinceLast));
		
		final String timestamp = root.getChildText(ActionXML.TIMESTAMP);
		// TODO parse timestamp
		
		final String recentActions = root.getChild(ActionXML.RECENT_ACTIONS).getText();
		action.setRecentActions(recentActions);
		
		final String sameProject = root.getChild(ActionXML.SAME_PROJECT).getText();
		if (sameProject != null && sameProject.equalsIgnoreCase("false")) {
			action.setSameProject(false);
		} else {
			action.setSameProject(true);
		}
		
		final String contextChange = root.getChild(ActionXML.CONTEXT_CHANGE).getText();
		if (contextChange.equalsIgnoreCase("false")) {
			action.setContextChange(false);
		} else {
			action.setContextChange(true);
		}
		
	}
	
	private void parseWorkResourcesParams(final EclipseAction action, final Element root) {
		final String totalPackages = root.getChild(ActionXML.TOTAL_PACKAGES).getText();
		action.setTotalPackages(Integer.parseInt(totalPackages));
		
		final String packageBefore = root.getChild(ActionXML.PACKAGE_BEFORE).getText();
		action.setPackageBefore(Integer.parseInt(packageBefore));
		
		final String totalResources = root.getChild(ActionXML.TOTAL_RESOURCES).getText();
		action.setTotalResources(Integer.parseInt(totalResources));
		
		final String resourceBefore = root.getChild(ActionXML.RESOURCES_BEFORE).getText();
		action.setResourceBefore(Integer.parseInt(resourceBefore));
		
		
	}
	
	public static void main(final String[] args) {
		final String fileName = "ACTION_20131206001244_00142.xml";
		final EclipseActionParser test = new EclipseActionParser();
		final EclipseAction action = test.parseEclipseAction(fileName);
		System.out.println(action);
		
	}
	
	
	
}
