public class Tes {
    public static void main(String[] args) {
        // buat objek graph
        Graph graph = new Graph();

        // 1. Tambah beberapa vertex
        graph.addNama("Ayu");
        graph.addNama("Budi");
        graph.addNama("Citra");
        graph.addNama("Doni");
        graph.addNama("Eka");
        graph.addNama("Fina");

        // 2. Tambah edge (hubungan pertemanan)
        // Graph: Ayu-Budi, Ayu-Citra, Budi-Doni, Citra-Eka, Doni-Eka, Eka-Fina
        graph.addEdge("Ayu", "Budi");
        graph.addEdge("Ayu", "Citra");
        graph.addEdge("Budi", "Doni");
        graph.addEdge("Citra", "Eka");
        graph.addEdge("Doni", "Eka");
        graph.addEdge("Eka", "Fina");

        // 3. Tampilkan semua vertex
        graph.showAllVertex();

        // 4. Tampilkan adjacency list (cek apakah linked list tetangga sudah benar)
        graph.showAdjacencyList();

        // 5. Uji BFS dari beberapa titik
        System.out.println();
        graph.bfs("Ayu");

        System.out.println();
        graph.bfs("Citra");

        // 6. Uji DFS dari beberapa titik
        System.out.println();
        System.out.println("=== DFS Traversal dari Ayu ===");
        graph.dfs("Ayu");

        System.out.println();
        System.out.println("=== DFS Traversal dari Citra ===");
        graph.dfs("Citra");

        // 7. Uji removeEdge
        System.out.println();
        System.out.println("=== Hapus edge Ayu - Budi ===");
        graph.removeEdge("Ayu", "Budi");

        // 8. Tampilkan adjacency list lagi setelah penghapusan
        graph.showAdjacencyList();

        // 9. Uji BFS lagi setelah edge dihapus
        System.out.println();
        graph.bfs("Ayu");
    }
}
