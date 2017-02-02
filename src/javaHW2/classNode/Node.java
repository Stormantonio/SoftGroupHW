
package javaHW2.classNode;

import javax.swing.plaf.PanelUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Asus on 02.02.2017.
 */

public class Node {
    public Node next;
    public int elem;

    public Node() {
        this(0);
    }

    public Node(int elem) {
        this.elem = elem;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getElem() {
        return elem;
    }

    public void setElem(int elem) {
        this.elem = elem;
    }
}

class Main {
    private static Node top;
    private static int size;

    public Main() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static void push1(int elem) {
        Node node = new Node();
        node.setElem(elem);
        node.setNext(top);
        top = node;
        size++;
    }

    public static LinkedList<Node> linkedList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
       /* while (true) {
            System.out.println("Enter command: 1 - add numeric value | 2 - print values in reverse order | 3 - exit|");
            switch (reader.readLine()) {
                case "1":
                    addToList();
                    break;
                case "2":
                    print(new Node());
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Wrong command!");
            }
        }*/
    }

    public static void print(Node node) {
        Queue<Node> queue = linkedList;
        if (queue.isEmpty()) {
            System.out.println("Queue is empty!");
        }
        while ((node = queue.poll()) != null) {
            System.out.println(node.getElem());
        }
    }

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void addToList() throws IOException {
        System.out.println("Enter the numeric value:");
        try {
            int number = Integer.parseInt(reader.readLine());
            linkedList.push(new Node(number));
        } catch (NumberFormatException e) {
            System.out.println("You must enter only numeric values!");
            addToList();
        }
    }
}
