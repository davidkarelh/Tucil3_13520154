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
    public PuzzlePanel puzzlePanel;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;

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

                    scrollPane.getViewport().setView(table);
                }

            }
        });
    }
}
