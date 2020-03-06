# Assignment 2
The purpose of this program is to create an ordered playlist of songs extracted from Spotify top charts and have a playlist history as well that tracks all the songs listened to and the last song listened to. The parseFile() method cleans up the CSV files to be more neat and converts the file's data into a 200 by 5 array (200 rows due to standard Spotify CSV size, 5 columns for position, track name, artist, streams, and url). The songs are sorted in a LinkedList and by means of Collections' sort(List<E>, Comparator<E>) method which sorts a data structure with a comparator object (in this case, the Song class is a comparator). By default, the program extracts all the global top weekly charts from Spotify within the last quarter (Beginning January 1st), sorts the songs by title, and adds them to a playlist. The playlist's listenToSong() method can be used to "listen" to each song which also adds it to the playlist's history, which each playlist has. The program's output files are the songs in the playlist ordered by title and the history from a few passes of the listenToSong() method.

# Important methods for Main
>Main.parseFile(String fileName) - Read in a Spotify top charts CSV file and convert it into a 2D array. Input does not need directory information as long as it is in src/main/resources.

>Playlist.addSong(String name) - Adds a song to the singly linked playlist

>Playlist.listenToSong() - Returns the current song (String) in the playlist and adds it to the history, then moves on to the next song

>SongHistoryList.lastListened()* - returns the last song (String) listened to in the playlist

>SongHistoryList.output()* - returns the entire recorded history (String)

*In order to get the history of a playlist, the syntax is (name of playlist).getHistory.(name of SongHistoryList method)
