package com.test.comtek.foldercomparator;

import java.io.File;
import java.util.Objects;

public class ArgsValidator {
	private static final String ERROR_EMPTY_ARGS = "Check incoming arguments, args is null";
	private static final String ERROR_ARGS_COUNT = "Check incoming arguments. The arguments shoud be: folder1, folder2, resultFolder";
	private static final String ERROR_FOLDER_NOT_EXISTS = "The folder %s does not exists";
	private static final String ERROR_FOLDER_NOT_DIRECOTRY = "The folder %s does not directory";

	public static ArgSettings checkAndReadArgs(String[] args) throws IllegalArgumentException {
		Objects.requireNonNull(args, ERROR_EMPTY_ARGS);
		if (args.length != 3) {
			throw new IllegalArgumentException(ERROR_ARGS_COUNT);
		}
		validateDirectory(args[0]);
		validateDirectory(args[1]);
		validateDirectory(args[2]);

		return new ArgSettings(args[0], args[1], args[2]);
	}

	private static void validateDirectory(String folder) {
		File fileFolder = new File(folder);
		// file exists - this is good, but make sure this is directory.
		if (!fileFolder.exists()) {
			String message = String.format(ERROR_FOLDER_NOT_EXISTS, folder);
			throw new IllegalArgumentException(message);
		}
		if (!fileFolder.isDirectory()) {
			String message = String.format(ERROR_FOLDER_NOT_DIRECOTRY, folder);
			throw new IllegalArgumentException(message);
		}
	}
}
