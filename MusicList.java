import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Revanth Cherukuri
 * @author George Gomez
 * @author Roey Rubin
 * @author Matthew Sun
 * CIS 36B MP3Player Course Project
 */
public interface MusicList {

    void fillSongList(Scanner input) throws IOException;

    void bubbleSort(ArrayList<Song> s);

    int binarySearch(ArrayList<Song> songs,Song s);

    void printSongList(ArrayList<Song> s);

}
