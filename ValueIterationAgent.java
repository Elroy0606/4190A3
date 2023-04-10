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
        System.out.println(calculateIterationValue(1, 0, 0.72, 0, 0.8, 0.9));
        //System.out.println("Hello World");
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
        }
    }

    private static double calculateIterationValue(double pos1, double pos2, double pos3, double reward, double prob, double gamma){
        double probAlt = (1-prob)/2;

        return (prob*(reward + (gamma*pos1)) + probAlt*(reward + (gamma*pos2)) + probAlt*(reward + (gamma*pos3)));

    }




}