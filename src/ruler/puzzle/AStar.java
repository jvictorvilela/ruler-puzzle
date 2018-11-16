package ruler.puzzle;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author victor
 */
public class AStar implements Search {
    
    GameTree tree;
    
    @Override
    public DataSolution searchSolution(GameTree tree) {
        
        this.tree = tree;
        int numExpandedNodes = 0;
        Node finalState = null;
        Node root = tree.getRoot();
        
        // Cria fila de prioridades e implementa o método de comparação dos custos de cada nó
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node node1, Node node2) {
                        if (heuristic(node1) < heuristic(node2)) {
                            return -1;
                        }
                        if (heuristic(node1) > heuristic(node2)) {
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
        

        return new DataSolution(tree, numExpandedNodes, finalState, "Busca A*");
        
    }
    
    // Calcula o somatório da função heurística dos nós anteriores, até a raiz
    private int costEvaluation(Node node) {
        
        int count = 0;
        
        while (node.getParent() != null) {
            count += heuristic(node);
            node = node.getParent();
        }
        
        return count;
    } 
    
    // Retorna a "distancia" do estado atual para o estado final
    private int heuristic(Node node) {
        
        int sum = 0;
        int subtraction = 0;
        
        for (int i = 0; i < 2*(tree.getN())+1; i++) {
            sum += distance(node.getBoard().getBoardArray()[i], i, node);
        }
        
        for (int i = 1; i < tree.getN(); i++) {
            subtraction += tree.getN() - i;
        }
        
        return sum-subtraction;
    }
    
    private int distance(char block, int i, Node node) {
        
        switch (block) {
            case 'A':
                if (node.getBoard().getEmptyPosition() < i) {
                    return 2*tree.getN()-i;
                } else {
                    return (2*tree.getN()-i)-1;
                }
            case 'B':
                if (node.getBoard().getEmptyPosition() > i) {
                    return i;
                } else {
                    return i-1;
                }
            case '#':
                return 0;
        }
        
        System.out.println("Erro!");
        return -1;
    }
}
