import java.util.Queue;

public class State {

    private int h,v;
    private double currVal;

//    private int nextAction;
//    private int otherAction1,otherAction2;
    private int[] possibleActions;
    private int actionTaken;

    private double oldVal;

    public  boolean isTerminal;

    private double north, south, east, west;

    private double [] qValues;


    public State(int h, int v)
    {
        this.h = h;
        this.v = v;
        currVal = 0.0;
        oldVal = 0.0;
        possibleActions = new int[3];
        actionTaken = 0;
        isTerminal = false;
        qValues = new double[4];


    }

    public double getEast() {
        return east;
    }

    public double getNorth() {
        return north;
    }

    public double getSouth() {
        return south;
    }

    public double getWest() {
        return west;
    }

    public void setEast(double east) {
        this.east = east;
    }

    public void setNorth(double north) {
        this.north = north;
    }

    public void setSouth(double south) {
        this.south = south;
    }

    public void setWest(double west) {
        this.west = west;
    }

    public double[] getqValues() {
        return qValues;
    }

    public void setQValues(double[] qValues) {
        this.qValues = qValues;


    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public State(int h, int v, double currVal) {
        this.h = h;
        this.v = v;
        this. currVal = currVal;
        possibleActions = new int[3];
        actionTaken = 0;
    }
    public int getH() {
        return h;
    }

    public int getActionTaken() {
        return actionTaken;
    }
    public void setActionTaken(int actionTaken) {
        this.actionTaken = actionTaken;
        //east
    }

    public int getV() {
        return v;
    }
    public double getCurrVal(){
        return currVal;
    }
    public void setCurrVal(double currVal) {
        this.oldVal = this.currVal;
        this.currVal = currVal;
    }

    public double getOldVal() {
        return oldVal;
    }

    public int[] getPossibleActions() {
        return possibleActions;
    }
}
