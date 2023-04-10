public class State {

    private int h,v;
    private double currVal;


    public State(int h, int v )
    {
        this.h = h;
        this.v = v;
        currVal = 0.0;
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

    public void setCurrVal(double currVal)
    {
        this.currVal = currVal;
    }

}
