import java.util.Comparator;

public class StateComparator implements Comparator<State> {
    public int compare(State s1, State s2) {
        if (s1.cost > s2.cost) {
            return 1;
        } else if (s1.cost < s2.cost) {
            return -1;
        }
        return 0;
    }
}
