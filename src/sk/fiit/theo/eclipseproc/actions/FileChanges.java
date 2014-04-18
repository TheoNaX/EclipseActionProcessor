package sk.fiit.theo.eclipseproc.actions;

public class FileChanges {

	private int changedLines;
	private int deletedLines;
	private int addedLines;
	
	public FileChanges() {}
	
	public FileChanges(final int changed, final int deleted, final int added) {
		this.changedLines = changed;
		this.deletedLines = deleted;
		this.addedLines = added;
	}
	
	public void updateFileChanges(final FileChanges changes) {
		this.changedLines += changes.getChangedLines();
		this.deletedLines += changes.getDeletedLines();
		this.addedLines += changes.getAddedLines();
	}

	public int getChangedLines() {
		return this.changedLines;
	}

	public void increaseChangedLines(final int changedLines) {
		this.changedLines += changedLines;
	}

	public int getDeletedLines() {
		return this.deletedLines;
	}

	public void increaseDeletedLines(final int deletedLines) {
		this.deletedLines += deletedLines;
	}

	public int getAddedLines() {
		return this.addedLines;
	}

	public void increaseAddedLines(final int addedLines) {
		this.addedLines += addedLines;
	}
	
	@Override
	public String toString() {
		return "File line changes: changed: " + this.changedLines + ", added: " + this.addedLines
				+ ", deleted: " + this.deletedLines;
	}

	
}
