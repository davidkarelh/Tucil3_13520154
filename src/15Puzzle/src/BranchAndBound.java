import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class BranchAndBound {
    // Atribut statik
    static private PriorityQueue<State> pq = new PriorityQueue<State>(new StateComparator());
    static private HashMap<List<List<Integer>>, Integer> myMap = new HashMap<List<List<Integer>>, Integer>();
    static private int limitCost = 999;
    static private List<List<Integer>> puzzle;
    static public Stack<Integer> st = new Stack<Integer>();
    static public int countNode = 0;
    static public float time;
    static public int stepCount;
    static public String[][] directionString;
    static public int sumKurangX;
    static public int[] kurang_i = new int[16];
    static private List<List<Integer>> goal = new ArrayList<List<Integer>>() {{
        add(List.of(1, 2, 3, 4));
        add(List.of(5, 6, 7, 8));
        add(List.of(9, 10, 11, 12));
        add(List.of(13, 14, 15, 16));
    }};

    static public void initiateNewPuzzle(List<List<Integer>> puzzle) {
        BranchAndBound.pq.clear();
        BranchAndBound.myMap.clear();
        BranchAndBound.st.clear();
        BranchAndBound.countNode = 1;
        BranchAndBound.limitCost = 999;
        int[] emptySlot = BranchAndBound.findEmptySlot(puzzle);
        BranchAndBound.puzzle = puzzle;
        BranchAndBound.pq.add(new State(puzzle, 0, 0, emptySlot[0], emptySlot[1], 0));

    }

    static private int[] findEmptySlot(List<List<Integer>> puzzle) {
        int[] ret = new int[2];
        boolean found = false;

        for (int i = 0; i < 4 && !found; i++) {
            for (int j = 0; j < 4 && !found; j++) {
                if (puzzle.get(i).get(j) == 16) {
                    found = true;
                    ret[0] = i;
                    ret[1] = j;
                }
            }
        }

        return ret;
    }

    static private boolean isSolveable() {
        int sumKurang = 0, X = 0;
        for (int i = 0; i < 16; i++) {
            int kurang = 0;
            for (int j = i + 1; j < 16; j++) {
                if (puzzle.get(j / 4).get(j % 4) < puzzle.get(i / 4).get(i % 4)) {
                    kurang++;
                }
            }

            BranchAndBound.kurang_i[puzzle.get(i / 4).get(i % 4) - 1] = kurang;

            if (puzzle.get(i / 4).get(i % 4) == 16 && (((i / 4) % 2 == 0 && (i % 4) % 2 != 0) || ((i / 4) % 2 != 0 && (i % 4) % 2 == 0))) {
                X = 1;
            }

            sumKurang += kurang;
        }

        BranchAndBound.sumKurangX = sumKurang + X;
        return (sumKurang + X) % 2 == 0;
    }

    static private void up(State s) {
        if (s.idxKosong[0] > 0) {
            List<List<Integer>> l = new ArrayList<List<Integer>>(4);

            for (List<Integer> el: s.puzzle) {
                l.add(new ArrayList<>(el));
            }

            int temp = l.get(s.idxKosong[0] - 1).get(s.idxKosong[1]);
            l.get(s.idxKosong[0] - 1).set(s.idxKosong[1], 16);
            l.get(s.idxKosong[0]).set(s.idxKosong[1], temp);

            int x = getCost(l) + s.depth + 1;
            if (x <= limitCost && (!myMap.containsKey(l) || x <= myMap.get(l))) {
                State ret = new State(l, x, s.depth + 1, s.idxKosong[0] - 1, s.idxKosong[1], 1);
                ret.directionNode.next = s.directionNode;
                myMap.put(l, x);
                pq.add(ret);
                BranchAndBound.countNode++;
            }
        }
    }

    static private void down(State s) {
        if (s.idxKosong[0] < 3) {
            List<List<Integer>> l = new ArrayList<List<Integer>>(4);

            for (List<Integer> el: s.puzzle) {
                l.add(new ArrayList<>(el));
            }

            int temp = l.get(s.idxKosong[0] + 1).get(s.idxKosong[1]);
            l.get(s.idxKosong[0] + 1).set(s.idxKosong[1], 16);
            l.get(s.idxKosong[0]).set(s.idxKosong[1], temp);

            int x = getCost(l) + s.depth + 1;
            if (x <= limitCost && (!myMap.containsKey(l) || x <= myMap.get(l))) {
                State ret = new State(l, x, s.depth + 1, s.idxKosong[0] + 1, s.idxKosong[1], -1);
                ret.directionNode.next = s.directionNode;
                myMap.put(l, x);
                pq.add(ret);
                BranchAndBound.countNode++;
            }
        }
    }

    static private void left(State s) {
        if (s.idxKosong[1] > 0) {
            List<List<Integer>> l = new ArrayList<List<Integer>>(4);

            for (List<Integer> el: s.puzzle) {
                l.add(new ArrayList<>(el));
            }

            int temp = l.get(s.idxKosong[0]).get(s.idxKosong[1] - 1);
            l.get(s.idxKosong[0]).set(s.idxKosong[1] - 1, 16);
            l.get(s.idxKosong[0]).set(s.idxKosong[1], temp);

            int x = getCost(l) + s.depth + 1;
            if (x <= limitCost && (!myMap.containsKey(l) || x <= myMap.get(l))) {
                State ret = new State(l, x, s.depth + 1, s.idxKosong[0], s.idxKosong[1] - 1, -2);
                ret.directionNode.next = s.directionNode;
                myMap.put(l, x);
                pq.add(ret);
                BranchAndBound.countNode++;
            }
        }
    }

    static private void right(State s) {
        if (s.idxKosong[1] < 3) {
            List<List<Integer>> l = new ArrayList<List<Integer>>(4);

            for (List<Integer> el: s.puzzle) {
                l.add(new ArrayList<>(el));
            }

            int temp = l.get(s.idxKosong[0]).get(s.idxKosong[1] + 1);
            l.get(s.idxKosong[0]).set(s.idxKosong[1] + 1, 16);
            l.get(s.idxKosong[0]).set(s.idxKosong[1], temp);

            int x = getCost(l) + s.depth + 1;
            if (x <= limitCost && (!myMap.containsKey(l) || x <= myMap.get(l))) {
                State ret = new State(l, x, s.depth + 1, s.idxKosong[0], s.idxKosong[1] + 1, 2);
                ret.directionNode.next = s.directionNode;
                myMap.put(l, x);
                pq.add(ret);
                BranchAndBound.countNode++;
            }
        }
    }

    static private int getCost(List<List<Integer>> mat) {
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (mat.get(i).get(j) != 16 && mat.get(i).get(j) != 4 * i + j + 1) {
                    ret++;
                }
            }
        }
        return ret;
    }

    static boolean solve() {
        Instant start = Instant.now();
        if (!isSolveable()) {
            System.out.println("Tidak bisa diselesaikan!");
            return false;
        } else {
            DirectionNode d = null;
            boolean success = false;
            State s;
            while (!pq.isEmpty()) {
                s = pq.poll();

                if (s.idxKosong[0] == 3 && s.idxKosong[1] == 3 && s.cost < limitCost && s.puzzle.equals(goal)) {
                    int tempCost = s.cost;
                    success = true;
                    limitCost = tempCost;
                    d = s.directionNode;
                    BranchAndBound.stepCount = s.depth;
                    pq.removeIf(element -> element.cost > tempCost);
                }

                if (s.directionNode.direction != -1) {
                    up(s);
                }

                if (s.directionNode.direction != -2) {
                    right(s);
                }

                if (s.directionNode.direction != 1) {
                    down(s);
                }

                if (s.directionNode.direction != 2) {
                    left(s);
                }

            }
            BranchAndBound.time = Duration.between(start, Instant.now()).toMillis() / 1000.0F;

            if (success) {

                BranchAndBound.directionString = new String[BranchAndBound.stepCount][1];
                int i = BranchAndBound.stepCount - 1;

                while (d.direction != 0) {
                    st.push(d.direction);

                    if (d.direction == 1) {
                        BranchAndBound.directionString[i][0] = "UP";
                    } else if (d.direction == -1) {
                        BranchAndBound.directionString[i][0] = "DOWN";
                    } else if (d.direction == 2) {
                        BranchAndBound.directionString[i][0] = "RIGHT";
                    } else if (d.direction == -2) {
                        BranchAndBound.directionString[i][0] = "LEFT";
                    }

                    i--;
                    d = d.next;
                }

                System.out.println("Waktu yang dibutuhkan: " + BranchAndBound.time / 1000.0F + " s");
                System.out.println("Jumlah langkah: " + BranchAndBound.stepCount);
                System.out.println("Jumlah simpul yang dibangkitkan: "  + BranchAndBound.countNode);
                System.out.println("Urutan langkah: ");
                for (String step[]: BranchAndBound.directionString) {
                    System.out.println(step[0]);
                }
                return true;
            } else {
                System.out.println("Tidak bisa diselesaikan!");
                return false;
            }
        }
    }
}
