package ruler.puzzle;

import java.util.Stack;

/**
 *
 * @author victor
 */
public class IterativeDepthFirstSearch implements Search {
    
    @Override
    public DataSolution searchSolution(GameTree tree) {
        
        int numExpandedNodes = 0;
        Node finalState = null;
        int limit = 1;
        
        while (finalState == null) {
            
            numExpandedNodes = 0;
            tree.restartTree();
            Node root = tree.getRoot();
            Stack<Node> stack = new Stack<Node>();
            stack.push(root);

            while (!stack.empty()) {
                Node node = stack.pop();

                if (tree.isFinished(node)) {
                    finalState = node;
                    break;
                } else {

                    // verifica se já atingiu o limite de altura da árvore
                    if (tree.heightTree(node) < limit) {
                        // expande o nó na árvore
                        tree.generateNext(node);
                        numExpandedNodes++;

                        // adiciona os filhos do nó na pilha do algoritmo
                        for (Node aux : node.getChildren()) {
                            stack.push(aux);
                        }
                    }
                }
            }
            
            limit++;
        }
        

        return new DataSolution(tree, numExpandedNodes, finalState, "Busca em Profundidade Iterativa");
        
    }
}
