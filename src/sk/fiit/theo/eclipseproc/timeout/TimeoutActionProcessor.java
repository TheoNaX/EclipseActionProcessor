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
	
	
	public void applyFilter(final ActionFilterIF filter) {
		this.filters.add(filter);
	}
	
	public void processAllActionFiles(final String directory, final int minutesLimit) throws IOException {
		
		int trueContextChange = 0;
		int falseContextChange = 0;
		int trueNoContextChange = 0;
		int falseNoContextChange = 0;
		
		
		final File dir = new File(directory);
		int processed = 0, accepted = 0, rejected = 0, contextChanges = 0, noContextChanges = 0;
		final long limit = minutesLimit * 60 * 1000;
		try {
			final String[] fileNames = dir.list();
			
			boolean timeoutContext;
			for (int i=0; i<fileNames.length; i++) {
				processed++;
				final EclipseAction action = this.parser.parseEclipseAction(dir.getAbsolutePath() + File.separator + fileNames[i]); 
				if (filterAction(action)) {
					accepted++;
					final long timeSincelastAction = action.getTimeSinceLastAction();
					if (timeSincelastAction >= limit) {
						timeoutContext = true;
					} else {
						timeoutContext = false;
					}
					
					if (action.isContextChange()) {
						if (timeoutContext) {
							trueContextChange++;
						} else {
							falseNoContextChange++;
						}
					} else {
						if (timeoutContext) {
							falseContextChange++;
						} else {
							trueNoContextChange++;
						}
					}
					
					if (action.isContextChange()) {
						contextChanges++;
					} else {
						noContextChanges++;
					}
					
				} else {
					rejected++;
				}
			}
			System.out.println("Processed actions: " + processed + ", accepted actions: " + accepted + ", rejected actions: " + rejected);
			System.out.println("Context changes: " + contextChanges + ", No context changes: " + noContextChanges);
			System.out.println("TIMEOUT RESULTS, LIMIT (min) : " + minutesLimit);
			System.out.println("True context changes:" + trueContextChange);
			System.out.println("False no context changes: " + falseNoContextChange);
			System.out.println("True no context changes: " + trueNoContextChange);
			System.out.println("False context changes: " + falseContextChange);
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
		String directory = "e:\\DIPLOMKA\\experiments\\iit.src\\data";
		if (args.length == 1) {
			directory = args[0];
		}
		final TimeoutActionProcessor processor = new TimeoutActionProcessor();
		final ValidActionsFilter filter = new ValidActionsFilter();
		processor.applyFilter(filter);
		//processor.processAllActionFiles(directory);
		computeForDifferentValues(directory, processor);
	}
	
	private static void computeForDifferentValues(final String directory, final TimeoutActionProcessor processor) throws IOException {
		final int limits[] = new int[] {5, 6, 7, 8, 9, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
		for (int i=0; i<limits.length; i++) {
			System.out.println("Timeout limit: " + limits[i] + " minutes");
			processor.processAllActionFiles(directory, limits[i]);
			System.out.println();
			System.out.println();
		}
		
	}

}
