# Penjelasan Flow Cara Kerja Method di Class Graph

## 📋 Deskripsi Umum
Class `Graph` ini mengimplementasikan struktur data graf menggunakan **Adjacency List** dengan array. Graf ini dapat menyimpan maksimal 10 node dan setiap node dapat memiliki maksimal 10 tetangga.

### Struktur Data:
- `daftarNama[]`: Array untuk menyimpan objek Node (maksimal 10 node)
- `tabelNama[][]`: Matriks 2D untuk menyimpan adjacency list (10x10)

---

## 🔧 Method-Method

### 1. Constructor `Graph()`

**Fungsi**: Menginisialisasi graf kosong dengan kapasitas maksimal 10 node.

**Cara Kerja**:
1. Membuat array `daftarNama` berukuran 10
2. Membuat matriks `tabelNama` berukuran 10x10

**Flow**:
```
START
  ↓
daftarNama = new Node[10]
  ↓
tabelNama = new String[10][10]
  ↓
END
```

**Contoh Penggunaan**:
```java
Graph graph = new Graph();
// Graf kosong telah dibuat dengan kapasitas 10 node
```

---

### 2. Method `getIndex(String name)`

**Fungsi**: Mencari index/posisi node berdasarkan nama.

**Cara Kerja**:
1. Loop melalui array `daftarNama` dari index 0 hingga 9
2. Cek apakah node tidak null DAN namanya cocok (case-insensitive)
3. Jika ditemukan, return index-nya
4. Jika tidak ditemukan setelah loop selesai, return -1

**Flow**:
```
START
  ↓
Loop i = 0 to 9
  ↓
daftarNama[i] != null AND nama cocok?
  ↓ YA              ↓ TIDAK
Return i        Lanjut loop
                     ↓
                Loop selesai?
                     ↓ YA
                  Return -1
```

**Contoh Penggunaan**:
```java
graph.addNama("Alice");
graph.addNama("Bob");

int index1 = graph.getIndex("Alice");   // Returns: 0
int index2 = graph.getIndex("Bob");     // Returns: 1
int index3 = graph.getIndex("Charlie"); // Returns: -1 (tidak ditemukan)
```

---

### 3. Method `addNama(String name)`

**Fungsi**: Menambahkan node baru ke dalam graf.

**Cara Kerja**:
1. Cek apakah nama sudah ada menggunakan `getIndex()`
2. Jika sudah ada (index != -1), tampilkan pesan error dan keluar
3. Jika belum ada, cari slot kosong (null) di array `daftarNama`
4. Tambahkan node baru pada slot pertama yang ditemukan
5. Jika semua slot penuh, tampilkan pesan "Graph penuh!"

**Flow**:
```
START
  ↓
index = getIndex(name)
  ↓
index != -1? (sudah ada?)
  ↓ YA                    ↓ TIDAK
Print "sudah ada"     Loop cari slot kosong
Return                     ↓
                      Ada slot kosong?
                      ↓ YA           ↓ TIDAK
              Tambah node baru    Print "Graph penuh"
              Print "berhasil"
                      ↓
                    Return
```

**Contoh Penggunaan**:
```java
graph.addNama("Alice");
// Output: Node 'Alice' berhasil ditambahkan.

graph.addNama("Bob");
// Output: Node 'Bob' berhasil ditambahkan.

graph.addNama("Alice");
// Output: Node 'Alice' sudah ada.
```

**Visualisasi Graf**:
```
Setelah menambah Alice dan Bob:

daftarNama[0] = Alice
daftarNama[1] = Bob
daftarNama[2] = null
...
daftarNama[9] = null
```

---

### 4. Method `addEdge(String name1, String name2)`

**Fungsi**: Menambahkan edge (hubungan) bidirectional antara dua node.

**Cara Kerja**:
1. Dapatkan index kedua node menggunakan `getIndex()`
2. Validasi: pastikan kedua node ada (index1 != -1 DAN index2 != -1)
3. Jika salah satu atau kedua tidak ada, tampilkan error dan keluar
4. Tambahkan `name2` ke adjacency list `name1` (cari slot kosong di tabelNama[index1])
5. Tambahkan `name1` ke adjacency list `name2` (cari slot kosong di tabelNama[index2])
6. Tampilkan pesan sukses

