import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

    private final State [][] grid;
    private final int row;
    private final int col;

    private int k;
    public Grid(int vertical, int horizontal) {
        row = vertical;
        col = horizontal;
        grid = new State [row][col];
         k= 0;
    }

    public void initialize(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = new State(i,j);
            }
        }
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public State[][] getGrid() {
        return grid;
    }

    public void add(int row, int col, double val){
        State newState = new State(row, col);
        newState.setCurrVal(val);
        grid[row][col] = newState;
    }
    public void addTerminal(int row, int col, double val){
        State newState = new State(row, col);
        newState.setTerminal(true);
        newState.setCurrVal(val);
        grid[row][col] = newState;
    }

    public void iterateOver(){
        ArrayList<State> newState = new ArrayList<>();
        int [] actions = {1,2,3,4};
        double [] itValues = new double[4];
        double [] values;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j].getCurrVal() >=1 || grid[i][j].getCurrVal() < 0 ){
                    continue;
                }
                for (int k = 0; k < actions.length; k++) {
                    grid[i][j].setActionTaken(actions[k]);
                    values = getOtherCell(i,j);
                    itValues[k] = calculateIterationValue(values[0], values[1], values[2], -0.1, 0.8, 0.9);
                }


                grid[i][j].setNorth(itValues[1]);
                grid[i][j].setEast(itValues[0]);
                grid[i][j].setWest(itValues[2]);
                grid[i][j].setSouth(itValues[3]);

                if(returnMax(itValues) > 0) {

                    State temp = new State(i,j,returnMax(itValues));
                    newState.add(temp);

                }

