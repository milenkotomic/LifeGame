import java.util.ArrayList;

/**
 * Created by milenkotomic on 04-08-15.
 */
public class LifeGameThread implements Runnable {
    int iterations = 0;
    int threadNumber;
    int threadsNumber;
    int loops;
    Object cube1; // TODO: cambiar tipo
    Object cube2 = new Object(); // TODO: cambiar tipo

    public LifeGameThread(Object cube, int thread, int threads, int loops){
        this.cube1 = cube;
        this.threadNumber = thread;
        this.threadsNumber = threads;
        this.loops = loops;
    }

    public ArrayList<Integer> getCellsToCalculate(){
        return null;
    }

    public int getNextState(ArrayList<Integer> neighbors){
        return 0;
    }

    public void writeNextState(int cell, int state){
    }

    private void swapCubes() {
    }


    public synchronized void checkEndLoop() throws InterruptedException {
        iterations++;
        if (iterations%threadsNumber == 0){
            swapCubes();
            notifyAll();
        }
        else{
            wait();
        }
    }

    @Override
    public void run() {
        ArrayList<Integer> cellsToCalc = getCellsToCalculate();
        int loopsDone = 0;
        while (loopsDone < loops){
            for (int cell: cellsToCalc){
                ArrayList<Integer> neighbors = cube1.getCellNeighbors(cell);
                int nextState = getNextState(neighbors);
                writeNextState(cell, nextState);
            }
            try {
                checkEndLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