**Flow**:
```
START
  ↓
index1 = getIndex(name1)
index2 = getIndex(name2)
  ↓
index1 == -1 OR index2 == -1?
  ↓ YA                        ↓ TIDAK
Print error              Loop cari slot kosong di tabelNama[index1]
Return                          ↓
                          Tambah name2 ke slot kosong
                                ↓
                          Loop cari slot kosong di tabelNama[index2]
                                ↓
                          Tambah name1 ke slot kosong
                                ↓
                          Print "berhasil ditambahkan"
                                ↓
                              END
```

**Contoh Penggunaan**:
```java
graph.addNama("Alice");
graph.addNama("Bob");
graph.addNama("Charlie");

graph.addEdge("Alice", "Bob");
// Output: Edge antara 'Alice' dan 'Bob' berhasil ditambahkan.

graph.addEdge("Alice", "Charlie");
// Output: Edge antara 'Alice' dan 'Charlie' berhasil ditambahkan.
```

**Visualisasi Graf**:
```
     Bob
      |
    Alice --- Charlie

Adjacency List:
Alice   : Bob, Charlie
Bob     : Alice
Charlie : Alice
```

**Struktur Data Internal**:
```
tabelNama[0] = ["Bob", "Charlie", null, ...]  // Alice's neighbors
tabelNama[1] = ["Alice", null, ...]           // Bob's neighbors
tabelNama[2] = ["Alice", null, ...]           // Charlie's neighbors
```

---

### 5. Method `addAdjacency(String name, String adjacentName)`

**Fungsi**: Menambahkan adjacency satu arah (directed edge) dari `name` ke `adjacentName`.

**Cara Kerja**:
1. Dapatkan index node `name` menggunakan `getIndex()`
2. Jika node tidak ditemukan (index == -1), tampilkan error dan keluar
3. Cari slot kosong di adjacency list node tersebut (tabelNama[index])
4. Tambahkan `adjacentName` ke slot kosong pertama yang ditemukan
5. Jika semua slot penuh, tampilkan pesan "Adjacency list penuh"

**Flow**:
```
START
  ↓
index = getIndex(name)
  ↓
index == -1?
  ↓ YA                  ↓ TIDAK
Print "tidak        Loop cari slot kosong
ditemukan"          di tabelNama[index]
Return                   ↓
                    Ada slot kosong?
                    ↓ YA              ↓ TIDAK
            Tambah adjacentName   Print "list penuh"
            Print "ditambahkan"   Return
            Return
```

**Contoh Penggunaan**:
```java
graph.addNama("Alice");
graph.addNama("Bob");

graph.addAdjacency("Alice", "Bob");
// Output: Adjacency 'Bob' ditambahkan ke 'Alice'.

// Note: Ini hanya menambah satu arah!
```

**Visualisasi Graf**:
```
Alice → Bob  (satu arah saja)

Adjacency List:
Alice : Bob
Bob   : (kosong)
```

**Perbedaan dengan `addEdge()`**:
- `addEdge()`: Membuat hubungan DUA ARAH (A → B dan B → A)
- `addAdjacency()`: Membuat hubungan SATU ARAH (A → B saja)

---

### 6. Method `removeEdge(String name1, String name2)`

**Fungsi**: Menghapus edge bidirectional antara dua node.

**Cara Kerja**:
1. Dapatkan index kedua node
2. Validasi kedua node ada
3. Hapus `name2` dari adjacency list `name1`:
   - Loop cari `name2` di tabelNama[index1]
   - Jika ditemukan, set menjadi null
   - Shift semua elemen setelahnya ke kiri
   - Set elemen terakhir menjadi null
4. Ulangi proses yang sama untuk menghapus `name1` dari adjacency list `name2`
5. Tampilkan pesan sukses

**Flow**:
```
START
  ↓
index1 = getIndex(name1)
index2 = getIndex(name2)
  ↓
index1 == -1 OR index2 == -1?
  ↓ YA                        ↓ TIDAK
Print error              Loop cari name2 di tabelNama[index1]
Return                          ↓
                          Ditemukan?
                          ↓ YA
                    Set posisi = null
                    Shift elemen ke kiri
                    Set elemen terakhir = null
                          ↓
                    Loop cari name1 di tabelNama[index2]
                          ↓
                          (sama seperti di atas)
                          ↓
                    Print "berhasil dihapus"
                          ↓
                         END
```

**Contoh Penggunaan**:
```java
graph.addNama("Alice");
graph.addNama("Bob");
graph.addNama("Charlie");
graph.addEdge("Alice", "Bob");
graph.addEdge("Alice", "Charlie");

// Sebelum remove:
// Alice : Bob, Charlie
// Bob   : Alice
// Charlie : Alice

graph.removeEdge("Alice", "Bob");
// Output: Edge antara 'Alice' dan 'Bob' berhasil dihapus.

// Setelah remove:
// Alice : Charlie
// Bob   : (kosong)
// Charlie : Alice
```

