package sk.fiit.theo.eclipseproc.actions;

import java.util.Date;


// TODO add new parameters for Eclipse action
public class EclipseAction {
	protected String actionType;
	
	protected long timeSinceLastAction;
	protected int previousAction;
	protected boolean contextChange;
	protected Date timestamp;
	protected String recentActions;
	protected int recentSameActionsCount;
	
	protected int packageDistanceFromLastAction;
	
	protected long averageDurationDiffForAction;
	protected long averageDurationDiff;
	protected long maxDurationDiff;
	protected long minDurationDiff;
	
	protected int sameActionsCountInContext;
	protected double sameActionsRatio;
	protected long timeSinceLastSameAction;
	protected int sameActionsTransitionsCount;
	protected double sameActionsTransitionsRatio;
	
	
	protected FileChanges mostRecentFileChanges;
	protected FileChanges averageFileChanges;
	
	protected double averagePackageDistanceDiff;
	protected double averagePackageDistanceDiffForAction;
	protected int maxPackageDistanceDiff;
	protected int minPackageDistanceDiff;
	
	public String getActionType() {
		return this.actionType;
	}
	public void setActionType(final String actionType) {
		this.actionType = actionType;
	}
	public long getTimeSinceLastAction() {
		return this.timeSinceLastAction;
	}
	public void setTimeSinceLastAction(final long timeSinceLastAction) {
		this.timeSinceLastAction = timeSinceLastAction;
	}
	public int getPreviousAction() {
		return this.previousAction;
	}
	public void setPreviousAction(final int previousAction) {
		this.previousAction = previousAction;
	}
	public boolean isContextChange() {
		return this.contextChange;
	}
	public void setContextChange(final boolean contextChange) {
		this.contextChange = contextChange;
	}
	public Date getTimestamp() {
		return this.timestamp;
	}
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getRecentActions() {
		return this.recentActions;
	}
	public void setRecentActions(final String recentActions) {
		this.recentActions = recentActions;
	}
	public int getRecentSameActionsCount() {
		return this.recentSameActionsCount;
	}
	public void setRecentSameActionsCount(final int recentSameActionsCount) {
		this.recentSameActionsCount = recentSameActionsCount;
	}
	public int getPackageDistanceFromLastAction() {
		return this.packageDistanceFromLastAction;
	}
	public void setPackageDistanceFromLastAction(final int packageDistanceFromLastAction) {
		this.packageDistanceFromLastAction = packageDistanceFromLastAction;
	}
	public long getAverageDurationDiffForAction() {
		return this.averageDurationDiffForAction;
	}
	public void setAverageDurationDiffForAction(final long averageDurationDiffForAction) {
		this.averageDurationDiffForAction = averageDurationDiffForAction;
	}
	public long getAverageDurationDiff() {
		return this.averageDurationDiff;
	}
	public void setAverageDurationDiff(final long averageDurationDiff) {
		this.averageDurationDiff = averageDurationDiff;
	}
	public long getMaxDurationDiff() {
		return this.maxDurationDiff;
	}
	public void setMaxDurationDiff(final long maxDurationDiff) {
		this.maxDurationDiff = maxDurationDiff;
	}
	public long getMinDurationDiff() {
		return this.minDurationDiff;
	}
	public void setMinDurationDiff(final long minDurationDiff) {
		this.minDurationDiff = minDurationDiff;
	}
	public int getSameActionsCountInContext() {
		return this.sameActionsCountInContext;
	}
	public void setSameActionsCountInContext(final int sameActionsCountInContext) {
		this.sameActionsCountInContext = sameActionsCountInContext;
	}
	public double getSameActionsRatio() {
		return this.sameActionsRatio;
	}
	public void setSameActionsRatio(final double sameActionsRatio) {
		this.sameActionsRatio = sameActionsRatio;
	}
	public long getTimeSinceLastSameAction() {
		return this.timeSinceLastSameAction;
	}
	public void setTimeSinceLastSameAction(final long timeSinceLastSameAction) {
		this.timeSinceLastSameAction = timeSinceLastSameAction;
	}
	public int getSameActionsTransitionsCount() {
		return this.sameActionsTransitionsCount;
	}
	public void setSameActionsTransitionsCount(final int sameActionsTransitionsCount) {
		this.sameActionsTransitionsCount = sameActionsTransitionsCount;
	}
	public double getSameActionsTransitionsRatio() {
		return this.sameActionsTransitionsRatio;
	}
	public void setSameActionsTransitionsRatio(final double sameActionsTransitionsRatio) {
		this.sameActionsTransitionsRatio = sameActionsTransitionsRatio;
	}
	public FileChanges getMostRecentFileChanges() {
		return this.mostRecentFileChanges;
	}
	public void setMostRecentFileChanges(final FileChanges mostRecentFileChanges) {
		this.mostRecentFileChanges = mostRecentFileChanges;
	}
	public FileChanges getAverageFileChanges() {
		return this.averageFileChanges;
	}
	public void setAverageFileChanges(final FileChanges averageFileChanges) {
		this.averageFileChanges = averageFileChanges;
	}
	public double getAveragePackageDistanceDiff() {
		return this.averagePackageDistanceDiff;
	}
	public void setAveragePackageDistanceDiff(final double averagePackageDistanceDiff) {
		this.averagePackageDistanceDiff = averagePackageDistanceDiff;
	}
	public double getAveragePackageDistanceDiffForAction() {
		return this.averagePackageDistanceDiffForAction;
	}
	public void setAveragePackageDistanceDiffForAction(final double averagePackageDistanceDiffForAction) {
		this.averagePackageDistanceDiffForAction = averagePackageDistanceDiffForAction;
	}
	public int getMaxPackageDistanceDiff() {
		return this.maxPackageDistanceDiff;
	}
	public void setMaxPackageDistanceDiff(final int maxPackageDistanceDiff) {
		this.maxPackageDistanceDiff = maxPackageDistanceDiff;
	}
	public int getMinPackageDistanceDiff() {
		return this.minPackageDistanceDiff;
	}
	public void setMinPackageDistanceDiff(final int minPackageDistanceDiff) {
		this.minPackageDistanceDiff = minPackageDistanceDiff;
	}
	
