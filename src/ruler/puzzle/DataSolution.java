package ruler.puzzle;

import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class DataSolution {
    
    String algorithmName;
    GameTree tree; // Árvore
    int numExpandedNodes; // Número de nós expandidos pelo algorítmo
    int sizeSolution;
    int treeSize;
    int cost;
    float branchingFactor;
    ArrayList<Node> steps;

    public DataSolution(GameTree tree, int numExpandedNodes, Node finalNode, String algorithmName) {
        this.tree = tree;
        this.numExpandedNodes = numExpandedNodes;
        this.algorithmName = algorithmName;
        
        treeSize = tree.getSize();
        branchingFactor = (float)treeSize/(float)numExpandedNodes;
        cost = tree.calculateCost(finalNode);
        
        steps = new ArrayList<Node>();
        Node node = finalNode;
        steps.add(node);
        while (node.getParent() != null) {
            node = node.getParent();
            steps.add(node);
        }
        
        sizeSolution = steps.size();
    }
    
    public void printData() {
        
        System.out.println("Algoritmo: "+algorithmName);
        System.out.println("Profundidade do estado meta: "+sizeSolution);
        System.out.println("Custo da solução: "+cost);
        System.out.println("Número de nós gerados: "+treeSize);
        System.out.println("Número de nós expandidos: "+numExpandedNodes);
        System.out.println("Fator de ramificação: "+branchingFactor);
        System.out.println("-----------------------------------");
        System.out.println("Caminho da solução:");
        for (int i = sizeSolution-1; i >= 0; i--) {
            System.out.println(sizeSolution-i+" - "+steps.get(i).getBoard().boardToString());
        }
    }
}