**Visualisasi Proses Shifting**:
```
Sebelum: ["Bob", "Charlie", null, null, ...]
                ↓ Hapus "Bob"
         [null, "Charlie", null, null, ...]
                ↓ Shift ke kiri
         ["Charlie", null, null, null, ...]
```

---

### 7. Method `bfs(String startName)` - Breadth-First Search

**Fungsi**: Melakukan traversal graf menggunakan algoritma BFS (penelusuran lebar pertama).

**Cara Kerja**:
1. Dapatkan index node awal
2. Validasi node ada
3. Buat array `visited[]` untuk menandai node yang sudah dikunjungi
4. Buat Queue dan masukkan node awal
5. Tandai node awal sebagai visited
6. Selama queue tidak kosong:
   - Dequeue node dari queue
   - Print nama node
   - Loop semua tetangga node tersebut:
     - Jika tetangga belum dikunjungi, tandai sebagai visited dan enqueue

**Flow**:
```
START
  ↓
startIndex = getIndex(startName)
  ↓
startIndex == -1?
  ↓ YA               ↓ TIDAK
Print error     Inisialisasi visited[]
Return          Inisialisasi queue
                     ↓
                Enqueue(startIndex)
                visited[startIndex] = true
                     ↓
                Queue kosong?
                ↓ TIDAK          ↓ YA
          current = Dequeue   Print "END"
          Print nama current  Return
                ↓
          Loop semua tetangga
                ↓
          Tetangga belum visited?
          ↓ YA              ↓ TIDAK
    visited = true       Skip
    Enqueue tetangga
          ↓
    (kembali ke "Queue kosong?")
```

**Contoh Penggunaan**:
```java
graph.addNama("A");
graph.addNama("B");
graph.addNama("C");
graph.addNama("D");
graph.addNama("E");

graph.addEdge("A", "B");
graph.addEdge("A", "C");
graph.addEdge("B", "D");
graph.addEdge("C", "E");

graph.bfs("A");
```

**Output**:
```
=== BFS Traversal dari A ===
Urutan kunjungan: A -> B -> C -> D -> E -> END
```

**Visualisasi Graf dan Proses BFS**:
```
Graf:
      A
     / \
    B   C
    |   |
    D   E

Proses BFS dari A:
Level 0: [A]           Queue: [A]         Visited: [A]
Level 1: [B, C]        Queue: [B, C]      Visited: [A, B, C]
Level 2: [D, E]        Queue: [D, E]      Visited: [A, B, C, D, E]

Urutan kunjungan: A -> B -> C -> D -> E
```

**Karakteristik BFS**:
- Mengunjungi node berdasarkan **level/jarak** dari node awal
- Menggunakan **Queue** (FIFO - First In First Out)
- Cocok untuk mencari jalur terpendek di graf tidak berbobot

---

### 8. Method `dfs(String startName)` - Depth-First Search

**Fungsi**: Melakukan traversal graf menggunakan algoritma DFS (penelusuran dalam pertama).

**Cara Kerja**:
1. Dapatkan index node awal
2. Validasi node ada
3. Buat array `visited[]` untuk menandai node yang sudah dikunjungi
4. Panggil method rekursif `dfsHelper()` dengan node awal

**Flow Method `dfs()`**:
```
START
  ↓
startIndex = getIndex(startName)
  ↓
startIndex == -1?
  ↓ YA               ↓ TIDAK
Print error     Inisialisasi visited[]
Return          Print header
                     ↓
                Call dfsHelper(startIndex, visited)
                     ↓
                Print "END"
                     ↓
                   END
```

### Method Helper `dfsHelper(int nodeIndex, boolean[] visited)`

**Cara Kerja Rekursif**:
1. Tandai node saat ini sebagai visited
2. Print nama node
3. Loop semua tetangga node saat ini:
   - Jika tetangga belum dikunjungi, panggil `dfsHelper()` secara rekursif untuk tetangga tersebut

**Flow Method `dfsHelper()`**:
```
START dfsHelper(nodeIndex, visited)
  ↓
visited[nodeIndex] = true
Print nama node
  ↓
Loop semua tetangga di tabelNama[nodeIndex]
  ↓
Tetangga belum visited?
  ↓ YA                    ↓ TIDAK
Call dfsHelper(tetangga)   Skip
(REKURSIF)
  ↓
Loop selesai
  ↓
Return (kembali ke pemanggil)
```

