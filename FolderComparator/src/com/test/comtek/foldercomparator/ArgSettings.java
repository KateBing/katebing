package com.test.comtek.foldercomparator;

public class ArgSettings {
	private final String folder1;
	private final String folder2;
	private final String resultFolder;

	public ArgSettings(String folder1, String folder2, String reultFolder) {
		this.folder1 = folder1;
		this.folder2 = folder2;
		this.resultFolder = reultFolder;
	}

	public String getFolder1() {
		return folder1;
	}

	public String getFolder2() {
		return folder2;
	}

	public String getResultFolder() {
		return resultFolder;
	}

	@Override
	public String toString() {
		return "ArgSettings [folder1=" + folder1 + ", folder2=" + folder2 + ", resultFolder=" + resultFolder + "]";
	}
}
