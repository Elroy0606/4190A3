import java.util.Random;

public class RL {
    private double[][] qTable; // The Q-Table
    private double learningRate; // The learning rate (alpha)
    private double discountFactor; // The discount factor (gamma)
    private int numStates; // The number of states in the environment
    private int numActions; // The number of actions in the environment

    public RL(int numStates, int numActions, double learningRate, double discountFactor) {
        this.numStates = numStates;
        this.numActions = numActions;
        this.learningRate = learningRate;
        this.discountFactor = discountFactor;
        this.qTable = new double[numStates][numActions];

        // Initialize the Q-Table to 0
        for (int i = 0; i < numStates; i++) {
            for (int j = 0; j < numActions; j++) {
                qTable[i][j] = 0.0;
            }
        }
    }

    public int chooseAction(int state) {
        Random rand = new Random();
        if (rand.nextDouble() < 0.1) { // epsilon-greedy exploration
            return rand.nextInt(numActions);
        } else { // exploitation
            int bestAction = 0;
            for (int i = 1; i < numActions; i++) {
                if (qTable[state][i] > qTable[state][bestAction]) {
                    bestAction = i;
                }
            }
            return bestAction;
        }
    }

    public void updateQTable(int state, int action, int nextState, double reward) {
        double oldQValue = qTable[state][action];
        double nextMaxQValue = getMaxQValue(nextState);
        double newQValue = (1 - learningRate) * oldQValue + learningRate * (reward + discountFactor * nextMaxQValue);
        qTable[state][action] = newQValue;
    }

    private double getMaxQValue(int state) {
        double maxQValue = qTable[state][0];
        for (int i = 1; i < numActions; i++) {
            if (qTable[state][i] > maxQValue) {
                maxQValue = qTable[state][i];
            }
        }
        return maxQValue;
    }


}
