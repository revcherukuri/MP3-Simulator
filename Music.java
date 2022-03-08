/**
 *
 * @author Revanth Cherukuri
 * @author George Gomez
 * @author Roey Rubin
 * @author Matthew Sun
 * CIS 36B MP3Player Course Project
 */
public abstract class Music {
    private String title;
    private String artist;
    private int numDownloads;

    public Music() {
        this("Title unknown", "Artist unknown", 0);
    }

    public Music(String title, String artist, int numDownloads) {
        this.title = title;
        this.artist = artist;
        this.numDownloads=numDownloads;
    }
    public void updateDownloads() {
        numDownloads++;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;

    }

    public int getNumDownloads() {
        return numDownloads;
    }



}
