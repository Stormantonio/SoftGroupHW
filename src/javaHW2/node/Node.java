package javaHW2.node;

/**
 * Created by Asus on 02.02.2017.
 */
public class Node {
    Node next;
    int elem;
}

class Main {
    private Node head;
    private Node tail;

    public void addFront(int elem) {
        Node node = new Node();
        node.elem = elem;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void printList(Node node) {
        while (node != null) {
            System.out.println(node.elem);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Node node = new Node();
        Main m = new Main();
        m.addFront(1);
        m.addFront(7);
        m.addFront(8);
        m.addFront(10);
        m.addFront(5);
        m.addFront(4);

        m.printList(node);
    }
}