	public String commonParamsToString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Action type: " + this.actionType + ", ");
		sb.append("Previous action: " + this.previousAction + ", ");
		sb.append("Time since last action: " + this.timeSinceLastAction + ", ");
		sb.append("Context change: " + this.contextChange + ", ");
		sb.append("Timestamp: " + this.timestamp + "\n");
		
		return sb.toString();
	}
	
	public String durationParamsToString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Average duration diff: " + this.averageDurationDiff + ", ");
		sb.append("Average duration diff for action: " + this.averageDurationDiffForAction + ", ");
		sb.append("Max duration diff: " + this.maxDurationDiff + ", ");
		sb.append("Min duration diff: " + this.minDurationDiff + "\n");
		
		return sb.toString();
	}
	
	public String packageDistParamsToString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Average package dist diff: " + this.averagePackageDistanceDiff + ", ");
		sb.append("Average package dist diff for action: " + this.averagePackageDistanceDiffForAction + ", ");
		sb.append("Max package dist diff: " + this.maxPackageDistanceDiff + ", ");
		sb.append("Min package dist diff: " + this.minPackageDistanceDiff + "\n");
		
		return sb.toString();
	}
	
	public String sameActionsParamsToString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Time since last same action: " + this.timeSinceLastSameAction + ", ");
		sb.append("Same actions count: " + this.sameActionsCountInContext + ", ");
		sb.append("Same actions ratio: " + this.sameActionsRatio + ", ");
		sb.append("Same actions transitions: " + this.sameActionsTransitionsCount + ", ");
		sb.append("Same actions transitions ratio: " + this.sameActionsTransitionsRatio + "\n");
		
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return commonParamsToString() + packageDistParamsToString() + durationParamsToString() + sameActionsParamsToString();
	}
}
