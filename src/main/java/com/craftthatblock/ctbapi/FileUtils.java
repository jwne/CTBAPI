package com.craftthatblock.ctbapi;

import java.io.*;

/**
 * @author CraftThatBlock
 */
public class FileUtils {
	/**
	 * Fully delete any file/directory
	 *
	 * @param file File to delete
	 * @throws IOException Failed
	 */
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void delete(File file) throws IOException {
		if (file.isDirectory()) {
			if (file.list().length == 0) {
				file.delete();
			} else {
				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					delete(fileDelete);
				}
				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			file.delete();
		}
	}

	/**
	 * Copy a full directory
	 *
	 * @param sourceLocation Copy from
	 * @param targetLocation Copy too
	 * @throws IOException Failed
	 */
	public static void copyDirectory(File sourceLocation, File targetLocation) throws IOException {
		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists()) {
				targetLocation.mkdir();
			}

			String[] children = sourceLocation.list();
			for (String aChildren : children) {
				copyDirectory(new File(sourceLocation, aChildren), new File(targetLocation, aChildren));
			}
		} else {

			InputStream in = new FileInputStream(sourceLocation);
			OutputStream out = new FileOutputStream(targetLocation);

			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}
}
