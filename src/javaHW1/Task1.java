package javaHW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anton on 21.01.2017.
 * Задача будильник: пользователь вводит число от 0 до 24, если число в диапазоне от 8 до 21 - вывести на экран сообщение
 * “Привет”, если любое другое, то “Абонент отдыхает”.
 */
public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        if (num < 0 || num > 24) {
            System.out.println("Нет такого времени!");
        } else if (num > 7 && num < 22) {
            System.out.println("Привет!");
        } else {
            System.out.println("Абонент отдыхает!");
        }
    }
}
