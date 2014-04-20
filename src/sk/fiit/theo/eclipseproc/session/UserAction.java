package sk.fiit.theo.eclipseproc.session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sk.fiit.theo.eclipseproc.actions.EclipseAction;

public class UserAction implements Comparable<UserAction> {
	private final EclipseAction action;
	private final String userId;
	private final Date timeStamp;
	private final int actionId;
	private final String fileName;
	
	public UserAction(final EclipseAction action, final String fileName) throws Exception {
		this.action = action;
		this.userId = getUserIdFromFileName(fileName);
		this.actionId = getActionIdFromFileName(fileName);
		this.timeStamp = getTimeStamp(fileName);
		this.fileName = fileName;
	}
	
	public static String getUserIdFromFileName(final String fileName) {
		final String[] parts = getFileNameParts(fileName);
		return parts[3];
	}
	
	private static String getTimestampFromFileName(final String fileName) {
		final String[] parts = getFileNameParts(fileName);
		return parts[1];
	}
	
	private static int getActionIdFromFileName(final String fileName) {
		final String[] parts = getFileNameParts(fileName);
		final int actionId = Integer.parseInt(parts[2]);
		return actionId;
	}
	
	private static String[] getFileNameParts(final String fileName) {
		final String fileNameWithoutExt = fileName.substring(0, fileName.indexOf("."));
		return fileNameWithoutExt.split("_");
	}
	
	private static Date getTimeStamp(final String fileName) throws ParseException {
		final String timestampStr = getTimestampFromFileName(fileName);
		final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		final Date timeStamp = format.parse(timestampStr);
		return timeStamp;
	}
	
	
	public static void main(final String[] args) throws Exception {
		final String fileName = "ACTION_20140418221035_00001_Tomas0148.xml";
		System.out.println(getUserIdFromFileName(fileName));
		System.out.println(getTimestampFromFileName(fileName));
		System.out.println(getActionIdFromFileName(fileName));
		final Date time = getTimeStamp(fileName);
		System.out.println(time);
	}
	
	public Date getTimeStamp() {
		return this.timeStamp;
	}
	
	public int getActionId() {
		return this.actionId;
	}
	
	public EclipseAction getAction() {
		return this.action;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public int compareTo(final UserAction o) {
		final int dateCompareTo = this.timeStamp.compareTo(o.getTimeStamp());
		if (dateCompareTo == 0) {
			if (this.actionId < o.getActionId()) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return dateCompareTo;
		}
	}
	
	@Override
	public String toString() {
		return this.fileName + ": " +  this.action.getActionType();
	}
}
