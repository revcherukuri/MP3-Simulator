/**
 *
 * @author Revanth Cherukuri
 * @author George Gomez
 * @author Roey Rubin
 * @author Matthew Sun
 * CIS 36B MP3Player Course Project
 */
public class Song extends Music implements Comparable<Song> {

    private String songLength;
    private String genre;
    private static int numSongs;
    private int numStreams;

    public Song() {
        this("Title Unknown", "Artist Unknown", 0,"Genre Unknown", "Length Unknown",0);
    }

    public Song(String title, String artist, String genre, String songLength) {
        this(title, artist, 1, genre, songLength, 0);
    }

    public Song(String title, String artist, int numDownloads, String genre, String songLength, int numStreams) {
        super(title, artist, numDownloads);
        this.genre = genre;
        this.songLength = songLength;
        this.numStreams = numStreams;
        updateNumSongs();

    }

    public void playSong() {
        System.out.println("\nNow Playing: \n" + this.getTitle() + " by " + this.getArtist());
        System.out.println("0:00|---------->-----------|" + this.getSongLength() + "\n");

    }

    public void updateStreams() {
        numStreams++;
    }

    public static void updateNumSongs() {
        numSongs++;
    }

    public String getGenre() {
        return genre;
    }

    public String getSongLength() {
        return songLength;
    }

    public static int getNumSongs() {
        return numSongs;
    }

    @Override
    public String toString() {
        return "Song Title: " + this.getTitle()
                + "\nArtist: " + this.getArtist()
                + "\nGenre: " + this.getGenre()
                + "\nSong Length: " + this.songLength
                + "\nStatistics: \nNumber of Downloads: " + this.getNumDownloads()
                + "\nNumber of Streams: " + this.numStreams + "\n";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Song)) {
            return false;
        } else {
            Song s = (Song) o;
            return (this.getTitle().equalsIgnoreCase(s.getTitle()))&&(this.getArtist().equalsIgnoreCase(s.getArtist()));
        }
    }


    @Override
    public int compareTo(Song s) {
        if(this.equals(s)) {
            return 0;
        } else if(!(this.getTitle().equalsIgnoreCase(s.getTitle()))) {
            return this.getTitle().compareTo(s.getTitle());
        } else if(!(this.getArtist().equalsIgnoreCase(s.getArtist()))) {
            return this.getArtist().compareTo(s.getArtist());
        }
        return -1;
    }

}
