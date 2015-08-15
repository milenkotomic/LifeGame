import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by milenkotomic on 04-08-15.
 */
public class LifeGameThread implements Runnable {
    static Integer[] loopsCompleted = null;
    int threadNumber;
    int threadsNumber;
    int repetitions;
    static int loops = 10;
    static Cube cube1 = null;
    static Cube cube2 = null;
    static Boolean swapped = false;
    private static final Object countLock = new Object();

    public LifeGameThread(Cube cube, int thread, int threads, int repetitions){
        this.threadNumber = thread;
        this.threadsNumber = threads;
        this.repetitions = repetitions;
        synchronized (countLock){
            if (loopsCompleted == null && cube1 == null && cube2 == null) {
                loopsCompleted = new Integer[threadsNumber];
                for (int i = 0; i < threadsNumber; i++){
                    loopsCompleted[i] = 0;
                }
                cube1 = cube;
                cube2 = new Cube(cube1.getSize());
                cube2.initCells();
                System.out.println("hola");
            }
        }
        System.out.println(threadNumber);

    }

    public ArrayList<Integer> getCellsToCalculate(){
        int size = cube1.getSize()*cube1.getSize()*cube1.getSize();
        int coucinete = size / threadsNumber;
        int initial = coucinete * threadNumber;
        int end = coucinete * (threadNumber+1);
        ArrayList<Integer> cells = new ArrayList<Integer>();
        for (int i = initial; i < end; i++){
            cells.add(i);
        }
        return cells;
    }

    public int getNextState(int state, ArrayList<Integer> neighbors){
        int n1 = 1;
        int n2 = 9;
        int n3 = 18;
        int n4 = 25;
        int n = 0;
        for (int neighbourState: neighbors){
            n += neighbourState;
        }
        if (state == 0 && n1 <= n && n < n2){
            state = 1;
        }
        else if (state == 1 && n3 <= n && n < n4){
            state = 0;
        }

        return state;
    }

    public void writeNextState(int cell, int state){
        Node node = cube2.searchCell(cell);
        node.setState(state);
    }

    private synchronized void swapCubes() {
        cube1.setGrilla(cube2.getGrilla());
        cube2.initCells();
    }

    public int minLoop(){
        List b = Arrays.asList(loopsCompleted);

        int n1, n2;

        try {
            n1 = (int) Collections.min(b.subList(0, threadNumber));
        }
        catch (Exception e) {
            n1 = Integer.MAX_VALUE;
        }

        try {
            n2 = (int) Collections.min(b.subList(threadNumber + 1, b.size()));
        }
        catch (Exception e) {
            n2 = Integer.MAX_VALUE;;
        }
        return  Math.min(n1, n2);
    }

    @Override
    public void run() {
        ArrayList<Integer> cellsToCalc = getCellsToCalculate();
        while (loopsCompleted[threadNumber] < repetitions){

            for (int cell: cellsToCalc){
                ArrayList<Integer> neighbors = cube1.getCellNeighborsStates(cell);
                Node node = cube1.searchCell(cell);
                int nextState = getNextState(node.getState(), neighbors);
                writeNextState(cell, nextState);
            }
            loopsCompleted[threadNumber]++;
            synchronized (countLock){
                while(minLoop() < loopsCompleted[threadNumber]){
                    swapped = true;
                    try {
                        countLock.notifyAll();
                        countLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (swapped) {
                    System.out.println("Swapping. Thread: " + threadNumber + "Loop: " + loopsCompleted[threadNumber]);
                    swapCubes();
                    swapped = false;

                }

                countLock.notifyAll();
            }
        }


    }
}
