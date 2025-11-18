import java.util.*;

public class Graph {
    private Node[] daftarNama;
    private String[][] tabelNama;
    private int indexData;
    
    public Graph() {
        daftarNama = new Node[10];
        tabelNama = new String[10][2];
        indexData = 0;
    }
    
    public int getIndex(String name){
        for (int i = 0; i < tabelNama.length; i++) {
            if(tabelNama[i][0] != null){
                if(tabelNama[i][0].equals(name)) return Integer.parseInt(tabelNama[i][1]);
            }
        }
        return -1;
    }
    
    public void addNama(String name) {
        resize();
        int index = getIndex(name);
        if (index != -1) {
            System.out.println("Node '" + name + "' sudah ada.");
            return;
        }
        
        for (int i = 0; i < tabelNama.length; i++) {
            if (tabelNama[i][0] == null) {
                tabelNama[i][0] = name;
                tabelNama[i][1] = Integer.toString(indexData);
                indexData++;
                System.out.println("Node '" + name + "' berhasil ditambahkan.");
                return;
            }
        }
    }

    void addEdge(String name1, String name2){
        int indexName1 = getIndex(name1);
        int indexName2 = getIndex(name2);

        if(indexName1 == -1 || indexName2 == -1){
            System.out.println("Salah satu atau kedua node tidak ditemukan.");
            return;
        }

        Node newNodeName1 = new Node(name1);
        Node newNodeName2 = new Node(name2);

        newNodeName2.setNext(daftarNama[indexName1]);
        daftarNama[indexName1] = newNodeName2;

        newNodeName1.setNext(daftarNama[indexName2]);
        daftarNama[indexName2] = newNodeName1;
        System.out.println("Edge antara '" + name1 + "' dan '" + name2 + "' berhasil ditambahkan.");
    }

    public void resize(){
        if(indexData == tabelNama.length){
            String temptabelNama[][] = new String[tabelNama.length * 2][tabelNama[0].length];
            Node[] tempGraph = new Node[daftarNama.length * 2];
            copyArray(tabelNama, temptabelNama);
            copyArray(daftarNama, tempGraph);
            tabelNama = temptabelNama;
            daftarNama = tempGraph;
        }
    }

    private void copyArray(String[][] source, String[][] destination){
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                destination[i][j] = source[i][j];
            }
        }
    }

    private void copyArray(Node[] source, Node[] destination){
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }
    
    public void removeEdge(String name1, String name2) {
        int index1 = getIndex(name1);
        int index2 = getIndex(name2);
        
        if (index1 == -1 || index2 == -1) {
            System.out.println("Salah satu atau kedua node tidak ditemukan.");
            return;
        }
        
        removeEdge(index1, name2);
        removeEdge(index2, name1);
        
        System.out.println("Edge antara '" + name1 + "' dan '" + name2 + "' berhasil dihapus.");
    }

    private void removeEdge(int indexNode, String adjacency){
        Node current = daftarNama[indexNode];
        Node prev = null, delete = null;
        if(current.getName().equals(adjacency)){
            daftarNama[indexNode] = current.getNext();
        }else{
            while (current != null) {
                if(current.getName().equals(adjacency)){
                    if(prev == null)
                        daftarNama[indexNode] = current.getNext();
                    else
                        prev.setNext(current.getNext());
                    return;
                }
                prev = current;
                current = current.getNext();
            }
        }
    }
    
    public void bfs(String startName) {
        int startIndex = getIndex(startName);
        if (startIndex == -1) {
            System.out.println("Vertex tidak ditemukan!");
            return;
        }

        boolean[] visited = new boolean[tabelNama.length]; 
        Queue queue = new Queue();

        queue.enqueue(startName);
        visited[startIndex] = true;

        System.out.println("\n=== BFS Traversal dari " + startName + " ===");
        System.out.println("Urutan kunjungan:");

        while (!queue.isEmpty()) {
            String currentName = queue.dequeue();
            int currentIndex = getIndex(currentName);

            if (currentIndex == -1) {
                continue;
            }

            System.out.println(currentName);

            Node neighbor = daftarNama[currentIndex];
            while (neighbor != null) {
                String neighborName = neighbor.getName();
                int neighborIndex = getIndex(neighborName);

                if (neighborIndex != -1 && !visited[neighborIndex]) {
                    visited[neighborIndex] = true;
                    queue.enqueue(neighborName);
                }

                neighbor = neighbor.getNext();
            }
        }

        System.out.println("END");
}


    
    public void dfs(String startName){
        Stack stack = new Stack();
        boolean[] visited = new boolean[daftarNama.length];

        int indexVertex = getIndex(startName);
        String name = startName;

        stack.push(name);

        while (!stack.isEmpty()) {
            name = stack.pop();
            indexVertex = getIndex(name);
            System.out.println(name);
            visited[indexVertex] = true;

            Node neighbor = daftarNama[indexVertex];
            while (neighbor != null) {
                name = neighbor.getName();
                int indexNeighbor = getIndex(name);
                if(!visited[indexNeighbor] && indexNeighbor != -1){
                    stack.push(name);
                    visited[indexNeighbor] = true;
                }
                neighbor = neighbor.getNext();
            }
        }
    }
    
    public void showAllVertex() {
        System.out.println("\n=== Daftar Semua Vertex ===");
        int count = 1;
        for (int i = 0; i < tabelNama.length; i++) {
            if (tabelNama[i][0] != null) {
                System.out.println(count + ". " + tabelNama[i][0]);
                count++;
            }else{
                break;
            }
        }
    }
    
    public void showAdjacencyList() {
        System.out.println("\n=== Adjacency List ===");

        for (int i = 0; i < daftarNama.length; i++) {

            if (daftarNama[i] != null) {
                System.out.print(tabelNama[i][0] + " : ");

                Node current = daftarNama[i];
                while(current != null){
                    System.out.print(current.getName());

                    if(current.getNext() != null) {
                        System.out.print(", ");
                    }
                    current = current.getNext();
                }

                System.out.println();
            }
        }
    }
}