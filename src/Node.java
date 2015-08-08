/**
 * Created by luism on 06-08-15.
 */
public class Node {

    private int number;
    private int state;
    public Node(int number, int state){
        this.number=number;
        this.state=state;
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

}
