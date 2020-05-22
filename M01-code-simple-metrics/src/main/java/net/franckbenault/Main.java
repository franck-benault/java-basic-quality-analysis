package net.franckbenault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {
		//String root = args[0];
		String root = ".\\src\\main";
      
        List<FileStatistic> files = readCodeSource(root);             
        LongSummaryStatistics summary = calculateStatistic(files);   
        printStatistic(files, summary); 
               
	}

	private static void printStatistic(List<FileStatistic> files, LongSummaryStatistics summary) {
		System.out.println(summary);     
        files.stream()
	      .filter(f -> f.getNbLines()==summary.getMin())
	      .forEach(f -> System.out.println("smallest file "+f) );   
        files.stream()
      	      .filter(f -> f.getNbLines()==summary.getMax())
      	      .forEach(f -> System.out.println("biggest file "+f));
	}

	private static LongSummaryStatistics calculateStatistic(List<FileStatistic> files) {
		LongSummaryStatistics summary = files
      	      .stream()
      	      .mapToLong(f -> f.getNbLines())
      	      .summaryStatistics();
		return summary;
	}

	private static List<FileStatistic> readCodeSource(String root) throws IOException {
		List<FileStatistic> files;
		files = Files.walk(Paths.get(root))
        .filter(Files::isRegularFile)
        .filter(f -> f.toString().endsWith(".java"))
        .map(f -> FileStatistic.getFileStatisticFromFile(f.toFile()))
        .collect(Collectors 
                .toCollection(ArrayList::new));
		return files;
	}



}
