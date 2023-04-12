import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.String.valueOf;

public class ValueIterationAgent {
    static int horizontal;
    static int vertical;
    static int K;
    static int Episodes;
    static double Discount;
    static double alpha;
    static double Noise;
    static double TransitionCost;
    static ArrayList<int[]> Terminals = new ArrayList<>();
    static ArrayList<int[]> Boulders = new ArrayList<>();
    static int[] RobotStartState = new int[2];




    public static void main(String[] args) throws IOException {
        int horizontal = 5;
        int vertical = 5;

        Grid newGrid = new Grid(vertical,horizontal);

    //processLine("Boulder={1={1,2},2={4,4}}");
        readFile("gridConf.txt");

        newGrid.initialize();
        newGrid.printGrid();

        int count = 1;
        System.out.println("K = "+ count++);
//        newGrid.addTerminal(2,3,1);
//        newGrid.addTerminal(1,3,-1);
//        newGrid.addTerminal(1,1,Double.NEGATIVE_INFINITY);
        newGrid.setK(count);
        newGrid.addTerminal(1,3, 10);
        newGrid.addTerminal(1,4, -10);
        newGrid.addTerminal(0,2, -10);

        newGrid.addTerminal(1,2, Double.NEGATIVE_INFINITY);
        newGrid.addTerminal(4,4, Double.NEGATIVE_INFINITY);
        newGrid.printGrid();


        while(count!= 1001) {
            newGrid.iterateOver();
            System.out.println("K = "+ count);
            newGrid.printGrid();
            newGrid.setK(count++);
        }

        GridGUI displayGrid = new GridGUI(newGrid);

    }
    private static double calculateIterationValue(double pos1, double pos2, double pos3, double reward, double prob, double gamma){
        double probAlt = (1-prob)/2;
        return (prob*(reward + (gamma*pos1)) + probAlt*(reward + (gamma*pos2)) + probAlt*(reward + (gamma*pos3)));

    }
    public static void doTheThing(Grid grid){
        for (int i = 0; i < grid.getRow()-1; i++) {
            for (int j = 0; j < grid.getCol()-1; j++) {

            }

        }
    }
    public double[] getActionValue(int [] action,double [][]grid, int row, int col) {
        double[] result = new double[3];
        int count = 0;
        while(count != result.length) {
            for (int j : action) {
                //east
                if (j == 1 && col - 1 > 0) {
                    result[count] = grid[row][col - 1];
                }

                //north
                else if (j == 2 && row - 1 > 0) {
                    result[count] = grid[row - 1][col];
                }

                //west
                else if (j == 3 && col + 1 < grid[0].length - 1) {
                    result[count] = grid[row][col + 1];

                }
                //south
                else if (j == 4 && row + 1 < grid.length - 1) {
                    result[count] = grid[row + 1][col];
                }
            }
            count++;
        }


        return result;
    }
    public void runIteration(double[][] grid, int k)
    {

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                while(count < k)
                {

                    //some actions


                    count ++;
                }
                //k== count and reward
            }
        }
    }

    public static void readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            processLine(line);
        }

        reader.close();
    }

    public static void processLine(String line){
        int newStart = loopTillEquals(line);
        if(line.charAt(0) == 'H'){
            horizontal = (int)extractNumber(line);;  ;
        }
        else if(line.charAt(0) == 'T'){
            if(line.charAt(1) == 'r'){
                TransitionCost =extractNumber(line);; ;
            } else if (line.charAt(1) == 'e') {
                Terminals = processCoordList(processTerminal(line));
            }

        }
        else if(line.charAt(0) == 'V'){
            vertical = (int)extractNumber(line);;  ;
        }
        else if(line.charAt(0) == 'B'){
            Boulders = processCoordinates(processBoulder(line));
        }
        else if(line.charAt(0) == 'R'){
            System.arraycopy(processStart(line), 0, RobotStartState, 0, processStart(line).length);

        }
        else if(line.charAt(0) == 'K'){
            K = (int)extractNumber(line);
        }
        else if(line.charAt(0) == 'E'){
            Episodes  = (int) extractNumber(line);;  ;
        }
        else if(line.charAt(0) == 'D'){
            Discount = extractNumber(line);;  ;
        }
        else if(line.charAt(0) == 'N'){
            Noise = extractNumber(line);;  ;

        }
        else if(line.charAt(0) == 'a'){
            alpha  = extractNumber(line);;  ;
        }
    }
    public static int loopTillEquals(String s){
        int equalsPos = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '='){
                equalsPos = i;
                break;
            }
        }
        return equalsPos;
    }
    public static ArrayList<String> processTerminal(String s){
        int newStart = loopTillEquals(s)+2;
        ArrayList<String> idk = new ArrayList<>();
        int commaCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < (s.length()-newStart-1); i++) {
            if(s.charAt(newStart+i) == ','){
                commaCounter++;
            }
            if(commaCounter != 3) {
                stringBuilder.append(s.charAt(newStart + i));
            }
            else{
                idk.add(valueOf(stringBuilder));
                stringBuilder = new StringBuilder();
                commaCounter = 0;
            }
            if(i == (s.length()-newStart-2)){
                idk.add(valueOf(stringBuilder));
            }
        }
        return idk;
    }
    public static ArrayList<String> processBoulder(String s){
        int newStart = loopTillEquals(s)+2;
        ArrayList<String> idk = new ArrayList<>();
        int commaCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < (s.length()-newStart-1); i++) {
            if(s.charAt(newStart+i) == ','){
                commaCounter++;
            }
            if(commaCounter != 2) {
                stringBuilder.append(s.charAt(newStart + i));
            }
            else{
                idk.add(valueOf(stringBuilder));
                stringBuilder = new StringBuilder();
                commaCounter = 0;
            }
            if(i == (s.length()-newStart-2)){
                idk.add(valueOf(stringBuilder));
            }
        }
        return idk;
    }
    public static int[] processStart(String s){
        int newStart = loopTillEquals(s)+2;
        int[] startStates = new int[2];
        int counter = 0;
        for (int i = newStart; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                startStates[counter] = Character.getNumericValue(s.charAt(i));
                counter++;
            }
        }

        return startStates;
    }
    public static ArrayList<int[]> processCoordinates(ArrayList<String> coordList){
        ArrayList<int[]> idk = new ArrayList<>();
        for (String cList : coordList) {
            int newStart = loopTillEquals(cList) + 1;
            int [] coordinates = new int[2];
            int checker = 0;
            for (int j = newStart; j <= (cList.length() - newStart); j++) {
                if (Character.isDigit(cList.charAt(j))) {
                    coordinates[checker] = Character.getNumericValue(cList.charAt(j));
                    checker++;
                }
            }
            idk.add(coordinates);
        }
        return idk;
    }
    public static ArrayList<int[]> processCoordList(ArrayList<String> coordList){
        ArrayList<int[]> idk = new ArrayList<>();
        for (String cList : coordList) {
            int newStart = loopTillEquals(cList) + 1;
            int [] coordinates = new int[3];
            int checker = 0;
            for (int j = newStart; j < (cList.length() - newStart - 1); j++) {
                if(cList.charAt(j) == '+'| cList.charAt(j) == '-'){
                    String newString = cList.substring(j);
                    coordinates[2] = processReward(newString);
                } else if (Character.isDigit(cList.charAt(j))) {
                    coordinates[checker] = Character.getNumericValue(cList.charAt(j));
                    checker++;
                }
            }
            idk.add(coordinates);
        }
        return idk;
    }
    public static double extractNumber(String str) {
        String[] parts = str.split("=");
        String numberStr = parts[1].trim();
        double number = Double.parseDouble(numberStr);
        return number;
    }

    public static int processReward(String reward){
        int rewardInt = 0;
        StringBuilder subInt = new StringBuilder();
        if(reward.charAt(0) == '+'){
            for (int j = 1; j < reward.length()-1; j++) {
                subInt.append(reward.charAt(j));
            }
            rewardInt = Integer.parseInt(String.valueOf(subInt));
        }
        else if(reward.charAt(0) == '-'){
            for (int j = 1; j < reward.length()-1; j++) {
                subInt.append(reward.charAt(j));
            }
            rewardInt = Integer.parseInt(String.valueOf(subInt)) * -1;
        }
        return rewardInt;
    }


}