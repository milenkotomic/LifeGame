/**
 * Created by luism on 06-08-15.
 */
public class Node {

    private int number;
    private int i;
    private int j;
    private int k;
    private int state;
    public Node(int number, int i, int j, int k, int state){
        this.number=number;
        this.state=state;
        this.i = i;
        this.j = j;
        this.k = k;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
