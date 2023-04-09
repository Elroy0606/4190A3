import java.util.*;

public class ValueIterationAgent {
    private int k;
    private int episodes;
    private double discount;
    private double alpha;
    private double noise;
    private double transitionCost;
    private double[][] values;

    public static void main(String[] args) {
        int horizontal = 3;
        int vertical = 4;
        double [][] Grid = new double[horizontal][vertical];
        for (int i = 0; i < horizontal; i++) {
            Arrays.fill(Grid[i], 0.0);
        }
        computeValueIteration(Grid,2);
        System.out.println("Hello World");
    }

    private static void computeValueIteration(double[][] grid, int i) {
        int currPosH;
        int currPosV;
        for (int j = 0; j < i; j++) {
            if (i == 0){
                continue;
            }
            else if(i == 1){
                grid[1][3] = -1.0;
                grid[0][3] = 1.0;
                currPosH = 0;
                currPosH = 3;
                continue;
            }
            if(currPosH )


        }


    }

    public ValueIterationAgent(int horizontal, int vertical, int k, int episodes, double discount, double alpha, double noise, double transitionCost) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.k = k;
        this.episodes = episodes;
        this.discount = discount;
        this.alpha = alpha;
        this.noise = noise;
        this.transitionCost = transitionCost;

        this.values = new double[horizontal][vertical];
        for (int i = 0; i < horizontal; i++) {
            Arrays.fill(values[i], 0.0);
        }
    }






}