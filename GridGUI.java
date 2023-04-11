import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridGUI extends JFrame {

    public final int h = 3;
    public final int v = 4;

    public Grid grid;


    public GridGUI(Grid grid){
        setTitle("Grid Drawing");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.grid = grid;
        add(new GridPanel());
        setVisible(true);
    }


    private class GridPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {


            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            double rewardValue  = 1;

            int width = getWidth();
            int height = getHeight();

            for (int i = 0; i <grid.getRow(); i++) {
                for (int j = 0; j < grid.getCol() ; j++) {

                    //yo
                    int x = i * width / grid.getRow();
                    int y = j * height / grid.getCol();


                    if (grid.getGrid()[i][j].isTerminal()) {

                        if (grid.getGrid()[i][j].getCurrVal() > 0) {

                            g.setColor(Color.GREEN);
                        } else if (grid.getGrid()[i][j].getCurrVal() == Double.NEGATIVE_INFINITY) {
                            g.setColor(Color.GRAY);
                        } else
                            g.setColor(Color.RED);
                        g.fillRect(x, y, width / grid.getRow(), height / grid.getCol());
                    } else {
                        g.setColor(getGreenGradient(rewardValue,grid.getGrid()[i][j].getCurrVal()));
                        g.fillRect(x, y, width / grid.getRow(), height / grid.getCol());

                    }


                    g.drawLine(x/2,y,x/2 + 5,y+5);
                    g.drawRect(x, y, width / grid.getRow(), height / grid.getCol());
                    g.setColor(Color.white);
                    g.setFont(new Font("Arial", Font.BOLD, 10));
                    String text = String.format("%.2f", grid.getGrid()[i][j].getCurrVal());
                    FontMetrics fm = g.getFontMetrics();
                    int textWidth = fm.stringWidth(text);
                    int textHeight = fm.getHeight();
                    int textX = x + (width / grid.getRow() - textWidth) / 2;
                    int textY = y + (height / grid.getCol() + textHeight) / 2;

                    g.drawString(text, textX, textY);
                }
            }

        }
    }

    public static Color getGreenGradient(double max, double curr) {

        int n = 0;
        // Ensure n is within the range of 0-255
        if (curr != 0) {
            n = (int) Math.max(0, Math.min(255,(255*curr/max)));
        }

        // Create and return the color object
        return new Color(0, n, 0);
    }
    public static Color getGridColor(double n) {
        // Convert the double to an integer
        int intValue = (int) n;

        // Choose the grid color based on the integer value of n
        if (intValue < 50) {
            return Color.LIGHT_GRAY;
        } else if (intValue < 100) {
            return Color.GRAY;
        } else {
            return Color.DARK_GRAY;
        }
    }



}
