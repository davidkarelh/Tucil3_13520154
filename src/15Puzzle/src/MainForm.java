import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel drawingPanel;
    private JLabel labelInput;
    private JButton buttonPickFile;
    private JButton buttonSolvePuzzle;
    private JLabel labelOutput;
    private JLabel labelSolveable;
    private JLabel labelTime;
    private JLabel labelStep;
    private JLabel labelNode;
    private JPanel panelStep;
    private JLabel labelSequence;
    private JPanel panelKurang;
    private JLabel labelSumKurang;
    public PuzzlePanel puzzlePanel;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;
    private JScrollPane kurangScrollpane;
    private JTable kurangTable;

    public MainForm() {
        String[] column = {"Urutan langkah: "};
        String[][] tableData = {{"-"}};

        // Konfigurasi tabel langkah
        JTable table = new JTable(tableData, column);
        table.setBackground(Color.WHITE);
        table.setShowGrid(false);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setTableHeader(null);
        table.setBorder(BorderFactory.createEmptyBorder());


        // Menengahkan elemen tabel
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

        // Konfigurasi form
        this.scrollPane = new JScrollPane(table);
        this.fileChooser = new JFileChooser();
        this.puzzlePanel = new PuzzlePanel();
        this.drawingPanel.setLayout(new GridLayout(1, 1));
        this.panelStep.setLayout(new GridLayout(1, 1));
        this.drawingPanel.add(this.puzzlePanel);
        this.panelStep.add(scrollPane);
        this.scrollPane.getViewport().setBackground(Color.WHITE);
//        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());

        String[] kurangColumn = {"KURANG(i): "};
        String[][] kurangTableData = {
                {"KURANG(1) = -"},
                {"KURANG(2) = -"},
                {"KURANG(3) = -"},
                {"KURANG(4) = -"},
                {"KURANG(5) = -"},
                {"KURANG(6) = -"},
                {"KURANG(7) = -"},
                {"KURANG(8) = -"},
                {"KURANG(9) = -"},
                {"KURANG(10) = -"},
                {"KURANG(11) = -"},
                {"KURANG(12) = -"},
                {"KURANG(13) = -"},
                {"KURANG(14) = -"},
                {"KURANG(15) = -"},
                {"KURANG(16) = -"},
        };

        this.kurangTable = new JTable(kurangTableData, kurangColumn);
        this.kurangTable.setFont(this.kurangTable.getFont().deriveFont(Font.BOLD));
        this.kurangTable.setBackground(Color.WHITE);
        this.kurangTable.setShowGrid(false);
        this.kurangTable.setFocusable(false);
        this.kurangTable.setRowSelectionAllowed(false);
        this.kurangTable.setTableHeader(null);
        this.kurangTable.setBorder(BorderFactory.createEmptyBorder());
        this.kurangTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

        this.kurangScrollpane = new JScrollPane(this.kurangTable);
        this.kurangScrollpane.getViewport().setBackground(Color.WHITE);
        this.kurangScrollpane.setBorder(BorderFactory.createEmptyBorder());
//        this.kurangScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.panelKurang.setLayout(new GridLayout(1, 1));
        this.panelKurang.add(this.kurangScrollpane);

        this.setContentPane(this.mainPanel);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        this.setLocationRelativeTo(null);

        // EVENT LISTENER
        buttonPickFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int result = fileChooser.showDialog(null, "Pick File");

                if (result == JFileChooser.APPROVE_OPTION) {
                    puzzlePanel.setTiles(FileReader.read(fileChooser.getSelectedFile()));
                    labelSolveable.setForeground(Color.BLACK);
                    labelSolveable.setText("File sudah dimasukkan.");
                    labelTime.setText("Waktu penyelesaian: -");
                    labelStep.setText("Jumlah langkah: -");
                    labelNode.setText("Jumlah simpul: -");

                    String[] column = {"Urutan langkah: "};
                    String[][] tableData = {{"-"}};

                    // Konfigurasi tabel langkah
                    JTable table = new JTable(tableData, column);
                    table.setBackground(Color.WHITE);
                    table.setShowGrid(false);
                    table.setFocusable(false);
                    table.setRowSelectionAllowed(false);
                    table.setTableHeader(null);
                    table.setBorder(BorderFactory.createEmptyBorder());

                    // Menengahkan elemen tabel
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

                    scrollPane.getViewport().setView(table);

                    String[] kurangColumn = {"KURANG(i): "};
                    String[][] kurangTableData = {
                            {"KURANG(1) = -"},
                            {"KURANG(2) = -"},
                            {"KURANG(3) = -"},
                            {"KURANG(4) = -"},
                            {"KURANG(5) = -"},
                            {"KURANG(6) = -"},
                            {"KURANG(7) = -"},
                            {"KURANG(8) = -"},
                            {"KURANG(9) = -"},
                            {"KURANG(10) = -"},
                            {"KURANG(11) = -"},
                            {"KURANG(12) = -"},
                            {"KURANG(13) = -"},
                            {"KURANG(14) = -"},
                            {"KURANG(15) = -"},
                            {"KURANG(16) = -"},
                    };

                    for (int i = 0; i < 16; i++) {
                        kurangTable.setValueAt(kurangTableData[i][0], i, 0);
                    }

                    labelSumKurang.setText("SUM(KURANG(i) + X = -");
                }
            }
        });

        buttonSolvePuzzle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                labelSolveable.setForeground(Color.BLACK);
                labelSolveable.setText("Solving...");
                puzzlePanel.setTiles(FileReader.read(fileChooser.getSelectedFile()));

                if (puzzlePanel.tryToSolve()) {
                    labelSolveable.setForeground(Color.GREEN);
                    labelSolveable.setText("Puzzle berhasil di-solve!");
                    labelTime.setText("Waktu penyelesaian: " + BranchAndBound.time +" s");
                    labelStep.setText("Jumlah langkah: " + BranchAndBound.stepCount);
                    labelNode.setText("Jumlah simpul: " + BranchAndBound.countNode);

                    // Menengahkan elemen tabel
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment( JLabel.CENTER );

                    String column[]={"Urutan Langkah:"};
                    JTable table = new JTable(BranchAndBound.directionString, column);
                    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
                    table.setBackground(Color.WHITE);
                    table.setShowGrid(false);
                    table.setFocusable(false);
                    table.setRowSelectionAllowed(false);
                    table.setTableHeader(null);
                    table.setBorder(BorderFactory.createEmptyBorder());
                    table.setFont(new Font("SansSerif", Font.BOLD, 11));

                    scrollPane.getViewport().setView(table);

                    panelStep.add(scrollPane);

                    for (int i = 0; i < 16; i++) {
                        kurangTable.setValueAt("KURANG(" + (i + 1) + ") = " + BranchAndBound.kurang_i[i], i, 0);
                    }

                    labelSumKurang.setText("SUM(KURANG(i) + X = " + BranchAndBound.sumKurangX);

                    puzzlePanel.solve();

                } else {
                    labelSolveable.setForeground(Color.RED);
                    labelSolveable.setText("Puzzle tidak dapat di-solve!");
                    labelTime.setText("Waktu penyelesaian: -");
                    labelStep.setText("Jumlah langkah: -");
                    labelNode.setText("Jumlah simpul: -");

                    String[] column = {"Urutan langkah: "};
                    String[][] tableData = {{"-"}};

                    // Konfigurasi tabel langkah
                    JTable table = new JTable(tableData, column);
                    table.setBackground(Color.WHITE);
                    table.setShowGrid(false);
                    table.setFocusable(false);
                    table.setRowSelectionAllowed(false);
                    table.setTableHeader(null);
                    table.setBorder(BorderFactory.createEmptyBorder());

                    // Menengahkan elemen tabel
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

                    for (int i = 0; i < 16; i++) {
                        kurangTable.setValueAt("KURANG(" + (i + 1) + ") = " + BranchAndBound.kurang_i[i], i, 0);
                    }

                    labelSumKurang.setText("SUM(KURANG(i) + X = " + BranchAndBound.sumKurangX);

                    scrollPane.getViewport().setView(table);
                }

            }
        });
    }
}
