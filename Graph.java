class Stack{
    Node top;
    Stack(){
        top = null;
    }
    public boolean isEmpty(){
        return top == null;
    }
    public void push(String nama){
        Node node = new Node(nama);
        node.setNext(top);
        top =  node;
    }
    public String pop(){
        if(top == null) return null;
        Node delete = top;
        top = top.getNext();
        return delete.getName();
    }
}

public class Graph {
    private String tabelData[][];
    private Node listName[];
    private int indexData;
    private int jumlahVertex; // ini belum, harusnya tiap nambahin di increment, atau ngga perlu dijadiin atribut sih. Bisa buat method count vertex aja.

    Graph(){
        listName = new Node[10];
        tabelData = new String[10][2];
        indexData = 0;
    }

    private Node getNodeByName(String name){
        int index = getIndex(name);
        return listName[index];
    }

    public void dfs(String startNode){
        Stack stack = new Stack();
        int indexNode = getIndex(startNode);

        if(indexNode == -1) return;

        boolean[] visited = new boolean[listName.length];
        Node current = listName[indexNode];
        String name = current.getName();
        stack.push(name);
        
        while (!stack.isEmpty()) {
            name = stack.pop();
            current = getNodeByName(name);
            indexNode = getIndex(name);
            if(!visited[indexNode]){
                System.out.println(name);
                visited[indexNode] = true;
            }
            Node afterCurrent = current.getNext();
            String nameAfterCurrent;
            while (afterCurrent != null) {
                nameAfterCurrent = afterCurrent.getName();
                indexNode = getIndex(nameAfterCurrent);
                if(!visited[indexNode])
                    stack.push(nameAfterCurrent);
                afterCurrent = afterCurrent.getNext();
            }
        }
    }

    public int getIndex(String name){
        for (int i = 0; i < tabelData.length; i++) {
            if(tabelData[i][0] != null){
                if(tabelData[i][0].equals(name)) return Integer.parseInt(tabelData[i][1]);
            }
        }
        return -1;
    }

    public void resize(){
        if(indexData == tabelData.length){
            String tempTabelData[][] = new String[tabelData.length * 2][tabelData[0].length];
            Node[] tempGraph = new Node[listName.length * 2];
            copyArray(tabelData, tempTabelData);
            copyArray(listName, tempGraph);
            tabelData = tempTabelData;
            listName = tempGraph;
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

    public void showAllVertex() {
        System.out.println("=== Daftar Semua Vertex (Teman) ===");

        for (int i = 0; i < jumlahVertex; i++) { // pake for tapi dari tabelData aja
            if (listName[i] != null) {
                System.out.println((i + 1) + ". " + listName[i].getName());
            }
        }
    }

    public void showAdjacency() {
        System.out.println("=== List Pertemanan ===");

        for (int i = 0; i < jumlahVertex; i++) { // ini juga pakai while aja sih, jangan terpaku sama jumlah vertex.
            System.out.print(listName[i].getName() + " : ");

            for (int j = 0; j < tabelData[i].length; j++) {
                if (tabelData[i][j] != null) {
                    System.out.print(tabelData[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}