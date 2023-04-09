public class Grid {

    private final double [][] Grid;

    public Grid(int horizontal, int vertical) {
        Grid = new double [horizontal][vertical];
    }

    public void setRobotPos(int h, int v){
        State robotStart = new State(h, v);
        Grid[h][v] = Float.NEGATIVE_INFINITY;
    }
    public void setBoulder(int h, int v){
        Grid[h][v] = Float.NEGATIVE_INFINITY;
    }
    public void setExit(int h, int v, double reward){
        Grid[h][v] = reward;
    }

    private void setGridState(State s){
        int h = s.getH();
        int v = s.getH();
        Grid[h][v] = s.getCurrVal();
    }
}
