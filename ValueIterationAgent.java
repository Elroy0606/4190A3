import java.util.*;

public class ValueIterationAgent {

    private int horizontal;
    private int vertical;
    private Map<Integer, List<Integer>> terminal;
    private Map<Integer, List<Integer>> boulder;
    private List<Integer> robotStartState;
    private int k;
    private int episodes;
    private double discount;
    private double alpha;
    private double noise;
    private double transitionCost;
    private double[][] values;

    public ValueIterationAgent(int horizontal, int vertical, Map<Integer, List<Integer>> terminal,
                               Map<Integer, List<Integer>> boulder, List<Integer> robotStartState,
                               int k, int episodes, double discount, double alpha, double noise, double transitionCost) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.terminal = terminal;
        this.boulder = boulder;
        this.robotStartState = robotStartState;
        this.k = k;
        this.episodes = episodes;
        this.discount = discount;
        this.alpha = alpha;
        this.noise = noise;
        this.transitionCost = transitionCost;

        this.values = new double[horizontal][vertical];
        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical; j++) {
                values[i][j] = 0.0;
            }
        }
    }

    public void runValueIteration() {
        for (int i = 0; i < k; i++) {
            double[][] newValues = new double[horizontal][vertical];
            for (int x = 0; x < horizontal; x++) {
                for (int y = 0; y < vertical; y++) {
                    double maxQ = Double.NEGATIVE_INFINITY;
                    for (int a = 0; a < 4; a++) {
                        double q = computeQValueFromValues(x, y, a);
                        if (q > maxQ) {
                            maxQ = q;
                        }
                    }
                    newValues[x][y] = maxQ;
                }
            }
            values = newValues;
        }
    }

    public int computeActionFromValues(int x, int y) {
        double maxQ = Double.NEGATIVE_INFINITY;
        int bestAction = -1;
        for (int a = 0; a < 4; a++) {
            double q = computeQValueFromValues(x, y, a);
            if (q > maxQ) {
                maxQ = q;
                bestAction = a;
            }
        }
        return bestAction;
    }

    public int computeQValueFromValues(int x, int y, int a)
    {
        return -1;
    }



}