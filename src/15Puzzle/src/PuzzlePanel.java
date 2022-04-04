import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class PuzzlePanel extends JPanel {
    private List<List<Integer>> tiles;
    private int margin;
    private int gridSize;
    private int tileSize;
    private int[] blankPos = new int[2];

    public PuzzlePanel() {
        super();
        this.setBackground(Color.white);
//        this.setForeground(new Color(239, 83, 80));
        this.setForeground(new Color(25,25,112));
        this.setMinimumSize(new Dimension(550, 550));
        this.setFont(new Font("SansSerif", Font.BOLD, 60));
        this.tiles = new ArrayList<List<Integer>>() {{
            add(List.of(1, 2, 3, 4));
            add(List.of(5, 6, 7, 8));
            add(List.of(9, 10, 11, 12));
            add(List.of(13, 14, 15, 16));
        }};
        this.margin = 30;
        this.gridSize = (550 - 2 * margin);
        this.tileSize = gridSize / 4;
        this.setEmptySlot();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.drawPuzzle(g2d);
    }

    private  void drawPuzzle(Graphics2D g2d) {
        int r, c, x, y;
        for (int i = 0; i < 16; i++) {
            r = i / 4;
            c = i % 4;

            if (this.tiles.get(r).get(c) == 16) {
                continue;
            }

            x = this.margin + c * tileSize;
            y = this.margin + r * tileSize;

            g2d.setColor(getForeground());
            g2d.fillRoundRect(x, y, tileSize, tileSize, 25, 25);
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(x, y, tileSize, tileSize, 25, 25);
            g2d.setColor(Color.WHITE);
            this.drawString(g2d, String.valueOf(this.tiles.get(r).get(c)), x, y);
        }
    }

    private void drawString(Graphics2D g2d, String s, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        g2d.drawString(s,  x + (tileSize - fm.stringWidth(s)) / 2,
                y + (asc + (tileSize - (asc + desc)) / 2));
    }

    public void setTiles(List<List<Integer>> tiles) {
        this.tiles = tiles;
        this.setEmptySlot();
        repaint();
    }

    public boolean tryToSolve() {
        BranchAndBound.initiateNewPuzzle(this.tiles);
        return BranchAndBound.solve();
    }

    public void solve() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (BranchAndBound.st.isEmpty()) {
                    timer.cancel();
                }

                if (!BranchAndBound.st.isEmpty()) {
                    int move = BranchAndBound.st.pop();

                    if (move == 1) {
                        int temp = tiles.get(blankPos[0] - 1).get(blankPos[1]);
                        tiles.get(blankPos[0] - 1).set(blankPos[1], 16);
                        tiles.get(blankPos[0]).set(blankPos[1], temp);
                        blankPos[0]--;
                    } else if (move == -1) {
                        int temp = tiles.get(blankPos[0] + 1).get(blankPos[1]);
                        tiles.get(blankPos[0] + 1).set(blankPos[1], 16);
                        tiles.get(blankPos[0]).set(blankPos[1], temp);
                        blankPos[0]++;
                    } else if (move == 2) {
                        int temp = tiles.get(blankPos[0]).get(blankPos[1] + 1);
                        tiles.get(blankPos[0]).set(blankPos[1] + 1, 16);
                        tiles.get(blankPos[0]).set(blankPos[1], temp);
                        blankPos[1]++;
                    } else {
                        int temp = tiles.get(blankPos[0]).get(blankPos[1] - 1);
                        tiles.get(blankPos[0]).set(blankPos[1] - 1, 16);
                        tiles.get(blankPos[0]).set(blankPos[1], temp);
                        blankPos[1]--;
                    }
                    repaint();
                }


                if (BranchAndBound.st.isEmpty()) {
                    timer.cancel();
                }
            }
        }, 0, 500);
}

    private void setEmptySlot() {
        boolean found = false;
        for (int i =0; i < 4 && !found; i++) {
            for (int j = 0; j < 4 && !found; j++) {
                if (this.tiles.get(i).get(j) == 16) {
                    found = true;
                    this.blankPos[0] = i;
                    this.blankPos[1] = j;
                }
            }
        }
    }



}
