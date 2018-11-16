package ruler.puzzle;

import java.math.BigInteger;
import java.util.HashSet;


/**
 *
 * @author victor
 */
public class GameTree {
    private Node root;
    private int n;
    private int size;
    private int height;
    // tabela hash responsável por armazenar os estados já explorados
    private HashSet<BigInteger> hashTable;
    
    public GameTree(Node root, int n) {
        this.root = root;
        this.n = n;
        this.hashTable = new HashSet<BigInteger>();
        
        // adicina a raiz na tabela
        hashTable.add(this.root.getHashCode());
        this.size = 1;
    }
    
    // reinicia a árvore, deixando apenas o nó raiz
    public void restartTree() {
        this.root.getChildren().clear();
        this.hashTable = new HashSet<BigInteger>();
        hashTable.add(this.root.getHashCode());
        this.size = 1;
    }
    
    public void generateNext(Node node) {
        
        // for percorre n vezes a esquerda do array
        for (int i = node.getBoard().getEmptyPosition()-1; i >= (node.getBoard().getEmptyPosition()-n); i--) {
            if (i >= 0) {
                // cria novo nó
                Node newNode = new Node(node.getBoard().copyBoard(), node, Math.abs(i-node.getBoard().getEmptyPosition()));
                // modifica o tabuleiro
                newNode.getBoard().swapPosition(i);
                
                // verifica se o nó já foi expandido
                if (!hashTable.contains(newNode.getHashCode())) {
                    // insere nó na árvore
                    node.addChildren(newNode);
                    size++;
                    if (!isFinished(newNode)) {
                        hashTable.add(newNode.getHashCode());
                    }
                }
            }
        }
        
        // for percorre n vezes a direita do array
        for (int i = node.getBoard().getEmptyPosition()+1; i <= (node.getBoard().getEmptyPosition()+n); i++) {
            if (i < node.getBoard().boardSize()) {
                // cria novo nó
                Node newNode = new Node(node.getBoard().copyBoard(), node, Math.abs(i-node.getBoard().getEmptyPosition()));
                // modifica o tabuleiro
                newNode.getBoard().swapPosition(i);
                
                // verifica se o nó já foi expandido
                if (!hashTable.contains(newNode.getHashCode())) {
                    // insere nó na árvore
                    node.addChildren(newNode);
                    size++;
                    if (!isFinished(newNode)) {
                        hashTable.add(newNode.getHashCode());
                    }
                }
            }
        }
    }
    
    private void generateTree(Node node) {
        
        if (isFinished(node)) {
            return;
        }
        
        // for percorre n vezes a esquerda do array
        for (int i = node.getBoard().getEmptyPosition()-1; i >= (node.getBoard().getEmptyPosition()-n); i--) {
            if (i >= 0) {
                // cria novo nó
                Node newNode = new Node(node.getBoard().copyBoard(), node, Math.abs(i-node.getBoard().getEmptyPosition()));
                // modifica o tabuleiro
                newNode.getBoard().swapPosition(i);
                
                // verifica se o nó já foi expandido
                if (!hashTable.contains(newNode.getHashCode())) {
                    // insere nó na árvore
                    node.addChildren(newNode);
                    if (!isFinished(newNode)) {
                        hashTable.add(newNode.getHashCode());
                    }
                }
            }
        }
        
        // for percorre n vezes a direita do array
        for (int i = node.getBoard().getEmptyPosition()+1; i <= (node.getBoard().getEmptyPosition()+n); i++) {
            if (i < node.getBoard().boardSize()) {
                // cria novo nó
                Node newNode = new Node(node.getBoard().copyBoard(), node, Math.abs(i-node.getBoard().getEmptyPosition()));
                // modifica o tabuleiro
                newNode.getBoard().swapPosition(i);
                
                // verifica se o nó já foi expandido
                if (!hashTable.contains(newNode.getHashCode())) {
                    // insere nó na árvore
                    node.addChildren(newNode);
                    if (!isFinished(newNode)) {
                        hashTable.add(newNode.getHashCode());
                    }
                }
            }
        }
        
        for (Node aux : node.getChildren()) {
            generateTree(aux);
        }
    }
    
    // calcula a altura da árvore a partir de um nó específico
    public int heightTree(Node node) {
        int count = 1;
        while (node.getParent() != null) {
            node = node.getParent();
            count++;
        }
        return count;
    }
    
    // verifica se o estado do nó é um estado final
    public boolean isFinished(Node node) {
        char[] array = node.getBoard().getBoardArray();
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 'B' && array[i-1] == '#') {
                if (i-2 >= 0) {
                    if (array[i-2] == 'A') {
                        return false;
                    }
                }
            } else {
                if (array[i] == 'B' && array[i-1] == 'A') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int calculateCost(Node node) {
        int count = 0;
        while (node.getParent() != null) {
            count += node.getCost();
            node = node.getParent();
        }
        return count;
    }
    
    // apenas para testes
    public void printTree() {
        printTree(this.root);
    }
    
    private void printTree(Node node) {
        node.getBoard().printBoard();
        for (Node aux : node.getChildren()) {
            printTree(aux);
        }
    }
    
    public Node getRoot() {
        return this.root;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public int getN() {
        return this.n;
    }
    
}
