public class Grid {

    private final State [][] Grid;
    private final int row;
    private final int col;

    public Grid(int horizontal, int vertical) {
        row = horizontal;
        col = vertical;
        Grid = new State [horizontal][vertical];
    }

    public void initialize(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Grid[i][j] = new State(i,j);
            }
        }
    }

    public void add(int row, int col, double val){
        State newState = new State(row, col);
        newState.setCurrVal(val);
        Grid[row][col] = newState;
    }

    public void iterateOver(){

        int [] states = {1,2,3,4};
        double [] itValues = new double[4];
        double [] values = new double[3];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < states.length; k++) {
                    Grid[i][j].setActionTaken(states[k]);
                    values = getOtherCell(i,j);
                    itValues[k] = calculateIterationValue(values[0], values[1], values[2], 0, 0.8, 0.9);
                }
                Grid[i][j].setCurrVal(returnMax(itValues));
            }
        }
    }
    public double[] getOtherCell(int row, int col){
        State s = Grid[row][col];
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
            pos1 = getNorth(s);
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
        double retState = 0;
        if (isValidCell(s.getH()-1, s.getV())){
            retState = Grid[s.getH()-1][s.getV()].getCurrVal();
        }
        return retState;
    }
    public double getSouth(State s){
        double retState = 0;
        if (isValidCell(s.getH()+1, s.getV())){
            retState = Grid[s.getH()+1][s.getV()].getCurrVal();
        }
        return retState;
    }
    public double getEast(State s){
        double retState = 0;
        if (isValidCell(s.getH(), s.getV()+1)){
            retState = Grid[s.getH()][s.getV()+1].getCurrVal();
        }
        return retState;
    }
    public  double getWest(State s){
        double retState = 0;
        if (isValidCell(s.getH(), s.getV()-1)){
            retState = Grid[s.getH()][s.getV()-1].getCurrVal();
        }
        return retState;
    }

    public void printGrid() {
        // Print top row
        System.out.print("|");
        for (int i = 0; i < col; i++) {
            System.out.print("______|");
        }
        System.out.println();

        // Print middle rows
        for (int i = 0; i < row; i++) {
            System.out.print("|");
            for (int j = 0; j < col; j++) {
                System.out.printf(" %.2f |", Grid[i][j].getCurrVal());
            }
            System.out.println();

            System.out.print("|");
            for (int j = 0; j < col; j++) {
                System.out.print("______|");
            }
            System.out.println();
        }
    }
}
