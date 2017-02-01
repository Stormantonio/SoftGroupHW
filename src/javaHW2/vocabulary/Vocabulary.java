package javaHW2.vocabulary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by Anton on 01.02.2017.
 */

public class Vocabulary {
    private static HashMap<String, String> map = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Vocabulary vocabulary = new Vocabulary();
        while (true) {
            System.out.println("Enter command: 1 - list | 2 - translate | 3 - exit");
            String command = reader.readLine();
            switch (command) {
                case "1":
                    vocabulary.readFile();
                    vocabulary.toConsole();
                    break;
                case "2":
                    vocabulary.readFile();
                    vocabulary.translate();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }
    }

    private void readFile() throws IOException {
        BufferedReader br = null;
        String res;
        try {
            FileInputStream fis = new FileInputStream("E://JavaProgramming//SoftGroup//SGroup//src//javaHW2//vocabulary//abc.txt");
            br = new BufferedReader(new InputStreamReader(fis, "CP1251"));
            while ((res = br.readLine()) != null) {
                String[] strings = res.split("\\s");
                map.put(strings[0], strings[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void toConsole() {
        for (Map.Entry<String, String> m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }

    private void translate() throws IOException {
        System.out.println("Enter the word:");
        String word = reader.readLine();
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> me : set) {
            if (word.equals(me.getKey())) {
                System.out.println(me.getValue());
                return;
            }
        }
        System.out.println("There is no thi word in the vocabulary!");
    }
}
