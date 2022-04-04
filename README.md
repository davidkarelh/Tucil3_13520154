# Tugas-Kecil-3-IF2211-Strategi-Algoritma-Penyelesaian-Persoalan-15-Puzzle-Algoritma-Branch-And-Bound

## Struktur <i>Repository<i>
    .
    │   README.md
    │
    ├───bin
    │       Tucil3_13520154.jar
    │
    ├───doc
    │       Tucil3_13520154.pdf
    |       Tucil3_13520154.docx
    │
    ├───src
    │   ├───.idea
    │   │       modules.xml
    │   │       src.iml
    │   │       vcs.xml
    │   │       workspace.xml
    │   │
    │   └───15Puzzle
    │       │   15Puzzle.iml
    │       │
    │       ├───.idea
    │       │   │   .gitignore
    │       │   │   misc.xml
    │       │   │   modules.xml
    │       │   │   uiDesigner.xml
    │       │   │   vcs.xml
    │       │   │   workspace.xml
    │       │   │
    │       │   └───artifacts
    │       │           Tucil3_13520154_jar.xml
    │       │
    │       ├───out
    │       │   └───production
    │       │       └───15Puzzle
    │       │           │   BranchAndBound$1.class
    │       │           │   BranchAndBound.class
    │       │           │   DirectionNode.class
    │       │           │   FileReader.class
    │       │           │   MainForm$1.class
    │       │           │   MainForm$2.class
    │       │           │   MainForm.class
    │       │           │   PuzzlePanel$1.class
    │       │           │   PuzzlePanel$2.class
    │       │           │   PuzzlePanel.class
    │       │           │   State.class
    │       │           │   StateComparator.class
    │       │           │   Tucil3_13520154.class
    │       │           │
    │       │           └───com
    │       │               └───intellij
    │       │                   └───uiDesigner
    │       │                       └───core
    │       │                               AbstractLayout.class
    │       │                               DimensionInfo.class
    │       │                               GridConstraints.class
    │       │                               GridLayoutManager.class
    │       │                               HorizontalInfo.class
    │       │                               LayoutState.class
    │       │                               Spacer.class
    │       │                               SupportCode$TextWithMnemonic.class
    │       │                               SupportCode.class
    │       │                               Util.class
    │       │                               VerticalInfo.class
    │       │
    │       └───src
    │               BranchAndBound.java
    │               DirectionNode.java
    │               FileReader.java
    │               MainForm.form
    │               MainForm.java
    │               PuzzlePanel.java
    │               State.java
    │               StateComparator.java
    │               Tucil3_13520154.java
    │
    └───test
            notSolveable1.txt
            notSolveable2.txt
            solveable1.txt
            solveable2.txt
            solveable3.txt

## Keterangan
Program ini dibuat untuk menyelesaikan Tugas Kecil 3 Strategi Algoritma IF2211 Semester II 2021/2022. Program dibuat dengan menggunakan bantuan kakas (<i>tool<i>) Intellij IDEA 2021.3.2. Jika ingin melakukan kompilasi terhada program, harus menggunakan Intellij IDEA sebab program ini dibuat menggunakan GUI builder dari Intellij IDEA. Untuk memudahkan keperluan pengetesan, pembuat sudah membuat <i>file executable<i> yang dapat dijalankan langsung. Folder/direktori test pada <i>repository<i> ini berisi <i>file<i> yang dapat digunakan untuk <i>test case<i>. Disarankan untuk menggunakan <i>file<i> dengan format .txt sebagai <i>test case<i>. Program ini juga menampilkan pergeseran ubin pada <i>puzzle<i>.

## Cara Melakukan Kompilasi
1. Pastikan Intellij IDEA sudah ter-<i>install<i> dalam perangkat, usahakan Intellij IDEA memiliki versi 2021.3.2 untuk mencegah kemungkinan terjadinya permasalahan akibat perbedaan versi dan <i>repository<i> ini sudah di-<i>clone<i> ke dalam perangkat.
2. Buka Intellij IDEA.
3. Tekan file pada bagian atas Intellij IDEA.
4. Tekan open.
5. Masuk ke <i>repository<i> ini.
6. masuk ke folder/direktori src.
7. Pilih folder/direktori 15Puzzle.
8. Tekan OK.
9. Tekan run pada bagian atas Intellij IDEA.
9. Tekan run.
10. Akan muncul pilihan konfigurasi run.
11. Pilih Tucil3_13520154, program akan dikompilasi oleh Intellij IDEA dan dijalankan.

## Cara Menjalankan File Executable (lebih mudah daripada melakukan kompilasi)
1. Pastikan <i>repository<i> ini sudah di-<i>clone<i> ke dalam perangkat.
2. Masuk ke dalam <i>repository<i> ini.
3. Masuk ke dalam folder/direktori bin, akan terdapat <i>file executable<i> bernama Tucil3_13520154.jar.
4. Terdapat 2 cara untuk menjalankan <i>file executable<i>, yaitu:
    - Tekan <i>file<i> Tucil3_13520154.jar dua kali, program akan berjalan.
    - Masuk ke dalam terminal, lalu masuk (cd) ke dalam folder/direktori bin pada <i>repsository<i> ini, lalu masukkan java -jar Tucil3_13520154.jar ke dalam terminal.
5. Program akan terbuka, tekan Pick File untuk memilih file input.
6. Jika sudah memilih file input, tekan Solve Puzzle untuk mencoba menyelesaikan puzzle. Selama program menampilkan animasi pergerakan ubin, dimohon untuk tidak menekan tombol apapun adalam program.

## Author
    David Karel Halomoan
    1350154
    Mahasiswa Institut Teknologi Bandung

