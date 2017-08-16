package com.test.comtek.foldercomparator.comparator;

import java.util.Set;

public class CompareResult {
	private final Set<String> fileListFromFolder1;
	private final Set<String> fileListFromFolder2;

	public CompareResult(Set<String> fileListFromFolder1, Set<String> fileListFromFolder2) {
		this.fileListFromFolder1 = fileListFromFolder1;
		this.fileListFromFolder2 = fileListFromFolder2;
	}

	public Set<String> getFileListFromFolder1() {
		return fileListFromFolder1;
	}

	public Set<String> getFileListFromFolder2() {
		return fileListFromFolder2;
	}

	@Override
	public String toString() {
		return "ComparatorCompareResult [fileListFromFolder1=" + fileListFromFolder1 + ", fileListFromFolder2="
				+ fileListFromFolder2 + "]";
	}
}