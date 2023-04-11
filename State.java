public class State {

    private int h,v;
    private double currVal;

//    private int nextAction;
//    private int otherAction1,otherAction2;
    private int[] possibleActions;
    private int actionTaken;

    private double oldVal;

    public  boolean isTerminal;


    public State(int h, int v)
    {
        this.h = h;
        this.v = v;
        currVal = 0.0;
        oldVal = 0.0;
        possibleActions = new int[3];
        actionTaken = 0;
        isTerminal = false;
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
