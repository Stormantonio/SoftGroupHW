package javaHW2.namesOfSongs;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Anton on 01.02.2017.
 * В текстовом файле хранятся названия песен, каждая песня с новой строки.
 * Нужно вывести в консоль отсортированные по алфавиту все песни из файла.
 */

public class NameOfSongs {
    public static void main(String[] args) {
        readFile();
    }

    private static void readFile() {
        LinkedList<String> list = new LinkedList<>();
        BufferedReader br;
        try {
            FileInputStream fis = new FileInputStream("src" + getSeparator() + "javaHW2" + getSeparator() + "namesOfSongs" + getSeparator() + "song");
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String s;
            while ((s = br.readLine()) != null)
                list.add(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(list, comparator);
        for (String text : list) {
            System.out.println(text);
        }
    }

    private static String getSeparator() {
        return System.getProperty("file.separator");
    }

    private static final Comparator<String> comparator = new Comparator<String>() {
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };
}
