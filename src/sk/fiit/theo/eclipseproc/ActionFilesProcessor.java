package sk.fiit.theo.eclipseproc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sk.fiit.theo.eclipseproc.actions.EclipseAction;
import sk.fiit.theo.eclipseproc.filter.ActionFilterIF;
import sk.fiit.theo.eclipseproc.filter.ValidActionsFilter;
import sk.fiit.theo.eclipseproc.weka.WekaDataEncoder;
import sk.fiit.theo.eclipseproc.xmlparser.EclipseActionParser;

public class ActionFilesProcessor {
	
	EclipseActionParser parser = new EclipseActionParser();
	WekaDataEncoder encoder = new WekaDataEncoder();
	
	private final List<ActionFilterIF> filters = new ArrayList<ActionFilterIF>();
	
	public void applyFilter(final ActionFilterIF filter) {
		this.filters.add(filter);
	}
	
	public void processAllActionFiles(final String directory) throws IOException {
		final File dir = new File(directory);
		final FileWriter writer = new FileWriter("weka-data.txt");
		int processed = 0, accepted = 0, rejected = 0, contextChanges = 0, noContextChanges = 0;
		try {
			final String[] fileNames = dir.list();
			
			for (int i=0; i<fileNames.length; i++) {
				processed++;
				final EclipseAction action = this.parser.parseEclipseAction(dir.getAbsolutePath() + File.separator + fileNames[i]); 
				if (filterAction(action)) {
					System.out.println(">>>>>>>>>>>>>> Action accepted by all filters");
					System.out.println(action);
					accepted++;
					if (action.isContextChange()) {
						contextChanges++;
					} else {
						noContextChanges++;
					}
					final String line = this.encoder.encodeEclipseActionForWeka(action);
					writer.write(line + "\n");
				} else {
					System.out.println(">>>>>>>>>> Action rejected by one or more filters");
					System.out.println(action);
					rejected++;
				}
			}
			System.out.println("Processed actions: " + processed + ", accepted actions: " + accepted + ", rejected actions: " + rejected);
			System.out.println("Context changes: " + contextChanges + ", No context changes: " + noContextChanges);
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
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
	
	public static void main(final String[] args) throws Exception {
		String directory = "E:\\eclipse_log_files";
		if (args.length == 1) {
			directory = args[0];
		}
		final ActionFilesProcessor processor = new ActionFilesProcessor();
		final ActionFilterIF validFilter = new ValidActionsFilter();
		processor.applyFilter(validFilter);
		
		processor.processAllActionFiles(directory);
		
	}

}