//                Grid[i][j].setCurrVal(returnMax(itValues));
            }
        }

        updateGrid(newState);
    }

    public void updateGrid(ArrayList<State> nothing){
        int hm,ha = 0;
        for (int i = 0; i < nothing.size(); i++) {
            hm = nothing.get(i).getH();
            ha = nothing.get(i).getV();
            grid[hm][ha].setCurrVal(nothing.get(i).getCurrVal());



        }
    }
    public double[] getOtherCell(int row, int col){
        State s = grid[row][col];
        double[] retArr = new double[3];
        double pos1 = 0;
        double pos2 = 0;
        double pos3 = 0;
        if(s.getActionTaken() == 1){
            pos1 = getEast(s);
            pos2 = getNorth(s);
            pos3 = getSouth(s);
        }
        else if(s.getActionTaken() == 2){
            pos1 = getNorth(s);
            pos2 = getEast(s);
            pos3 = getWest(s);
        }
       else if(s.getActionTaken() == 3){
            pos1 = getWest(s);
            pos2 = getNorth(s);
            pos3 = getSouth(s);
        }
        else if(s.getActionTaken() == 4){
            pos1 = getSouth(s);
            pos2 = getEast(s);
            pos3 = getWest(s);
        }
        retArr[0] = pos1;
        retArr[1] = pos2;
        retArr[2] = pos3;

        return retArr;
    }
    private static double calculateIterationValue(double pos1, double pos2, double pos3, double reward, double prob, double gamma){
        double probAlt = (1-prob)/2;
        return (prob*(reward + (gamma*pos1)) + probAlt*(reward + (gamma*pos2)) + probAlt*(reward + (gamma*pos3)));

    }
    public boolean isValidCell(int row, int col){
        boolean isValid = false;
        if(row >=0 && row <= this.row-1){
            if(col >=0 && col<= this.col-1){
                isValid = true;
            }
        }
        return isValid;
    }
    public boolean isBlockCell(int row, int col){
        return grid[row][col].getCurrVal() == Double.NEGATIVE_INFINITY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double returnMax(double[] arr){
        double maxVal = 0.0;

        for (int i = 0; i < arr.length; i++) {
            maxVal = Math.max(maxVal, arr[i]);
        }

        return maxVal;
    }
    public double getNorth(State s){
        double retState = s.getCurrVal();
        if (isValidCell(s.getH()-1, s.getV()) && !isBlockCell(s.getH()-1, s.getV())){
            retState = grid[s.getH()-1][s.getV()].getCurrVal();
        }
        return retState;
    }
    public double getSouth(State s){
        double retState = s.getCurrVal();
        if (isValidCell(s.getH()+1, s.getV()) && !isBlockCell(s.getH()+1, s.getV())){
            retState = grid[s.getH()+1][s.getV()].getCurrVal();
        }
        return retState;
    }
    public double getEast(State s){
        double retState = s.getCurrVal();
        if (isValidCell(s.getH(), s.getV()+1) && !isBlockCell(s.getH(), s.getV()+1)){
            retState = grid[s.getH()][s.getV()+1].getCurrVal();
        }
        return retState;
    }
    public  double getWest(State s){
        double retState = s.getCurrVal();
        if (isValidCell(s.getH(), s.getV()-1) && !isBlockCell(s.getH(), s.getV()-1)){
            retState = grid[s.getH()][s.getV()-1].getCurrVal();
        }
        return retState;
    }

    public void printGrid() {
        // Print top row
        System.out.print("+");
        for (int i = 0; i < col; i++) {
            System.out.print("--------+");
        }
        System.out.println();

        // Print middle rows
        for (int i = 0; i < row; i++) {
            System.out.print("|");
            for (int j = 0; j < col; j++) {
                if(grid[i][j].getCurrVal() == Double.NEGATIVE_INFINITY){
                    System.out.printf("      |");
                }
                else{

                }

            }
            System.out.println();

            System.out.print("+");
            for (int j = 0; j < col; j++) {
                System.out.print("--------+");
            }
            System.out.println();
        }
    }



    public int findHighestAdjacentValue(int row, int col) {
        double max = Double.MIN_VALUE;
        int result = -1;



        // Check north
        if (row > 0 && grid[row-1][col].getCurrVal() > max) {
            max = grid[row-1][col].getCurrVal();
            result = 2;
        }

        // Check south
        if (row < grid.length-1 && grid[row+1][col].getCurrVal() > max) {
            max = grid[row+1][col].getCurrVal();
            result = 4;
        }

        // Check east
        if (col > 0 && grid[row][col-1].getCurrVal() > max) {
            max = grid[row][col-1].getCurrVal();
            result = 1;

        }

        // Check west
        if (col < grid[0].length-1 && grid[row][col+1].getCurrVal() > max) {
            result = 3;

        }





        return result;
    }

    public State getState(int row, int col)
    {
        return grid[row][col];
    }

    public void start(int row, int col)
    {

    }





  //  public void rlQval(int startX, int startY,double gamma, double alpha, double reward)
//    {
//
//        double [] values = new double[4];
//        int [] actions = {1,2,3,4};
//
//        int actionTaken = 0;
//
//        double sample = 0;
//        int x = startX;
//        int y = startY;
//        for (int i = 0; i < 10; i++) {
//            while(!grid[x][y].isTerminal) {
//
//
//
//
//                double max = returnMax(values);
//                for (int j = 0; j < values.length; j++) {
//                    if(max == values[j])
//                    {
//                        actionTaken = j+1;
//                    }
//
//                }
//                sample = reward + gamma*max;
//                double qVal = (1-alpha)* grid[x][y].getCurrVal() + (alpha* sample);
//
//                grid[x][y].setCurrVal(qVal);
//
//                if(actionTaken == 1 && isValidCell(x,y-1))
//                {
//                    y = y-1;
//                }
//                else if(actionTaken == 2 && isValidCell(x-1,y))
//                {
//                    x = x-1;
//                }
//                else if(actionTaken == 3 && isValidCell(x+1,y)){
//                    x= x+1;
//                }
//                else if(actionTaken == 4 && isValidCell(x,y+1)){
//                    y = y+1;
//                }
//                //System.out.println("X loc " + x + " Y loc " + y);
//            }
//
//            printGrid();
//            x = startX;
//            y = startY;
//        }
//    }
public void rlQval(int startX, int startY, double gamma, double alpha, double reward) {

    int[] actions = {1, 2, 3, 4};

    int x = startX;
    int y = startY;

    for (int i = 0; i < 10; i++) {

        // Reset the state to the starting position
        x = startX;
        y = startY;

        // Loop until a terminal state is reached
        while (!grid[x][y].isTerminal) {

            // Choose an action to take based on the current Q-values
            int actionTaken = chooseAction(x, y, actions);

            // Take the chosen action and observe the resulting state and reward
            int[] nextState = getNextState(x, y, actionTaken);
            double observedReward = getReward(x, y, actionTaken);

            // Update the Q-value for the current state-action pair
            double sample = observedReward + gamma * getMaxQValue(nextState[0], nextState[1]);
            double qValue = (1 - alpha) * getQValue(x, y, actionTaken) + alpha * sample;
            setQValue(x, y, actionTaken, qValue);

            // Update the current state to the next state
            x = nextState[0];
            y = nextState[1];
        }

        // Print the path taken by the agent
        printAgentPath(startX, startY);

        // Check for convergence and terminate if necessary
        if (hasConverged()) {
            break;
        }
    }
}

    private int chooseAction(int x, int y, int[] actions) {
        Random rand = new Random();
        double[] qValues = getQValues(x, y, actions);
        int[] maxActionIndices = getMaxActionIndices(qValues);
        return maxActionIndices[rand.nextInt(maxActionIndices.length)];
    }

    private int[] getNextState(int x, int y, int action) {
        int[] nextState = {x, y};
        if (action == 1 && isValidCell(x, y - 1)) {
            nextState[1] = y - 1;
        } else if (action == 2 && isValidCell(x - 1, y)) {
            nextState[0] = x - 1;
        } else if (action == 3 && isValidCell(x + 1, y)) {
            nextState[0] = x + 1;
        } else if (action == 4 && isValidCell(x, y + 1)) {
            nextState[1] = y + 1;
        }
        return nextState;
    }

    private double getReward(int x, int y, int action) {
        // TODO: Implement reward function


        return getActionVal(x,y,action);
    }

    private double getMaxQValue(int x, int y) {
        double[] qValues = getQValues(x, y, null);
        return returnMax(qValues);
    }

    private double getQValue(int x, int y, int action) {
        // TODO: Implement Q-value retrieval
        return 0;
    }

    private void setQValue(int x, int y, int action, double value) {
        // TODO: Implement Q-value update
    }

    private double[] getQValues(int x, int y, int[] actions) {
        // TODO: Implement Q-value retrieval
        return null;
    }

    private int[] getMaxActionIndices(double[] values) {
        List<Integer> maxIndices = new ArrayList<>();
        double max = returnMax(values);
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] == max) {
                maxIndices.add(i);
            }
        }
        int[] maxIndicesArray = new int[maxIndices.size()];
        for (int i = 0; i < maxIndices.size(); i++) {
            maxIndicesArray[i] = maxIndices.get(i);
        }
        return maxIndicesArray;

    }

    private boolean hasConverged() {
// TODO: Implement convergence checking
        return false;
    }

    private void printAgentPath(int startX, int startY) {
        int x = startX;
        int y = startY;
        while (!grid[x][y].isTerminal) {
            int actionTaken = chooseAction(x, y, null);
            System.out.println("X loc " + x + " Y loc " + y + " Action " + actionTaken);
            int[] nextState = getNextState(x, y, actionTaken);
            x = nextState[0];
            y = nextState[1];
        }
        System.out.println("X loc " + x + " Y loc " + y);
    }

    public double getActionVal( int x, int y, int action)
    {
        double result = 0;

        //east
        if(action == 1 && isValidCell(x,y-1))
        {
            result = grid[x][y-1].getCurrVal();
        }
        //north
        else if(action == 2 && isValidCell(x-1,y))
        {
            result = grid[x-1][y].getCurrVal();
        }
        //west
        else if(action == 3 && isValidCell(x,y+1))
        {
            result = grid[x][y+1].getCurrVal();
        }
        else if( action == 4 && isValidCell(x+1,y))
        {
            result = grid[x+1][y].getCurrVal();
        }
        return result;
    }
}
