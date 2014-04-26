package sk.fiit.theo.eclipseproc.files;

import java.io.File;

public class FileRename {

	public void renameAllFilesInDir(final String dirpath, final String userName) {
		final File dir = new File(dirpath);
		final String[] fileNames = dir.list();
		for (final String fileName : fileNames) {
			System.out.println("Renaming: " + fileName);
			final File file = new File(dirpath + "\\" + fileName);
			
			final String newFileName = getBaseFileName(fileName) + "_" + userName + ".xml";
			System.out.println("New fileName: " + newFileName);
			
			final File newFile = new File(dirpath + "\\" + newFileName);
			System.out.println(file.renameTo(newFile));
		}
	}
	
	private String getBaseFileName(final String fileName) {
		final String base = fileName.substring(0, fileName.indexOf(".xml"));
		return base;
	}
	
	public static void main(final String[] args) {
		final FileRename fileRename = new FileRename();
//		String dir = "e:\\DIPLOMKA\\experiments\\iit.src\\work";
//		String dir = "e:\\DIPLOMKA\\experiments\\iit.src\\orig";
		String dir = "e:\\DIPLOMKA\\experiments\\iit.src\\eclipselog";
		String name = "Eclipse";
		if (args.length == 2) {
			dir = args[0];
			name = args[1];
		}
		
		fileRename.renameAllFilesInDir(dir, name);
		
	}
	
}
