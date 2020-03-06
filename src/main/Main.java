package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
	public static void main(String args[]) {
		try {
			
			// Array of file names of all global weekly lists from the past quarter
			String[] files = new String[] {
					"regional-global-weekly-2020-01-03--2020-01-10.csv",
					"regional-global-weekly-2020-01-10--2020-01-17.csv",
					"regional-global-weekly-2020-01-17--2020-01-24.csv",
					"regional-global-weekly-2020-01-24--2020-01-31.csv",
					"regional-global-weekly-2020-01-31--2020-02-07.csv",
					"regional-global-weekly-2020-02-07--2020-02-14.csv",
					"regional-global-weekly-2020-02-14--2020-02-21.csv",
					"regional-global-weekly-2020-02-21--2020-02-28.csv"
					};
			
			// Will contain all songs from files combined
			LinkedList<String> songs = new LinkedList<>();
			
			for(int i = 0; i < files.length; i++) {

				// Parses the files and stores parsed info in data[][]
				String[][] data = parseFile(files[i]);

				// Turns info stored in data into Song objects and adds non-duplicates to the unordered playlist 
				for(int j = 0; j < 200; j++) {
					if(!songs.contains(data[j][1]))
						songs.add(data[j][1]);
				}
			}

			// Uses Collections' sort method to sort with Song class' compare method
			Collections.sort(songs, new Song());

			Playlist playlist = new Playlist();
			
			// Output ordered list of songs
			FileWriter wr = new FileWriter("src/main/output/Songs (Ordered).txt");
			for(String song : songs) {
				wr.write(song + "\n");
				playlist.addSong(song);
			}
			wr.close();
			
			// Output playlist history
			wr = new FileWriter("src/main/output/Playlist History.txt");
			wr.write(
			playlist.getHistory().lastListened() + "\n"
			+ playlist.listenToSong() + "\n"
			+ playlist.listenToSong() + "\n"
			+ playlist.listenToSong() + "\n"
			+ playlist.listenToSong() + "\n"
			+ playlist.getHistory().lastListened() + "\n"
			+ playlist.getHistory().output()
			);
			
			wr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[][] parseFile(String fileName) throws FileNotFoundException, IOException {
		String[][] topStreamedList = new String[200][5];
		BufferedReader reader = null;
		
		// Create BufferedReader in one line
		reader = new BufferedReader(new FileReader(new File("src/main/resources/" + fileName)));

		// Skip note and row with column names
		reader.readLine();
		reader.readLine();

		for (int row = 0; row < topStreamedList.length; row++) {
			
			// Eventually inserted into topStreamedList[row]
			String[] finalRow = new String[5];
			
			// Used for fixing 'Artist' section with extra commas after dealing with 'Track Name' commas
			String[] afterTitle;

			// Simplify line by removing quotes and replacing brackets with parenthesis
			String line = reader.readLine().replace("\"", "").replace("[", "(").replace("]", ")");
			
			// First, dealing with extra commas in 'Track Name'
			// If line has a (feat. [artists]) in the title
			if (line.contains("(")) {

				// Fill in properly at first two indexes
				finalRow[0] = line.substring(0, line.indexOf(","));
				finalRow[1] = line.substring(line.indexOf(",") + 1).substring(0, line.substring(line.indexOf(",") + 1).indexOf(")") + 1);

				// Split from end of title so the commas in (feat. [artists]) don't get split
				afterTitle = line.substring(line.lastIndexOf(")"))
						.substring(line.substring(line.lastIndexOf(")")).indexOf(",") + 1).split(",");
			}

			else {
				finalRow[0] = line.substring(0, line.indexOf(","));
				finalRow[1] = line.substring(line.indexOf(",") + 1).substring(0, line.substring(line.indexOf(",") + 1).indexOf(","));
				afterTitle = (line.substring(line.indexOf(",") + 1) .substring(line.substring(line.indexOf(",") + 1).indexOf(",") + 1)).split(",");
			}

			// Then, dealing with extra commas in 'Artists'

			// If there more than one artist listed, add them to index 0 of afterTitle where they belong
			if (afterTitle.length > 3) {
				for(int i = 1; i < afterTitle.length - 2; i++) {
					afterTitle[0] += "," + afterTitle[i];
				}
				// Push 'Streams' and 'URL' to where they belong
				afterTitle[1] = afterTitle[afterTitle.length - 2];
				afterTitle[2] = afterTitle[afterTitle.length - 1];
			}

			// Finish filling in finalRow
			for (int i = 0; i < 3; i++)
				finalRow[i + 2] = afterTitle[i];

			topStreamedList[row] = finalRow;
		}
		
		reader.close();
		return topStreamedList;
	}
	
}
