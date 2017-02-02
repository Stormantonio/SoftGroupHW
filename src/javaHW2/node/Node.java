package javaHW2.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anton on 02.02.2017.
 */
public class Node {
    Node next;
    int elem;

    Node(int elem) {
        this.elem = elem;
    }

    void display() {
        System.out.print(elem + " ");
    }

    public static void main(String[] args) throws IOException {
        LinkList linkList = new LinkList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sNumb;
        System.out.println("Start enter integers! Press the Space bar to stop the cycle and press Enter");
        while (true) {
            sNumb = reader.readLine();
            if (sNumb.trim().length() != sNumb.length()) {
                linkList.insertFirstLink(Integer.parseInt(sNumb.trim()));
                linkList.display();
                return;
            } else {
                linkList.insertFirstLink(Integer.parseInt(sNumb));
            }
        }
    }
}

class LinkList {
    private Node firstNode;

    LinkList() {
        firstNode = null;
    }

    void insertFirstLink(int elem) {
        Node newNode = new Node(elem);
        newNode.next = firstNode;
        firstNode = newNode;
    }

    void display() {
        Node thisNode = firstNode;
        System.out.print("Nods: ");
        while (thisNode != null) {
            thisNode.display();
            thisNode = thisNode.next;
        }
    }
}
