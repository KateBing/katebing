package com.test.comtek.foldercomparator;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import com.test.comtek.foldercomparator.comparator.CompareResult;
import com.test.comtek.foldercomparator.comparator.FilesComparator;

public class Main {
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		LOG.info("Start Main.main()");
		// Check and read arguments
		ArgSettings argSettings = ArgsValidator.checkAndReadArgs(args);

		// Cleanup result folder
		FileUtil.cleanupFolder(argSettings.getResultFolder());

		FolderReader folderReader1 = new FolderReader(argSettings.getFolder1());
		Set<String> filesFolder1Set = folderReader1.readFileList();

		FolderReader folderReader2 = new FolderReader(argSettings.getFolder2());
		Set<String> filesFolder2Set = folderReader2.readFileList();

		FilesComparator filesComparator = new FilesComparator(argSettings, filesFolder1Set, filesFolder2Set);

		try {
			CompareResult compareResult = filesComparator.compare();
			FileUtil.copyFilesToFolder(argSettings.getFolder1(), compareResult.getFileListFromFolder1(),
					argSettings.getResultFolder());
			FileUtil.copyFilesToFolder(argSettings.getFolder2(), compareResult.getFileListFromFolder2(),
					argSettings.getResultFolder());
		} catch (IOException ioe) {
			LOG.info("Error:" + ioe);
			LOG.info("Application was finished with ERROR.");
			return;
		}
		LOG.info("Application successfully finished");
	}
}
