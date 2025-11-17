public class Graph {

    private int jumlahVertex;
    private Node[] daftarNama;
    private String[][] tabelNama;

    public Graph(int jumlahVertex) {
        this.jumlahVertex = jumlahVertex;
        daftarNama = new Node[jumlahVertex];
        tabelNama = new String[jumlahVertex][jumlahVertex];
    }

    public void setVertex(int index, String name) {
        daftarNama[index] = new Node(name);
    }

    public void showAllVertex() {
        System.out.println("=== Daftar Semua Vertex (Teman) ===");

        for (int i = 0; i < jumlahVertex; i++) {
            if (daftarNama[i] != null) {
                System.out.println((i + 1) + ". " + daftarNama[i].getName());
            }
        }
    }

    public void showAdjacency() {
        System.out.println("=== List Pertemanan ===");

        for (int i = 0; i < jumlahVertex; i++) {
            System.out.print(daftarNama[i].getName() + " : ");

            for (int j = 0; j < tabelNama[i].length; j++) {
                if (tabelNama[i][j] != null) {
                    System.out.print(tabelNama[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
