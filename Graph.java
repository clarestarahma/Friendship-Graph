import java.util.*;

class Stack {
    Node top;

    Stack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(String nama) {
        Node node = new Node(nama);
        node.setNext(top);
        top = node;
    }

    public String pop() {
        if (top == null) return null;
        Node delete = top;
        top = top.getNext();
        return delete.getName();
    }
}

public class Graph {
    private String dataName;
    private ArrayList<Node>[] adjList;
    private int indexNode;
    private static final int MAX_NODES = 30;

    public Graph(){
        adjList = new ArrayList[MAX_NODES];
        indexNode = 0;
        dataName = "";

        for (int i = 0; i < MAX_NODES; i++) {
            adjList[i] = new ArrayList<Node>();
        }
    }

    // Mendapatkan index dari node berdasarkan nama mereka
    public int getIndex(String name) {
        for (int i = 0; i < indexNode; i++) {
            if (adjList[i].size() > 0 && adjList[i].getFirst().getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    /* 
        Cara penggunaan getIndex:
        Graph graph = new Graph();
        graph.addNode("Alice");
        graph.addNode("Bob");
        int indexAlice = graph.getIndex("Alice"); // Mengembalikan 0
        int indexBob = graph.getIndex("Bob");     // Mengembalikan 1
        int indexCharlie = graph.getIndex("Charlie"); // Mengembalikan -1 karena Charlie belum ada
    */

    // Menambahkan Node baru ke graph
    public void addNode(String name) {
        if (getIndex(name) == -1 && indexNode < MAX_NODES) {
            Node newNode = new Node(name);
            adjList[indexNode].add(newNode);
            indexNode++;
        }
    }
}
