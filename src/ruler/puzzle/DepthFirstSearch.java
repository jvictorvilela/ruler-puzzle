package ruler.puzzle;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author victor
 */
public class DepthFirstSearch implements Search{
    
    @Override
    public DataSolution searchSolution(GameTree tree) {
        
        int numExpandedNodes = 0;
        Node finalState = null;
        Node root = tree.getRoot();
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        
        while (!stack.empty()) {
            Node node = stack.pop();
            
            if (tree.isFinished(node)) {
                finalState = node;
                break;
            } else {
                // expande o nó na árvore
                tree.generateNext(node);
                numExpandedNodes++;
                
                // adiciona os filhos do nó na pilha do algoritmo
                for (Node aux : node.getChildren()) {
                    stack.push(aux);
                }
            }
        }
        

        return new DataSolution(tree, numExpandedNodes, finalState, "Busca em Profundidade");
        
    }
    
    
}
