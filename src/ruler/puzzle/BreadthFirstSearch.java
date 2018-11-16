
package ruler.puzzle;

import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author victor
 */
public class BreadthFirstSearch implements Search {
    
    @Override
    public DataSolution searchSolution(GameTree tree) {
        
        int numExpandedNodes = 0;
        Node finalState = null;
        Node root = tree.getRoot();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            
            if (tree.isFinished(node)) {
                finalState = node;
                break;
            } else {
                // expande o nó na árvore
                tree.generateNext(node);
                numExpandedNodes++;
                
                // adiciona os filhos do nó na pilha do algoritmo
                for (Node aux : node.getChildren()) {
                    queue.add(aux);
                }
            }
        }
        
        return new DataSolution(tree, numExpandedNodes, finalState, "Busca em Largura");
        
    }
}
