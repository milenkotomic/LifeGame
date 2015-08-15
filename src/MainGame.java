/**
 * Created by milenkotomic on 15-08-15.
 */
public class MainGame {

    public static void main(String[] args) throws InterruptedException {
        Cube cube = new Cube(3);
        cube.randomCells();

        /*Thread t1 = new Thread(new LifeGameThread(cube, 0, 1, 2));
        t1.start();
        t1.join();*/

        Thread t1 = new Thread(new LifeGameThread(cube, 0, 3, 30));
        Thread t2 = new Thread(new LifeGameThread(cube, 1, 3, 30));
        Thread t3 = new Thread(new LifeGameThread(cube, 2, 3, 30));
        /*Thread t4 = new Thread(new LifeGameThread(cube, 3, 4, 4));*/
        t1.start();
        t2.start();
        t3.start();
        /*t4.start();*/
        t1.join();
        t2.join();
        t3.join();
        /*t4.join();*/

        System.out.println("fin");
    }

}
