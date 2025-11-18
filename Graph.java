import java.util.*;

public class Graph {
    private Node[] daftarNama;
    private String[][] tabelNama;
    
    public Graph() {
        daftarNama = new Node[10];
        tabelNama = new String[10][10];
    }
    
    public int getIndex(String name) {
        for (int i = 0; i < daftarNama.length; i++) {
            if (daftarNama[i] != null && daftarNama[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
    
    public void addNama(String name) {
        int index = getIndex(name);
        if (index != -1) {
            System.out.println("Node '" + name + "' sudah ada.");
            return;
        }
        
        for (int i = 0; i < daftarNama.length; i++) {
            if (daftarNama[i] == null) {
                daftarNama[i] = new Node(name);
                System.out.println("Node '" + name + "' berhasil ditambahkan.");
                return;
            }
        }
        
        System.out.println("Graph penuh!");
    }
    
    public void addEdge(String name1, String name2) {
        int index1 = getIndex(name1);
        int index2 = getIndex(name2);
        
        if (index1 == -1 || index2 == -1) {
            System.out.println("Salah satu atau kedua node tidak ditemukan.");
            return;
        }
        
        for (int i = 0; i < tabelNama[index1].length; i++) {
            if (tabelNama[index1][i] == null) {
                tabelNama[index1][i] = name2;
                break;
            }
        }
        
        for (int i = 0; i < tabelNama[index2].length; i++) {
            if (tabelNama[index2][i] == null) {
                tabelNama[index2][i] = name1;
                break;
            }
        }
        
        System.out.println("Edge antara '" + name1 + "' dan '" + name2 + "' berhasil ditambahkan.");
    }
    
    public void addAdjacency(String name, String adjacentName) {
        int index = getIndex(name);
        if (index == -1) {
            System.out.println("Node '" + name + "' tidak ditemukan.");
            return;
        }
        
        for (int i = 0; i < tabelNama[index].length; i++) {
            if (tabelNama[index][i] == null) {
                tabelNama[index][i] = adjacentName;
                System.out.println("Adjacency '" + adjacentName + "' ditambahkan ke '" + name + "'.");
                return;
            }
        }
        
        System.out.println("Adjacency list penuh untuk '" + name + "'.");
    }
    
    public void removeEdge(String name1, String name2) {
        int index1 = getIndex(name1);
        int index2 = getIndex(name2);
        
        if (index1 == -1 || index2 == -1) {
            System.out.println("Salah satu atau kedua node tidak ditemukan.");
            return;
        }
        
        for (int i = 0; i < tabelNama[index1].length; i++) {
            if (tabelNama[index1][i] != null && tabelNama[index1][i].equalsIgnoreCase(name2)) {
                tabelNama[index1][i] = null;
                for (int j = i; j < tabelNama[index1].length - 1; j++) {
                    tabelNama[index1][j] = tabelNama[index1][j + 1];
                }
                tabelNama[index1][tabelNama[index1].length - 1] = null;
                break;
            }
        }
        
        for (int i = 0; i < tabelNama[index2].length; i++) {
            if (tabelNama[index2][i] != null && tabelNama[index2][i].equalsIgnoreCase(name1)) {
                tabelNama[index2][i] = null;
                for (int j = i; j < tabelNama[index2].length - 1; j++) {
                    tabelNama[index2][j] = tabelNama[index2][j + 1];
                }
                tabelNama[index2][tabelNama[index2].length - 1] = null;
                break;
            }
        }
        
        System.out.println("Edge antara '" + name1 + "' dan '" + name2 + "' berhasil dihapus.");
    }
    
    public void bfs(String startName) {
        int startIndex = getIndex(startName);
        if (startIndex == -1) {
            System.out.println("Node tidak ditemukan!");
            return;
        }
        
        boolean[] visited = new boolean[daftarNama.length];
        Queue queue = new Queue();
        
        queue.enqueue(startIndex);
        visited[startIndex] = true;
        
        System.out.println("\n=== BFS Traversal dari " + startName + " ===");
        System.out.print("Urutan kunjungan: ");
        
        while (!queue.isEmpty()) {
            int currentIndex = queue.dequeue();
            System.out.print(daftarNama[currentIndex].getName() + " -> ");
            
           
            for (int i = 0; i < tabelNama[currentIndex].length; i++) {
                if (tabelNama[currentIndex][i] != null) {
                    int neighborIndex = getIndex(tabelNama[currentIndex][i]);
                    if (neighborIndex != -1 && !visited[neighborIndex]) {
                        visited[neighborIndex] = true;
                        queue.enqueue(neighborIndex);
                    }
                }
            }
        }
        System.out.println("END");
    }
    
    public void dfs(String startName) {
        int startIndex = getIndex(startName);
        if (startIndex == -1) {
            System.out.println("Node tidak ditemukan!");
            return;
        }
        
        boolean[] visited = new boolean[daftarNama.length];
        System.out.println("\n=== DFS Traversal dari " + startName + " ===");
        System.out.print("Urutan kunjungan: ");
        dfsHelper(startIndex, visited);
        System.out.println("END");
    }
    
    private void dfsHelper(int nodeIndex, boolean[] visited) {
        visited[nodeIndex] = true;
        System.out.print(daftarNama[nodeIndex].getName() + " -> ");
        
        for (int i = 0; i < tabelNama[nodeIndex].length; i++) {
            if (tabelNama[nodeIndex][i] != null) {
                int neighborIndex = getIndex(tabelNama[nodeIndex][i]);
                if (neighborIndex != -1 && !visited[neighborIndex]) {
                    dfsHelper(neighborIndex, visited);
                }
            }
        }
    }
    
    public void showAllVertex() {
        System.out.println("\n=== Daftar Semua Vertex ===");
        int count = 1;
        for (int i = 0; i < daftarNama.length; i++) {
            if (daftarNama[i] != null) {
                System.out.println(count + ". " + daftarNama[i].getName());
                count++;
            }
        }
    }
    
    public void showAdjacencyList() {
        System.out.println("\n=== Adjacency List ===");

        for (int i = 0; i < daftarNama.length; i++) {

            if (daftarNama[i] != null) {
                System.out.print(daftarNama[i].getName() + " : ");
                
                for (int j = 0; j < tabelNama[i].length; j++) {
                    if (tabelNama[i][j] != null) {
                        System.out.print(tabelNama[i][j]);
                        if (j < tabelNama[i].length - 1 && tabelNama[i][j + 1] != null) {
                            System.out.print(", ");
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}