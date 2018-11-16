package ruler.puzzle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author victor
 */
public class Main {

    private static int algorithm;
    private static String board;
    private static int n;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Escolha o algoritmo:");
        System.out.println("1 - Busca de Menor Custo (Uniform Cost Search)");
        System.out.println("2 - Busca em Largura (Breadth-First Search)");
        System.out.println("3 - Busca em Profundidade (Depth-First Search)");
        System.out.println("4 - Busca em Profundidade Iterativa (Iterative Deepening Search)");
        System.out.println("5 - Busca A*");
        System.out.println("6 - Busca IDA*");
        
        algorithm = input.nextInt();
        
        if (algorithm <= 0 && algorithm > 6) {
            System.out.println("");
            System.out.println("Algorítmo inválido!");
            System.exit(0);
        }
        
        System.out.println("");
        System.out.println("Digite o caminho para o arquivo:");
        
        input.nextLine(); //limpando buffer
        
        String arq = input.nextLine();
        
        BufferedReader br = new BufferedReader(new FileReader(arq));
        
        n = Integer.parseInt(br.readLine());
        board = br.readLine();
        
        br.close();
        
        
        Game game = new Game(n, board);
        
        if (algorithm == 1) {
            Search search = new UniformCostSearch();
            game.solveGame(search);
        }
        
        if (algorithm == 2) {
            Search search = new BreadthFirstSearch();
            game.solveGame(search);
        }
        
        if (algorithm == 3) {
            Search search = new DepthFirstSearch();
            game.solveGame(search);
        }
        
        if (algorithm == 4) {
            Search search = new IterativeDepthFirstSearch();
            game.solveGame(search);
        }
        
        if (algorithm == 5) {
            Search search = new AStar();
            game.solveGame(search);
        }
        
        if (algorithm == 6) {
            Search search = new IdaStar();
            game.solveGame(search);
        }
        
        
    }
}
