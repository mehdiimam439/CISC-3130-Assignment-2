package main;

public class Playlist {
	
	Song first;
	SongHistoryList history = new SongHistoryList();
	
	public void addSong(String name) {
		
		// Create node we want to insert
		Song song = new Song();
		song.setName(name);
		
		// If playlist is empty, set first node to our node
		if(first == null)
			first = song;
		
		else {	
			
			// Get first node
			Song s = first;
			
			// Go through playlist until we reach the last node
			while(s.getNext() != null)
				s = s.getNext();
			
			// Our node gets inserted after last node
			s.setNext(song);
		}
	}
	
	public String listenToSong() {
		String output = first.getName();
		history.addSong(first.getName());
		first = first.getNext();
		return "Listening To:\n" + output + "\n";
	}
	
	public SongHistoryList getHistory() {
		return history;
	}
	
	public String output() {
		
		// Initialize output String
		String output = "";
		
		// Get first node
		Song song = first;
		
		// Go through playlist and add data to output in each node until we reach last node
		while(song.getNext() != null) {
			output += song.getName() + "\n";
			song = song.getNext();
		}
		
		// Add data of last node where loop ended and return output
		return output + song.getName();
	}
	
}
