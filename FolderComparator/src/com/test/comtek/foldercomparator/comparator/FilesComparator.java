package com.test.comtek.foldercomparator.comparator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.test.comtek.foldercomparator.ArgSettings;

public class FilesComparator {
	private final ArgSettings argSettings;

	private final Set<String> fileNameListOne;
	private final Set<String> fileNameListTwo;

	private final int BUFFER_SIZE = 4096;

	public FilesComparator(ArgSettings argSettings, Set<String> fileNameListOne, Set<String> fileNameListTwo) {
		this.argSettings = argSettings;
		this.fileNameListOne = fileNameListOne;
		this.fileNameListTwo = fileNameListTwo;
	}

	public CompareResult compare() throws IOException {

		Set<String> resultFolder1 = new HashSet<>();
		Set<String> resultFolder2 = new HashSet<>();

		for (String fileName : fileNameListOne) {
			if (fileNameListTwo.contains(fileName)) {
				if (!binaryCompare(fileName)) {
					resultFolder1.add(fileName);
				}
			} else {
				resultFolder1.add(fileName);
			}
		}

		for (String fileName : fileNameListTwo) {
			if (!fileNameListOne.contains(fileName)) {
				resultFolder2.add(fileName);
			}
		}

		return new CompareResult(resultFolder1, resultFolder2);
	}

	private boolean binaryCompare(String fileName) throws IOException {
		if (!compareByFileSize(fileName)) {
			return false;
		}
		return compareByContent(fileName);
	}

	private boolean compareByFileSize(String fileName) throws IOException {
		Path path1 = Paths.get(argSettings.getFolder1() + fileName);
		Path path2 = Paths.get(argSettings.getFolder1() + fileName);

		long f1Size = Files.size(path1);
		long f2Size = Files.size(path2);
		if (f1Size != f2Size) {
			return false;
		}
		return true;
	}

	private boolean compareByContent(String fileName) throws IOException {

		File fileFromFolder1 = new File(argSettings.getFolder1() + fileName);
		File fileFromFolder2 = new File(argSettings.getFolder2() + fileName);

		InputStream ios1 = null;
		InputStream ios2 = null;

		byte[] buffer1 = new byte[BUFFER_SIZE];
		byte[] buffer2 = new byte[BUFFER_SIZE];

		// we know that files size are equals.

		ios1 = new FileInputStream(fileFromFolder1);
		ios2 = new FileInputStream(fileFromFolder2);
		boolean continueReading = true;
		boolean localResult = true;

		while (continueReading) {

			int readBytes1 = ios1.read(buffer1);
			int readBytes2 = ios2.read(buffer2);
			if (readBytes1 != readBytes2) {
				localResult = false;
				break;
			}

			localResult = Arrays.equals(buffer1, buffer2);
			if (readBytes1 < BUFFER_SIZE && localResult) {
				continueReading = false;
			}
		}
		ios1.close();
		ios2.close();

		return localResult;
	}
}
