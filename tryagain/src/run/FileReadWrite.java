package run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class FileReadWrite {

	BufferedReader breader;
	FileReader reader;
	FileWriter writer;
	BufferedWriter bufferedWriter;
	
	String fPath;
	File f;
	
	
	public FileReadWrite(String filePath) {
		f = new File(filePath);
		fPath = filePath;
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
		} catch (Exception e) {
			e.printStackTrace();
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

	public static void main(String[] args) {
		Random r = new Random();
		FileReadWrite frw = new FileReadWrite("Res/score.jafn");
		frw.readScore();
		frw.writeScore("score=" + r.nextInt(36));
		frw.readScore();
		
	}

}
