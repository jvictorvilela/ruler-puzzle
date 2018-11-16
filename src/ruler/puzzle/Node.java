package ruler.puzzle;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class Node {
    private Board board;
    private Node parent;
    private ArrayList<Node> children;
    private int cost;
    
    public Node(Board board, Node parent, int cost) {
        this.board = board;
        this.parent = parent;
        this.cost = cost;
        children = new ArrayList<Node>();
    }
    
    public Node(String state, Node parent, int cost) {
        this.parent = parent;
        this.board = new Board(state.length(), state);
        this.cost = cost;
        children = new ArrayList<Node>();
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public void addChildren(Node node) {
        children.add(node);
    }
    
    public ArrayList<Node> getChildren() {
        return this.children;
    }
    
    public BigInteger getHashCode() {
        String num = "";
        for (char aux : board.getBoardArray()) {
            switch (aux) {
                case '#':
                    num += "1";
                    break;
                case 'A':
                    num += "2";
                    break;
                case 'B':
                    num += "3";
                    break;
            }
        }
        return new BigInteger(num);
    }
    
    public int calculateTotalCost() {
        int count = 0;
        Node node = this;
        while (node.getParent() != null) {
            count += node.getCost();
            node = node.getParent();
        }
        return count;
    }
    
    public Node getParent() {
        return this.parent;
    }
    
    public int getCost() {
        return cost;
    }
}
