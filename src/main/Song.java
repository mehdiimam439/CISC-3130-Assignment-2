package main;

import java.util.Comparator;

// Made into a comparator to be able to sort songs while ignoring case in Main
public class Song implements Comparable<Object>, Comparator<Object>{
	
	private String name;
	private Song next;
	
	// Accessors
	public String getName() {
		return name;
	}
	
	public Song getNext() {
		return next;
	}
	
	// Mutators
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNext(Song next) {
		this.next = next;
	}
	
	// Overriding Object class' equals method to fit Song object
	public boolean equals(Object o) {
		return o instanceof Song && name.equals(((Song) o).name);
	}
	
	// Implementing Comparable interface's compareTo method
	public int compareTo(Object s) {
		return name.compareTo(((Song)s).name);
	}
	
	// Implementing Comparator interface's compare method
	public int compare(Object o1, Object o2) {
        return ((String) o1).toLowerCase().compareTo(((String) o2).toLowerCase());
    }
	
	// Overriding Object class' toString method
	public String toString() {
		
		// toString() returns name along with the next song only if next is not null
		return name + (next != null ? "\nNext Up: " + next.name : "");
	}

}
