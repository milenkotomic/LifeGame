import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by luism on 06-08-15.
 */
public class Cube {
    private int size;
    private ArrayList<ArrayList<ArrayList<Node>>> grilla;
    private HashMap<Integer, ArrayList<Integer>> map;

    public Cube(int size){
        this.size=size;
        grilla = new ArrayList<ArrayList<ArrayList<Node>>> (this.size);
        map = new HashMap<>();
        //initCells();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ArrayList<ArrayList<Node>>> getGrilla() {
        return grilla;
    }

    public void setGrilla(ArrayList<ArrayList<ArrayList<Node>>> grilla) {
        this.grilla = grilla;
    }

    public ArrayList<Integer> getCellNeighborsStates(int pos) {
        ArrayList<Integer> coords = map.get(pos);
        ArrayList<Integer> states = new ArrayList<>();
        Node node;
        int iCoord = coords.get(0);
        int jCoord = coords.get(1);
        int kCoord = coords.get(2);
        for (int i=iCoord-1; i < iCoord + 2; i++){
            int iNeighbor = i;
            if (i < 0){
                iNeighbor = size-1;
            }
            else if (i >= size){
                iNeighbor = 0;
            }
            for (int j=jCoord-1; j < jCoord+2; j++){
                int jNeighbor = j;
                if (j < 0){
                    jNeighbor = size-1;
                }
                else if (j >= size){
                    jNeighbor = 0;
                }
                for (int k=kCoord-1; k < kCoord+2; k++) {
                    int kNeighbor = k;
                    if (k < 0) {
                        kNeighbor = size-1;
                    }
                    else if (k >= size) {
                        kNeighbor = 0;
                    }
                    if (iNeighbor == iCoord && jNeighbor == jCoord && kNeighbor == kCoord)
                        continue;
                    int posToSearch = iNeighbor + (jNeighbor * size) + (kNeighbor * size * size);
                    node = searchCell(posToSearch);
                    states.add(node.getState());
                }
            }
        }
        return states;
    }

    public Node searchCell(int pos) {
        ArrayList<Integer> coords = map.get(pos);
        ArrayList<ArrayList<Node>> ycord = grilla.get(coords.get(0));
        ArrayList<Node> zcord = ycord.get(coords.get(1));
        return zcord.get(coords.get(2));
    }

    private int nextNeighbors(int i) {
        if (i>size*size*size)
            return i%size;
        return i;
    }

    public void initCells(){
        int pos;
        for (int i = 0; i < size; i++) {
            ArrayList<ArrayList<Node>> ycord= new ArrayList<ArrayList<Node>>(this.size);
            for (int j = 0; j < size; j++) {
                ArrayList<Node> zcord = new ArrayList<Node>(this.size);
                for (int k = 0; k < size; k++){
                    pos = i + (j * size) + (k * size * size);
                    Node newNode =new Node(pos, i, j, k, 0);
                    ArrayList<Integer> array = new ArrayList<>();
                    array.add(i);
                    array.add(j);
                    array.add(k);
                    this.map.put(pos, array);
                    zcord.add(newNode);
                }
                ycord.add(zcord);
            }
            grilla.add(ycord);
        }
    }

    public void randomCells(){
        int pos;
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            ArrayList<ArrayList<Node>> ycord= new ArrayList<ArrayList<Node>>(this.size);
            for (int j = 0; j < size; j++) {
                ArrayList<Node> zcord = new ArrayList<Node>(this.size);
                for (int k = 0; k < size; k++){
                    pos = i + (j * size) + (k * size * size);
                    Node newNode =new Node(pos, i, j, k, r.nextInt(2));
                    ArrayList<Integer> array = new ArrayList<>();
                    array.add(i);
                    array.add(j);
                    array.add(k);
                    this.map.put(pos, array);
                    zcord.add(newNode);
                }
                ycord.add(zcord);
            }
            grilla.add(ycord);
        }
    }
}
