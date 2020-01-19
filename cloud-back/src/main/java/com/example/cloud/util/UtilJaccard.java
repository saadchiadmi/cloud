package com.example.cloud.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.cloud.entities.Book;
import com.example.cloud.repository.BookRepository;

public class UtilJaccard {

	public static List<String> getOrderedFileNames(String directory){
		try(Stream<Path> files = Files.walk(Paths.get(directory))){
			return files.filter(Files::isRegularFile)
			.map(f -> f.getFileName().toString())
			.sorted()
			.collect(Collectors.toList());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void computeJaccard(List<Book> files, BookRepository bookRepository) {
		files.stream()
		.forEach(f -> {
			try {
				computeJaccardFile(f, files, bookRepository);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	private static void computeJaccardFile(Book file,List<Book> otherFiles, BookRepository bookRepository) throws IOException {
		BufferedWriter bf =Files.newBufferedWriter(Paths.get("Indice-de-centralite/jaccard.txt"));
		otherFiles.stream()
		.filter(f2 -> f2.getName().compareTo(file.getName()) > 0)
		.forEach(f2 -> distanceJaccard(bf,file, f2, bookRepository));
		bf.close();
	}
	
	private static void distanceJaccard(BufferedWriter bf,Book f1, Book f2, BookRepository bookRepository) {
		try {
			Map<String,Long> map1 = bookRepository.findById(f1.getName()).get().getIndex().stream()
					.collect(Collectors.toMap(a -> a.getWord(), a -> a.getOccurence()));
			Map<String,Long> map2 = bookRepository.findById(f2.getName()).get().getIndex().stream()
					.collect(Collectors.toMap(a -> a.getWord(), a -> a.getOccurence()));
			map1.keySet().retainAll(map2.keySet());
			map2.keySet().retainAll(map1.keySet());
			String res = f1.getName()+" "+f2.getName()+" "+computeJaccardMap(map1, map2)+"\n";
			bf.write(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static double computeJaccardMap(Map<String,Long> m1, Map<String,Long> m2) {
		Long num = m1.entrySet().stream()
				.mapToLong(e -> Math.abs(e.getValue()-m2.get(e.getKey()))).sum();
		Long den = m2.entrySet().stream()
				.mapToLong(e -> Math.max(e.getValue(), m1.get(e.getKey()))).sum();
		
		if(den > 0) {
			return  num*1.0/den;
		}
		else {
			return 0.0;
		}
		
	}
	
}
