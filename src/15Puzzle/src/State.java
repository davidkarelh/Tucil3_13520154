import java.util.List;

public class State {
    public List<List<Integer>> puzzle;
    public int cost;
    public int depth;
    public int[] idxKosong = new int[2];
    public DirectionNode directionNode;

    public State(List<List<Integer>> puzzle, int cost, int depth, int row, int col, int lastMove) {
        this.puzzle = puzzle;
        this.cost = cost;
        this.depth = depth;
        this.idxKosong[0] = row;
        this.idxKosong[1] = col;
        this.directionNode = new DirectionNode();
        this.directionNode.direction = lastMove;
    }
}
