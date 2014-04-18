package sk.fiit.theo.eclipseproc.timeout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sk.fiit.theo.eclipseproc.actions.EclipseAction;
import sk.fiit.theo.eclipseproc.filter.ActionFilterIF;
import sk.fiit.theo.eclipseproc.filter.ValidActionsFilter;
import sk.fiit.theo.eclipseproc.xmlparser.EclipseActionParser;

public class TimeoutActionProcessor {
	
	
	public static final long TIMEOUT_LIMIT_MIN = 10;
	public static final long TIMEOUT_LIMIT = TIMEOUT_LIMIT_MIN * 1000 * 60;
	EclipseActionParser parser = new EclipseActionParser();
	List<ActionFilterIF> filters = new ArrayList<ActionFilterIF>();
	
	private int trueContextChange;
	private int falseContextChange;
	private int trueNoContextChange;
	private int falseNoContextChange;
	
	public void applyFilter(final ActionFilterIF filter) {
		this.filters.add(filter);
	}
	
	public void processAllActionFiles(final String directory) throws IOException {
		final File dir = new File(directory);
		int processed = 0, accepted = 0, rejected = 0, contextChanges = 0, noContextChanges = 0;
		try {
			final String[] fileNames = dir.list();
			
			boolean timeoutContext;
			for (int i=0; i<fileNames.length; i++) {
				processed++;
				final EclipseAction action = this.parser.parseEclipseAction(dir.getAbsolutePath() + File.separator + fileNames[i]); 
				if (filterAction(action)) {
					System.out.println(">>>>>>>>>>>>>> Action accepted by all filters");
					System.out.println(action);
					accepted++;
					final long timeSincelastAction = action.getTimeSinceLastAction();
					if (timeSincelastAction >= TIMEOUT_LIMIT) {
						timeoutContext = true;
					} else {
						timeoutContext = false;
					}
					
					if (action.isContextChange()) {
						if (timeoutContext) {
							this.trueContextChange++;
						} else {
							this.falseNoContextChange++;
						}
					} else {
						if (timeoutContext) {
							this.falseContextChange++;
						} else {
							this.trueNoContextChange++;
						}
					}
					
					if (action.isContextChange()) {
						contextChanges++;
					} else {
						noContextChanges++;
					}
					
				} else {
					System.out.println(">>>>>>>>>> Action rejected by one or more filters");
					System.out.println(action);
					rejected++;
				}
			}
			System.out.println("Processed actions: " + processed + ", accepted actions: " + accepted + ", rejected actions: " + rejected);
			System.out.println("Context changes: " + contextChanges + ", No context changes: " + noContextChanges);
			System.out.println("TIMEOUT RESULTS, LIMIT (min) : " + TIMEOUT_LIMIT_MIN);
			System.out.println("True context changes:" + this.trueContextChange);
			System.out.println("False no context changes: " + this.falseNoContextChange);
			System.out.println("True no context changes: " + this.trueNoContextChange);
			System.out.println("False context changes: " + this.falseContextChange);
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			
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
	
	public static void main(final String[] args) throws IOException {
		String directory = "E:\\eclipse_log_files";
		if (args.length == 1) {
			directory = args[0];
		}
		final TimeoutActionProcessor processor = new TimeoutActionProcessor();
		final ValidActionsFilter filter = new ValidActionsFilter();
		processor.applyFilter(filter);
		processor.processAllActionFiles(directory);
	}

}
