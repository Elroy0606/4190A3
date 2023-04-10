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

        Grid[0][1] = 2;
        Grid[1][0] = 22;
        Grid[2][1] = 211;
        Grid[1][2] = 2111;

        ValueIterationAgent v = new ValueIterationAgent();
        System.out.println(v.findHighestAdjacentValue(Grid,1,1));

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



    public  int[] findHighestAdjacentValue(double[][] grid, int row, int col) {
        double max = Double.MIN_VALUE;
        int result = -1;
        int [] arrResult = new int[3];

        int noAction = -1;
        int pos2 = -1;
        int pos3 = -1;


        // Check north
        if (row > 0 && grid[row-1][col] > max) {
            max = grid[row-1][col];
            result = 2;
            pos3 = 3;
            pos2 = 1;

        }

        // Check south
        if (row < grid.length-1 && grid[row+1][col] > max) {
            max = grid[row+1][col];
            result = 4;
            pos3 = 3;
            pos2 = 1;
        }

        // Check east
        if (col > 0 && grid[row][col-1] > max) {
            max = grid[row][col-1];
            result = 1;
            pos3 = 2;
            pos2 = 4;
        }

        // Check west
        if (col < grid[0].length-1 && grid[row][col+1] > max) {
            result = 3;
            pos3 = 2;
            pos2 = 4;
        }


        arrResult[0]= result;
        arrResult[2] = pos2;
        arrResult[3] = pos3;



        return arrResult;
    }


    public double[] getActionValue(int [] action,double [][]grid, int row, int col)
    {


        double[] result = new double[3];
        int count = 0;
        while(count != result.length) {
            for (int i = 0; i < action.length; i++) {
                //east
                if (action[i] == 1 && col - 1 > 0) {
                    result[count] =grid[row][col - 1];
                }

                //north
                else if (action[i] == 2 && row - 1 > 0) {
                    result[count] = grid[row - 1][col];
                }

                //west
                else if (action[i] == 3 && col + 1 < grid[0].length - 1) {
                    result[count] = grid[row][col + 1];

                }
                //south
                else if (action[i] == 4 && row + 1 < grid.length - 1) {
                    result[count] = grid[row + 1][col];
                }
            }
            count++;
        }


        return result;
    }


    public double getTransitionCost() {
        return transitionCost;
    }

    public double getProb()
    {
        return (1- noise);
    }

    public double getDiscount() {
        return discount;
    }

    public void currVal(double[][]grid, int x, int y)
    {
        double [] arr= getActionValue(findHighestAdjacentValue(grid,x,y),grid,x,y);
        grid[x][y] = calculateIterationValue(arr[0],arr[1],arr[2],getTransitionCost(),getProb(),getDiscount());
    }
}