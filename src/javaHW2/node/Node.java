package javaHW2.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anton on 02.02.2017.
 */
public class Node {
    public Node next;
    public int elem;

    public Node(int elem) {
        this.elem = elem;
    }

    public void display() {
        System.out.print(elem + " ");
    }

    public static int isTrue(String s) {
        return s.trim().length();
    }

    public static void main(String[] args) throws IOException {
        LinkList linkList = new LinkList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numb;
        String sNumb;
        System.out.println("Start enter integers! Press the space bar to stop the cycle");
        while (true) {
            sNumb = reader.readLine();
            if (isTrue(sNumb) != sNumb.length()) {
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
    public Node firstNode;

    LinkList() {
        firstNode = null;
    }

    public void insertFirstLink(int elem) {
        Node newNode = new Node(elem);
        newNode.next = firstNode;
        firstNode = newNode;
    }

    public void display() {
        Node thisNode = firstNode;
        System.out.print("Nods: ");
        while (thisNode != null) {
            thisNode.display();
            thisNode = thisNode.next;
        }
    }
}
