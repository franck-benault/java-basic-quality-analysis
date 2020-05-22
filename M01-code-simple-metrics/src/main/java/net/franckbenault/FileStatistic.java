package net.franckbenault;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileStatistic {

	private String fullPathName;
	private String name;
	private long nbLines;
	
	
	public static FileStatistic getFileStatisticFromFile(File file) {
		
		long nbLines =0;
		try {
			nbLines = nbLines(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new FileStatistic(file.getAbsolutePath(),file.getName(),nbLines);
	}

	public long getNbLines() {
		return nbLines;
	}



	private static long nbLines(File file) throws IOException {
		long numOfLines = 0;
		try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset())) {
			  numOfLines = lines.count();

		}
		return numOfLines;
	}
	
	public FileStatistic(String fullPathName, String name, long nbLines) {

		this.fullPathName = fullPathName;
		this.name = name;
		this.nbLines = nbLines;
	}


	@Override
	public String toString() {
		return "FileStatistic [fullPathName=" + fullPathName + ", name=" + name + ", nbLines=" + nbLines + "]";
	}
	

	
	
	
	
}
