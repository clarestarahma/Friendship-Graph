import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();
        
        System.out.println("==============================================");
        System.out.println("   SISTEM ANALISIS JARINGAN PERTEMANAN UKM");
        System.out.println("==============================================");

        while (true) {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Tambah Node");
            System.out.println("2. Tambah Edge");
            System.out.println("3. Hapus Edge");
            System.out.println("4. Tampilkan Semua Vertex");
            System.out.println("5. Tampilkan Adjacency List");
            System.out.println("6. BFS Traversal");
            System.out.println("7. DFS Traversal");
            System.out.println("0. Keluar");
            System.out.println("================================");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama node: ");
                    String nama = scanner.nextLine();
                    graph.addNama(nama);
                    break;

                case 2:
                    System.out.print("Masukkan nama node pertama: ");
                    String nama1 = scanner.nextLine();
                    System.out.print("Masukkan nama node kedua: ");
                    String nama2 = scanner.nextLine();
                    graph.addEdge(nama1, nama2);
                    break;

                case 3:
                    System.out.print("Masukkan nama node pertama: ");
                    String hapus1 = scanner.nextLine();
                    System.out.print("Masukkan nama node kedua: ");
                    String hapus2 = scanner.nextLine();
                    graph.removeEdge(hapus1, hapus2);
                    break;

                case 4:
                    graph.showAllVertex();
                    break;

                case 5:
                    graph.showAdjacencyList();
                    break;

                case 6:
                    System.out.print("Masukkan nama node awal untuk BFS: ");
                    String bfsStart = scanner.nextLine();
                    graph.bfs(bfsStart);
                    break;

                case 7:
                    System.out.print("Masukkan nama node awal untuk DFS: ");
                    String dfsStart = scanner.nextLine();
                    graph.dfs(dfsStart);
                    break;

                case 0:
                    System.out.println("\nTerima kasih!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}