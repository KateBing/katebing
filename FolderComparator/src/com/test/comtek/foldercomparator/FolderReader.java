package com.test.comtek.foldercomparator;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FolderReader {

	private final String folderName;
	private Set<String> resultSet = new HashSet<>();

	public FolderReader(String folderName) {
		this.folderName = folderName;
	}

	public Set<String> readFileList() {
		resultSet.clear();
		File rootFolderFile = new File(this.folderName);
		listFilesForFolder("", rootFolderFile);
		return resultSet;
	}

	private void listFilesForFolder(String dir, File folder) {
		String subDir = dir + "/";
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				String newSubDir = subDir + fileEntry.getName();
				listFilesForFolder(newSubDir, fileEntry);
			} else {
				resultSet.add(subDir + fileEntry.getName());
			}
		}
	}
}
