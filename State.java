public class State {

    private int h,v;
    private double currVal;
    int[] possibleActions;

    public State(int h, int v )
    {
        this.h = h;
        this.v = v;
        currVal = 0.0;
        possibleActions = new int[3];
    }

    public int getH() {
        return h;
    }

    public int getV() {
        return v;
    }

    public double getCurrVal(){
        return currVal;
    }

    public int doBestAction(int action) {
        int doneAction = 0;
        if (action == 1) {
            doneAction = 1;

        } else if (action == 2) {
            doneAction = 2;

        } else if (action == 3) {
            doneAction = 3;

        } else if (action == 4) {
            doneAction = 4;
        }
        return doneAction;
    }

    public int[] getPossibleActions() {
        return possibleActions;
    }
}
