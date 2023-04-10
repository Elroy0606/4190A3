public class ExitState {

    private int h,v;
    private double reward;

    public ExitState(int h, int v, double reward)
    {
        this.h = h;
        this.v = v;
        this.reward = reward;

    }

    public int getV() {
        return v;
    }

    public int getH() {
        return h;
    }

    public double getReward() {
        return reward;
    }
}