**Contoh Penggunaan**:
```java
graph.addNama("A");
graph.addNama("B");
graph.addNama("C");
graph.addNama("D");
graph.addNama("E");

graph.addEdge("A", "B");
graph.addEdge("A", "C");
graph.addEdge("B", "D");
graph.addEdge("C", "E");

graph.dfs("A");
```

**Output**:
```
=== DFS Traversal dari A ===
Urutan kunjungan: A -> B -> D -> C -> E -> END
```

**Visualisasi Graf dan Proses DFS**:
```
Graf:
      A
     / \
    B   C
    |   |
    D   E

Proses DFS dari A (stack call):
1. Visit A → tetangga: [B, C]
2. Visit B (tetangga pertama A) → tetangga: [D]
3. Visit D (tetangga pertama B) → tidak ada tetangga belum visited
4. Return ke B, return ke A
5. Visit C (tetangga kedua A) → tetangga: [E]
6. Visit E (tetangga pertama C) → tidak ada tetangga belum visited
7. Return ke C, return ke A
8. Selesai

Urutan kunjungan: A -> B -> D -> C -> E
```

**Stack Rekursif**:
```
Call Stack:
dfsHelper(A)
  → dfsHelper(B)
      → dfsHelper(D)
      ← return
  ← return
  → dfsHelper(C)
      → dfsHelper(E)
      ← return
  ← return
← return
```

**Karakteristik DFS**:
- Mengunjungi node dengan pergi **sedalam mungkin** terlebih dahulu
- Menggunakan **rekursi** (atau stack secara eksplisit)
- Cocok untuk pencarian path, deteksi siklus, topological sort

---

### 9. Method `showAllVertex()`

**Fungsi**: Menampilkan semua node/vertex yang ada di dalam graf.

**Cara Kerja**:
1. Print header "Daftar Semua Vertex"
2. Loop melalui array `daftarNama`
3. Jika posisi tidak null, print nomor urut dan nama node
4. Increment counter untuk penomoran

**Flow**:
```
START
  ↓
Print header
count = 1
  ↓
Loop i = 0 to 9
  ↓
daftarNama[i] != null?
  ↓ YA                  ↓ TIDAK
Print count + nama      Skip
count++
  ↓
Loop selesai
  ↓
END
```

**Contoh Penggunaan**:
```java
graph.addNama("Alice");
graph.addNama("Bob");
graph.addNama("Charlie");

graph.showAllVertex();
```

**Output**:
```
=== Daftar Semua Vertex ===
1. Alice
2. Bob
3. Charlie
```

---

### 10. Method `showAdjacencyList()`

**Fungsi**: Menampilkan adjacency list lengkap untuk semua node dalam graf.

**Cara Kerja**:
1. Print header "Adjacency List"
2. Loop melalui semua node di `daftarNama`
3. Untuk setiap node yang tidak null:
   - Print nama node diikuti " : "
   - Loop melalui adjacency list node tersebut (tabelNama[i])
   - Print setiap tetangga yang tidak null
   - Tambahkan koma pemisah jika masih ada tetangga berikutnya
   - Print newline setelah selesai satu node

**Flow**:
```
START
  ↓
Print header
  ↓
Loop i = 0 to 9 (loop node)
  ↓
daftarNama[i] != null?
  ↓ YA                      ↓ TIDAK
Print nama + " : "          Skip ke node berikutnya
  ↓
Loop j = 0 to 9 (loop tetangga)
  ↓
tabelNama[i][j] != null?
  ↓ YA                  ↓ TIDAK
Print tabelNama[i][j]   Skip
  ↓
Ada tetangga berikutnya?
  ↓ YA          ↓ TIDAK
Print ", "      Skip
  ↓
Loop tetangga selesai
  ↓
Print newline
  ↓
Loop node selesai
  ↓
END
```

**Contoh Penggunaan**:
```java
graph.addNama("Alice");
graph.addNama("Bob");
graph.addNama("Charlie");
graph.addNama("David");

graph.addEdge("Alice", "Bob");
graph.addEdge("Alice", "Charlie");
graph.addEdge("Bob", "David");
graph.addEdge("Charlie", "David");

graph.showAdjacencyList();
```

**Output**:
```
=== Adjacency List ===
Alice : Bob, Charlie
Bob : Alice, David
Charlie : Alice, David
David : Bob, Charlie
```

