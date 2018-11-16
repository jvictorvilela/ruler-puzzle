package ruler.puzzle;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author victor
 */
public class UniformCostSearch implements Search {
    
    @Override
    public DataSolution searchSolution(GameTree tree) {
        
        int numExpandedNodes = 0;
        Node finalState = null;
        Node root = tree.getRoot();
        
        // Cria fila de prioridades e implementa o método de comparação dos custos de cada nó
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node node1, Node node2) {
                        if (node1.calculateTotalCost() < node2.calculateTotalCost()) {
                            return -1;
                        }
                        if (node1.calculateTotalCost() > node2.calculateTotalCost()) {
                            return 1;
                        }
                        return 0;
                    }
                    
                }
        );
        
        priorityQueue.add(root);
        
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            
            if (tree.isFinished(node)) {
                finalState = node;
                break;
            } else {
                // expande o nó na árvore
                tree.generateNext(node);
                numExpandedNodes++;
                
                // adiciona os filhos do nó na pilha do algoritmo
                for (Node aux : node.getChildren()) {
                    priorityQueue.add(aux);
                }
            }
        }
        

        return new DataSolution(tree, numExpandedNodes, finalState, "Busca de Menor Custo");
        
    }
}
