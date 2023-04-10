public class State {

    private int h,v;
    private double currVal;

    private int nextAction;
    private int otherAction1,otherAction2;
    int[] possibleActions;

    public State(int h, int v )
    {
        this.h = h;
        this.v = v;
        currVal = 0.0;
        possibleActions = new int[3];
        nextAction = 0;
        otherAction1 = 0;
        otherAction2 = 0;
    }

    public int getH() {
        return h;
    }

    public int getNextAction() {
        return nextAction;
    }

    public int getOtherAction1() {
        return otherAction1;
    }

    public int getOtherAction2() {
        return otherAction2;
    }

    public void setNextAction(int nextAction) {
        this.nextAction = nextAction;
    }

    public void setOtherAction1(int otherAction1) {
        this.otherAction1 = otherAction1;
    }

    public void setOtherAction2(int otherAction2) {
        this.otherAction2 = otherAction2;
    }

    public int getV() {
        return v;
    }

    public double getCurrVal(){
        return currVal;
    }



    public int[] getPossibleActions() {
        return possibleActions;
    }
}
