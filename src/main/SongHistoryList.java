package main;

public class SongHistoryList {
	
	private Song first;
	
	public SongHistoryList() {
		first = null;
	}
	
	public void addSong(String s) {
		
		// Create temporary node with data
		Song temp = new Song();
		temp.setName(s);
		
		// temp gets put at top
		temp.setNext(first);
		first = temp;
	}
	
	public String lastListened() {
		
		// Returns song name at top of stack
		// If there is nothing, return error message
		if(first == null) {
			return ("History is empty\n");
		}
		return "Last Listened To:\n" + first.getName() + "\n";
	}
	
	public String output() {
		
		// Begin at top
		Song temp = first;
		
		// Instantiate output String
		String output = "History:\n";
		
		// Go through Stack and add to output
		while(temp != null) {
			output += temp.getName() + "\n";
			temp = temp.getNext();
		}
		
		return output;
	}
}