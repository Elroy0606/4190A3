import java.util.*;

public class ValueIterationAgent {
    private int k;
    private int episodes;
    private double discount;
    private double alpha;
    private double noise;
    private double transitionCost;
    private double[][] values;
    private Terminal terminalState;



    public static void main(String[] args) {
        int horizontal = 3;
        int vertical = 4;

        Grid newGrid = new Grid(horizontal,vertical);



        newGrid.initialize();
        newGrid.printGrid();

        int count = 1;
        System.out.println("K = "+ count++);
        newGrid.addTerminal(0,3,1);
        newGrid.addTerminal(1,3,-1);
        newGrid.addTerminal(1,1,Double.NEGATIVE_INFINITY);
//        newGrid.addTerminal(1,3, 10);
//        newGrid.addTerminal(1,4, -10);
//        newGrid.addTerminal(0,2, -10);
//
//        newGrid.addTerminal(1,2, Double.NEGATIVE_INFINITY);
//        newGrid.addTerminal(4,4, Double.NEGATIVE_INFINITY);
        newGrid.printGrid();


        while(count!= 3) {
            newGrid.iterateOver();
            System.out.println("K = "+ count);
            newGrid.printGrid();
            count++;
        }

        GridGUI displayGrid = new GridGUI(newGrid);

    }
    private static double calculateIterationValue(double pos1, double pos2, double pos3, double reward, double prob, double gamma){
        double probAlt = (1-prob)/2;
        return (prob*(reward + (gamma*pos1)) + probAlt*(reward + (gamma*pos2)) + probAlt*(reward + (gamma*pos3)));

    }

    public static void doTheThing(Grid grid){
        for (int i = 0; i < grid.getRow()-1; i++) {
            for (int j = 0; j < grid.getCol()-1; j++) {

            }

        }
    }


    /*public int[] findHighestAdjacentValue(State[][] grid, int row, int col) {
        double max = Double.MIN_VALUE;
        int result = -1;
        int [] arrResult = new int[3];
        int pos2 = -1;
        int pos3 = -1;


        // Check north
        if (row > 0 && grid[row-1][col].getCurrVal() > max) {
            max = grid[row-1][col].getCurrVal();
            result = 2;
            pos3 = 3;
            pos2 = 1;

        }

        // Check south
        if (row < grid.length-1 && grid[row+1][col].getCurrVal() > max) {
            max = grid[row+1][col].getCurrVal();
            result = 4;
            pos3 = 3;
            pos2 = 1;
        }

        // Check east
        if (col > 0 && grid[row][col-1].getCurrVal() > max) {
            max = grid[row][col-1].getCurrVal();
            result = 1;
            pos3 = 2;
            pos2 = 4;
        }

        // Check west
        if (col < grid[0].length-1 && grid[row][col+1].getCurrVal() > max) {
            result = 3;
            pos3 = 2;
            pos2 = 4;
        }



        grid[row][col].setNextAction( result);
        grid[row][col].
        arrResult[0]= result;
        arrResult[2] = pos2;
        arrResult[3] = pos3;



        return arrResult;
    }
*/

    public double[] getActionValue(int [] action,double [][]grid, int row, int col) {
        double[] result = new double[3];
        int count = 0;
        while(count != result.length) {
            for (int j : action) {
                //east
                if (j == 1 && col - 1 > 0) {
                    result[count] = grid[row][col - 1];
                }

                //north
                else if (j == 2 && row - 1 > 0) {
                    result[count] = grid[row - 1][col];
                }

                //west
                else if (j == 3 && col + 1 < grid[0].length - 1) {
                    result[count] = grid[row][col + 1];

                }
                //south
                else if (j == 4 && row + 1 < grid.length - 1) {
                    result[count] = grid[row + 1][col];
                }
            }
            count++;
        }


        return result;
    }


    public void runIteration(double[][] grid, int k)
    {

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                while(count < k)
                {

                    //some actions


                    count ++;
                }
                //k== count and reward
            }
        }
    }

//    public double valueIterationAlgorithm(int k ,State currState, double reward, double gamma, double probability)
//    {
//        if(k == 0  || terminalState.isExit(currState.getH(),currState.getV(),currState.getCurrVal()))
//        {
//            return currState.getCurrVal();
//        }
//
//     //   currState = probability*( reward + gamma* valueIterationAlgorithm(k-1,));
//
//
//
//
//
//
//
//        return 0;
//
//    }

    public double getTransitionCost() {
        return transitionCost;
    }

    public double getProb() {
        return (1- noise);
    }

    public double getDiscount() {
        return discount;
    }


    public static void createGrid(double [][] Grid) {
        int numRows = Grid.length;
        int numCols = Grid[0].length;

        // Print top row
        System.out.print("|");
        for (int i = 0; i < numCols; i++) {
            System.out.print("______|");
        }
        System.out.println();

        // Print middle rows
        for (int i = 0; i < numRows; i++) {
            System.out.print("|");
            for (int j = 0; j < numCols; j++) {
                System.out.printf(" %.2f |", Grid[i][j]);
            }
            System.out.println();

            System.out.print("|");
            for (int j = 0; j < numCols; j++) {
                System.out.print("______|");
            }
            System.out.println();
        }
    }

        }