package javaHW2.node2;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Asus on 02.02.2017.
 */
public class Node {
    private Integer elem;
    private Node next;

    public int getElem() {
        return elem;
    }

    public void setElem(int e) {
        elem = e;
        // System.out.println("elem is " + elem);
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        next = node;
//        System.out.println("next is " + next);
    }

    /*Node() {
        elem = -999;
        next = null;
    }*/

    Node(int e, Node n) {
        elem = e;
        next = n;
        System.out.println("elem is " + elem + " next is: " + next);
    }
}

class QueueProject {
    public static void main(String[] args) {
        QueueClass queueClass = new QueueClass();
        LinkedList<Integer> linkedList = new LinkedList<>();
        System.out.println("Type integers to fill queue");
        Scanner scanner = new Scanner(System.in);
        int value;
        value = scanner.nextInt();
        while (value != 0) {
            queueClass.enqueue(value);
            linkedList.add(value);
            System.out.println("Value enqueued: " + value);
            value = scanner.nextInt();
        }
        System.out.println("Finished enqueueing... \n Dequeuing");
        while (!queueClass.isEmpty()) {
            System.out.printf("%6d", queueClass.dequeue());
        }
        System.out.println("\nGood bye");
    }
}

class QueueClass {
    Node head;
    Node tail;

    QueueClass() {
        head = null;
        tail = null;
    }

    public void enqueue(int value) {
        Node temp = new Node(value, null);
        if (isEmpty()) {
            head = temp;
            tail = temp;
//            System.out.println("head is: " + head + "tail is: " + tail);
        } else {
            tail.setNext(temp);
            tail = temp;
//            System.out.println("tail is: " + tail);
        }
    }

    /*public int dequeue() {
        int temp;
        temp = head.getElem();
        head = head.getNext();
//        System.out.println("temp is: " + temp + " head is: " + head);
        return temp;
    }*/

    public int dequeue() {
        int temp;
        temp = tail.getElem();
        head = tail.getNext();
//        System.out.println("temp is: " + temp + " head is: " + head);
        return temp;
    }

    public boolean isEmpty() {
        return (head == null);
    }
}
