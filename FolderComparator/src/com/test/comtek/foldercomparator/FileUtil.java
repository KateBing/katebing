package com.test.comtek.foldercomparator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public class FileUtil {
	public static void cleanupFolder(String folderName) {
		File folder = new File(folderName);
		File[] files = folder.listFiles();
		for (File f : files) {
			deleteFolder(f);
		}
	}

	private static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	public static void copyFilesToFolder(String rootPathSource, Collection<String> fileNameCollection,
			String rootPathResult) throws IOException {
		for (String fileName : fileNameCollection) {
			File sourceFile = new File(rootPathSource, fileName);
			Path sourcePath = sourceFile.toPath();

			File destFile = new File(rootPathResult + fileName);
			Path destPath = destFile.toPath();
			destFile.getParentFile().mkdirs();
			Files.copy(sourcePath, destPath);
		}
	}
}
