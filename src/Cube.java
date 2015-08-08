import java.util.ArrayList;

/**
 * Created by luism on 06-08-15.
 */
public class Cube {
    private int size;
    private ArrayList<ArrayList<Node>> grilla;
    public Cube(int size){
        this.size=size;
        grilla= new ArrayList<>(size);
        initCells();
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Integer> getCellNeighbors(int cell) {
        int [] addPos= {1,2,3,4,5,6,7,8,9,10,11,12,13};
        ArrayList<Integer> neighboursState=new ArrayList<Integer>();
        for (int i = 0; i < addPos.length; i++) {
            int pos=nextNeigbour(cell+addPos[i]);
            Node neighbour= searchCell(pos);
            Node neighbour2=searchCell(-pos);
            neighboursState.add(neighbour.getState());
            neighboursState.add(neighbour2.getState());
        }
        return neighboursState;
    }

    private Node searchCell(int pos) {
        for (ArrayList<Node> list : grilla) {
            for (Node node: list){
                if (node.getNumber()==pos)
                    return node;
            }
        }
        return null;
    }

    private int nextNeigbour(int i) {
        if (i>size*size*size)
            return i%size;
        return i;
    }

    private void initCells(){
        int pos=0;
        for (int i = 0; i < size; i++) {
            ArrayList<Node> ycord=new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                Node newNode =new Node(pos,0);
                ycord.add(newNode);
                pos++;
            }
            grilla.add(ycord);
        }
    }
}
