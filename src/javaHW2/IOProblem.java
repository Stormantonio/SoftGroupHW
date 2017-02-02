package javaHW2;

import java.io.*;

/**
 * Created by Anton on 28.01.2017.
 * 1.Пользователь вводит с консоли произвольный текст, мы должны этот текст записать в файл и потом прочитать из файла.
 * Использовать буферизацию.
 */
public class IOProblem {
    public static void main(String[] args) throws IOException {
        IOProblem problem = new IOProblem();
        problem.saveInFile();
        problem.readFile();
        problem.getSeparator();
    }

    private String getSeparator() {
        return System.getProperty("file.separator");
    }

    private void saveInFile() throws IOException {
        File file = new File("src" + getSeparator() + "javaHW2" + getSeparator() + "info");
        if (file.exists()) {
            file.delete();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter some text please:");
        String result = reader.readLine();
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.print(result);
            System.out.println("Information was successfully saved in the file!");
        } catch (IOException e) {
            System.err.println("ошибка открытия потока " + e);
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }

    private void readFile() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src//javaHW2//info.txt"));
            String result = br.readLine();
            System.out.println("Output of content to the console:" + "\n" + result);
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
}