**Visualisasi Graf**:
```
     Alice -------- Bob
       |             |
       |             |
    Charlie ------- David

Adjacency List menunjukkan tetangga dari setiap node:
- Alice terhubung dengan: Bob, Charlie
- Bob terhubung dengan: Alice, David
- Charlie terhubung dengan: Alice, David
- David terhubung dengan: Bob, Charlie
```

---

## 📊 Perbandingan BFS vs DFS

| Aspek | BFS | DFS |
|-------|-----|-----|
| **Struktur Data** | Queue (FIFO) | Stack/Rekursi (LIFO) |
| **Strategi** | Lebar pertama | Dalam pertama |
| **Urutan Kunjungan** | Berdasarkan level/jarak | Sedalam mungkin dulu |
| **Kompleksitas Waktu** | O(V + E) | O(V + E) |
| **Kompleksitas Ruang** | O(V) | O(V) |
| **Cocok untuk** | Jalur terpendek, level traversal | Path finding, cycle detection |

---

## 💡 Contoh Penggunaan Lengkap

```java
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        
        // Menambah node
        System.out.println("=== Menambah Node ===");
        graph.addNama("A");
        graph.addNama("B");
        graph.addNama("C");
        graph.addNama("D");
        graph.addNama("E");
        
        // Menambah edge
        System.out.println("\n=== Menambah Edge ===");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        
        // Tampilkan semua vertex
        graph.showAllVertex();
        
        // Tampilkan adjacency list
        graph.showAdjacencyList();
        
        // BFS dari node A
        graph.bfs("A");
        
        // DFS dari node A
        graph.dfs("A");
        
        // Hapus edge
        System.out.println("\n=== Menghapus Edge ===");
        graph.removeEdge("A", "B");
        
        // Tampilkan adjacency list setelah penghapusan
        graph.showAdjacencyList();
    }
}
```

**Output**:
```
=== Menambah Node ===
Node 'A' berhasil ditambahkan.
Node 'B' berhasil ditambahkan.
Node 'C' berhasil ditambahkan.
Node 'D' berhasil ditambahkan.
Node 'E' berhasil ditambahkan.

=== Menambah Edge ===
Edge antara 'A' dan 'B' berhasil ditambahkan.
Edge antara 'A' dan 'C' berhasil ditambahkan.
Edge antara 'B' dan 'D' berhasil ditambahkan.
Edge antara 'C' dan 'D' berhasil ditambahkan.
Edge antara 'D' dan 'E' berhasil ditambahkan.

=== Daftar Semua Vertex ===
1. A
2. B
3. C
4. D
5. E

=== Adjacency List ===
A : B, C
B : A, D
C : A, D
D : B, C, E
E : D

=== BFS Traversal dari A ===
Urutan kunjungan: A -> B -> C -> D -> E -> END

=== DFS Traversal dari A ===
Urutan kunjungan: A -> B -> D -> C -> E -> END

=== Menghapus Edge ===
Edge antara 'A' dan 'B' berhasil dihapus.

=== Adjacency List ===
A : C
B : D
C : A, D
D : B, C, E
E : D
```

**Visualisasi Graf**:
```
Graf Awal:
      A
     / \
    B   C
     \ / \
      D   
      |
      E

Graf Setelah Hapus Edge A-B:
      A
       \
        C
       / \
      B   D
          |
          E
```

---

## 🎯 Kesimpulan

Class `Graph` ini menyediakan implementasi dasar struktur data graf dengan fitur:
- ✅ Menambah dan mengelola node/vertex
- ✅ Menambah dan menghapus edge (undirected)
- ✅ Menambah adjacency (directed)
- ✅ Traversal menggunakan BFS dan DFS
- ✅ Menampilkan vertex dan adjacency list

**Keterbatasan**:
- Maksimal 10 node
- Maksimal 10 tetangga per node
- Tidak mendukung weighted graph
- Tidak ada pengecekan duplikasi edge

**Kompleksitas**:
- `addNama()`: O(n) - worst case harus loop semua node
- `addEdge()`: O(n) - harus cari index dan cari slot kosong
- `removeEdge()`: O(n) - harus cari dan shift elemen
- `bfs()` / `dfs()`: O(V + E) - V = jumlah vertex, E = jumlah edge
- `showAdjacencyList()`: O(V × E) - loop semua vertex dan edge

---

## 📚 Referensi
- Graph Theory
- Adjacency List Representation
- Breadth-First Search (BFS)
- Depth-First Search (DFS)

---

**Dibuat untuk pembelajaran Struktur Data - Graph**