package javaHW2.songStorage;

import java.io.*;
import java.util.*;

/*
 *
 * Created by Anton on 01.02.2017.
 *
 */

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static File file = new File("src" + getSeparator() + "javaHW2" + getSeparator() + "songStorage" + getSeparator() + "songs.txt");
    private static String result = "";

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Enter the command: 1 - add song | 2 - sort by singer | 3 - sort by song | 4 - exit");
            switch (reader.readLine()) {
                case "1":
                    addSong();
                    break;
                case "2":
                    sortBySinger();
                    break;
                case "3":
                    sortBySong();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Wrong command!");
            }
        }
    }

    private static String getSeparator() {
        return System.getProperty("file.separator");
    }

    private static void addSong() throws IOException {
        System.out.println("Enter the song's name:");
        addTextToFile();
        System.out.println("Enter the singer's name:");
        addTextToFile();
        addRateToFile();
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(result);
            System.out.println("The song saved successfully!");
            result = "";
        } catch (IOException e) {
            System.err.println("error by opening of stream " + e);
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }

    private static void addTextToFile() throws IOException {
        String res = reader.readLine();
        if (res.isEmpty()) {
            System.out.println("The field is not must be empty! Please, enter the text!");
            addTextToFile();
        } else {
            result += res + "/";
        }
    }

    private static void addRateToFile() throws IOException {
        System.out.println("Enter the rate of the song (1 - 10):");
        try {
            int res = Integer.parseInt(reader.readLine());
            if (res < 1 || res > 10) {
                System.out.println("Wrong rate!");
                addRateToFile();
            } else {
                result += res;
            }
        } catch (NumberFormatException e) {
            System.out.println("You must enter only numbers 1 - 10!");
            addRateToFile();
        }
    }

    private static void readFile(TreeSet<Song> set) {
        BufferedReader br;
        try {
            FileInputStream fis = new FileInputStream("src" + getSeparator() + "javaHW2" + getSeparator() + "songStorage" + getSeparator() + "songs.txt");
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String s;
            while ((s = br.readLine()) != null) {
                String[] arr = s.split("/");
                set.add(new Song(arr[0], arr[1], Integer.parseInt(arr[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sortBySong() {
        System.out.println("Sorting by song:");
        TreeSet<Song> treeSet = new TreeSet<>(new ComparatorBySong());
        readFile(treeSet);
        for (Song s : treeSet) {
            System.out.println(s.getSongName() + "/" + s.getAuthor() + "/" + s.getRate());
        }

    }

    private static void sortBySinger() {
        System.out.println("Sorting by singer:");
        TreeSet<Song> treeSet1 = new TreeSet<>(new ComparatorBySinger());
        readFile(treeSet1);
        for (Song s : treeSet1) {
            System.out.println(s.getAuthor() + "/" + s.getSongName() + "/" + s.getRate());
        }
    }
}

class ComparatorBySong implements Comparator<Song> {

    @Override
    public int compare(Song song1, Song song2) {
        return song1.getSongName().toLowerCase().compareTo(song2.getSongName().toLowerCase());
    }
}

class ComparatorBySinger implements Comparator<Song> {

    @Override
    public int compare(Song song1, Song song2) {
        return song1.getAuthor().toLowerCase().compareTo(song2.getAuthor().toLowerCase());
    }
}
