package ruler.puzzle;

/**
 *
 * @author victor
 */
public class Game {
    private GameTree tree;
    
    public Game(int n, String initialState) {
        this.tree = new GameTree(new Node(initialState, null, 0), n);
    }
    
    public void solveGame(Search algorithm) {
        algorithm.searchSolution(tree).printData();
    }
    
    public GameTree getGameTree() {
        return this.tree;
    }
    
}
