import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Revanth Cherukuri
 * @author George Gomez
 * @author Roey Rubin
 * @author Matthew Sun CIS 36B MP3Player Course Project
 */
public class MP3Player implements MusicList {
    private static final String filename = "songs.txt";
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Song> playlist = new ArrayList<Song>();
    StringBuilder playlistName = new StringBuilder("Unnamed Playlist");

    public static void main(String[] args) {
        MP3Player m = new MP3Player();

        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            m.fillSongList(input);
            input.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }

        String choice = "";
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the MP3 App \n\nHere is our current selection of songs:\n\nWe currently have "
                + Song.getNumSongs() + " songs");
        m.printSongList(m.songs);
        while (!choice.equalsIgnoreCase("quit")) {
            System.out.println("\nPlease select from one of the options or 'quit' to exit the program: \n");
            System.out.println("1. View full detailed song list");
            System.out.println("2. View your custom playlist: " + m.playlistName);
            System.out.println("3. Add song to " + m.playlistName);
            System.out.println("4. Add a song to the music list ");
            System.out.println("5. Search for songs ");
            System.out.println("6. Play a song ");
            System.out.println("7. Change Playlist Name");
            System.out.print("\nEnter your choice: ");
            choice = input.nextLine();
            System.out.println();
            if (choice.equals("1")) {
                if (m.songs.size() == 0)
                    System.out.println("You have no songs in the songlist");
                for (int i = 0; i < m.songs.size(); i++) {
                    System.out.println(m.songs.get(i).toString());
                }
            } else if (choice.equals("2")) {
                if (m.playlist.size() == 0)
                    System.out.println("Playlist is empty");
                for (int i = 0; i < m.playlist.size(); i++) {
                    System.out.println(m.playlist.get(i).toString());
                }
            } else if (choice.equals("3")) {
                System.out.print(
                        "Create new song or add from song list: \n1. Create new song\n2. Add song from full song list\nEnter your choice: ");
                choice = input.nextLine();
                if (choice.equals("1")) {
                    System.out.print("\nEnter Song Name: ");
                    String songName = input.nextLine();
                    System.out.print("Enter Author: ");
                    String author = input.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = input.nextLine();
                    System.out.print("Enter Song Length: ");
                    String songLength = input.nextLine();
                    Song tempSong = new Song(songName, author, genre, songLength);
                    m.playlist.add(tempSong);
                    System.out.println("You've added " + songName + " to " + m.playlistName);
                } else if (choice.equals("2")) {
                    System.out.println("\nChoose which song from below to add into the playlist:\n");
                    m.printSongList(m.songs);
                    System.out.print("Enter song number(1-" + Song.getNumSongs() + "): ");
                    choice = input.nextLine();
                    int index = Integer.parseInt(choice) - 1;

                    m.playlist.add(m.songs.get(index));

                    System.out
                            .println("\nSuccessfully added " + m.songs.get(index).getTitle() + " to " + m.playlistName);

                }
            } else if (choice.equals("4")) {
                System.out.print("Enter Song Name: ");
                String songName = input.nextLine();
                System.out.print("Enter Author: ");
                String author = input.nextLine();
                System.out.print("Enter Genre: ");
                String genre = input.nextLine();
                System.out.print("Enter Song Length: ");
                String songLength = input.nextLine();
                m.songs.add(new Song(songName, author, genre, songLength));
                System.out.println("You've added " + songName + " to the song list");
            } else if (choice.equals("5")) {

                System.out.println("a) Search song by name\nb) View list of songs first");
                String searchType = input.nextLine();
                boolean loop = true;
                while (loop) {
                    if (searchType.equalsIgnoreCase("a")) {
                        loop = false;
                    } else if (searchType.equalsIgnoreCase("b")) {
                        m.printSongList(m.songs);
                        loop = false;
                    } else {
                        System.out.println("Invalid option!\n");
                        System.out.println("a) Search song by name\nb) View list of songs first");
                        searchType = input.nextLine();
                    }
                }

                System.out.print("Enter the name of the song you wish to search: ");
                String songName = input.nextLine();
                System.out.print("Enter the artist of the song you wish to search: ");
                String artistName = input.nextLine();
                Song s = new Song(songName, artistName, "", "");
                m.bubbleSort(m.songs);
                int songIndex = m.binarySearch(m.songs, s);
                if (songIndex < 0) {
                    System.out.println("\nSorry, the song " + songName + " by " + artistName
                            + " does not exist in our database.\n");
                } else {
                    System.out.println("\nHere is the song you searched for: \n\n" + m.songs.get(songIndex));
                }

            } else if (choice.equals("6")) {

                System.out.print(
                        "Select an option below:\n1. Search for a song to play \n2. Start Playlist\n3. Start song list\n\nEnter your choice(1-3): ");
                choice = input.nextLine();

                if (choice.equalsIgnoreCase("1")) {
                    System.out.print("Enter a song to play: ");
                    String songName = input.nextLine();
                    boolean loop = true;
                    while (loop) {
                        for (int i = 0; i < m.songs.size(); i++) {
                            if (m.songs.get(i).getTitle().equals(songName)) {
                                m.songs.get(i).playSong();
                                loop = false;
                            }
                        }
                        break;
                    }
                    if (loop) {
                        System.out.println("\nSorry, the song " + songName + " does not exist in our database.\n");
                    }

                }

                else if (choice.equalsIgnoreCase("2")) {
                    if (m.playlist.size() == 0) {
                        System.out.print(
                                "\nYour playlist is empty! Make sure you add songs here to choose from a collection of your favorites.\nWould you like to play a song from the song list instead? Y/N: ");
                        String listChoice = input.nextLine();
                        if (listChoice.equalsIgnoreCase("Y")) {
                            System.out.println();
                            m.printSongList(m.songs);
                            System.out.println("1. Select song\n2. Start song list\n3. Shuffle");
                            String playSelect = input.nextLine();
                            if (playSelect.equalsIgnoreCase("1")) {
                                System.out.print("What song do you want to play from the list?(1-" + Song.getNumSongs()
                                        + ")\nEnter your choice: ");
                                choice = input.nextLine();
                                int index = Integer.parseInt(choice) - 1;
                                if (index >= Song.getNumSongs() || index < 0) {
                                    System.out.println("Invalid Song Index.");
                                } else {
                                    m.songs.get(index).playSong();
                                }
                            }
                            else if (playSelect.equalsIgnoreCase("2")) {
                                m.songs.get(0).playSong();
                            } else if (playSelect.equalsIgnoreCase("3")) {
                                m.songs.get(((int) (Math.random() * (m.songs.size())))).playSong();
                            } else {
                                System.out.println("\nInvalid Option.\n");
                            }
                        } else {
                            System.out.println();
                        }
                    } else {
                        System.out.println("\n" + m.playlistName + ":\n");
                        m.printSongList(m.playlist);
                        System.out.println("1. Select song\n2. Start playlist\n3. Shuffle");
                        String playSelect = input.nextLine();
                        if (playSelect.equalsIgnoreCase("1")) {
                            System.out.print("What song do you want to play from playlist: " + m.playlistName + "?(1-" + m.playlist.size()
                                    + ")\nEnter your choice: ");
                            choice = input.nextLine();
                            int index = Integer.parseInt(choice) - 1;
                            if (index >= m.playlist.size() || index < 0) {
                                System.out.println("Invalid Song Index.");
                            } else {
                                m.playlist.get(index).playSong();
                            }
                        }
                        else if (playSelect.equalsIgnoreCase("2")) {
                            m.playlist.get(0).playSong();
                        }
                        else if (playSelect.equalsIgnoreCase("3")) {
                            m.playlist.get(((int) (Math.random() * (m.playlist.size())))).playSong();
                        } else {
                            System.out.println("\nInvalid Option.\n");
                        }
                    }

                }

                else if (choice.equalsIgnoreCase("3")) {
                    System.out.println();
                    m.printSongList(m.songs);
                    System.out.println("1. Select song\n2. Start list\n3. Shuffle");
                    String playSelect = input.nextLine();
                    if (playSelect.equalsIgnoreCase("1")) {
                        System.out.print("What song do you want to play from the list?(1-" + Song.getNumSongs()
                                + ")\nEnter your choice: ");
                        choice = input.nextLine();
                        int index = Integer.parseInt(choice) - 1;
                        if (index >= Song.getNumSongs() || index < 0) {
                            System.out.println("Invalid Song Index.");
                        } else {
                            m.songs.get(index).playSong();
                        }
                    }
                    else if (playSelect.equalsIgnoreCase("2")) {
                        m.songs.get(0).playSong();
                    }
                    else if (playSelect.equalsIgnoreCase("3")) {
                        m.songs.get(((int) (Math.random() * (m.songs.size())))).playSong();
                    } else {
                        System.out.println("\nInvalid Option.\n");
                    }
                }

                else {
                    System.out.println("\nInvalid Search Option.\n");
                }

            } else if (choice.equals("7")) {
                System.out.print("Enter your new desired playlist name:");
                String newName = input.nextLine();
                m.playlistName.replace(0, m.playlistName.length(), newName);
                System.out.println("Playlist name successfully changed to " + m.playlistName + "\n");
            } else {
                System.out.print("Invalid Option!\n");
            }

        }

    }

    @Override
    public void fillSongList(Scanner input) throws IOException {
        Song s;
        while (input.hasNextLine()) {
            String title = input.nextLine();
            String artist = input.nextLine();
            String genre = input.nextLine();
            String length = input.nextLine();
            int numDownloads = Integer.parseInt(input.nextLine());
            int numStreams = Integer.parseInt(input.nextLine());

            s = new Song(title, artist, numDownloads, genre, length, numStreams);
            songs.add(s);
            if (input.hasNextLine()) {
                input.nextLine();
            }

        }
    }

    @Override
    public void bubbleSort(ArrayList<Song> s) {

        for (int i = 0; i < s.size() - 1; i++) {
            for (int j = 0; j < s.size() - i - 1; j++) {
                if (s.get(j).compareTo(s.get(j + 1)) > 0) {
                    Song temp = s.get(j);
                    s.set(j, s.get(j + 1));
                    s.set(j + 1, temp);
                }
            }
        }

    }

    @Override
    public int binarySearch(ArrayList<Song> s, Song song) {
        int low = 0;
        int high = s.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (s.get(mid).equals(song)) {
                return mid;
            } else if (s.get(mid).compareTo(song) < 0) {
                low = mid + 1;
            } else if (s.get(mid).compareTo(song) > 0) {
                high = mid - 1;
            }

        }
        return -1;
    }

    @Override
    public void printSongList(ArrayList<Song> s) {
        for (int i = 0; i < s.size(); i++) {
            System.out.println(i+1 + ". " + s.get(i).getTitle() + " - " + s.get(i).getArtist());
        }
        System.out.println();
    }

    public void songSearch(Scanner input) {

    }

}
