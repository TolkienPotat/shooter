package run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileReadWrite {

	BufferedReader breader;
	FileReader reader;
	FileWriter writer;
	BufferedWriter bufferedWriter;
	
	String fPath;
	File f;
	
	
	public FileReadWrite(String filePath, String directory) {
		
		
		File filedir = new File(directory);
		if (!filedir.exists()) {
			if (filedir.mkdirs()) {
				System.out.println("Filepath to game files not found, creating one");
			}
		}
		f = new File(directory + filePath);
		fPath = directory + filePath;

		
		try {
			if (f.createNewFile()) {
				System.out.println("File Created");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			reader = new FileReader(f);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid FilePath");
			e.printStackTrace();
		}
		breader = new BufferedReader(reader);
		try {
			writer = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufferedWriter = new BufferedWriter(writer);
	}

	public void writeScore(String s) {
		PrintWriter pw;
		try {
			
			pw = new PrintWriter(fPath);
			pw.close();
			bufferedWriter.write(s);
			bufferedWriter.close();
			System.out.println("Score saved");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error, score not saved");
		}
		
		
	}
	
	public int readScore() {
		int i = 0;
		try {
			String line;
			line = breader.readLine();
			
			reader = new FileReader(f);
			breader = new BufferedReader(reader);
			
			i = Integer.parseInt(line.replaceAll("[^0-9]+", ""));
			System.out.println("Current highscore is " + i);
			
		} catch (IOException e) {
			System.out.println("Error fetching score");
		}

		return i;
		
	}

	
}
