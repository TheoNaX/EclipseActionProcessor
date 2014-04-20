package sk.fiit.theo.eclipseproc.session;


public class SessionMain {
	
	public static void main(final String[] args) throws Exception {
		
		String directory = "E:\\DIPLOMKA\\logs";
		if (args.length == 1) {
			directory = args[0];
		}
		
		final SessionActionProcessor processor = new SessionActionProcessor();
		processor.processAllActionFiles(directory);
	}

}
