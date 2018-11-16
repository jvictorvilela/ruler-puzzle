package ruler.puzzle;

/**
 *
 * @author victor
 */
public class Board {
    char[] boardArray;
    int emptyPosition;
    
    public Board(int size, String state) {
        boardArray = new char[size];
        
        for (int i = 0; i < size; i++) {
            boardArray[i] = state.charAt(i);
        }
        
        updateEmptyPosition();
    }
    
    public Board(char[] boardArray, int emptyPosition) {
        this.boardArray = boardArray;
        this.emptyPosition = emptyPosition;
    }
    
    // busca a posição do espaço vazio do tabuleiro e atualiza a variável emptyPosition
    private void updateEmptyPosition() {       
        for (int i = 0; i < boardArray.length; i++) {
            if (boardArray[i] == '#') {
                this.emptyPosition = i;
                break;
            }
        }
    }
   
    public char[] getBoardArray() {
        return boardArray;
    }
    
    public Board copyBoard() {
        char[] newArray = new char[this.boardArray.length];
        
        for (int i = 0; i < this.boardArray.length; i++) {
            newArray[i] = this.boardArray[i];
        }
        
        return new Board(newArray, this.emptyPosition);
    }
    
    // troca a posição indicada pelo espaço vazio
    public void swapPosition(int i) {
        if (i >= 0 && i <= boardArray.length) {
            boardArray[emptyPosition] = boardArray[i];
            boardArray[i] = '#';
            emptyPosition = i;
        }
    }
    
    public int getEmptyPosition() {
        return this.emptyPosition;
    }
    
    public int boardSize() {
        return boardArray.length;
    }
    
    public void printBoard() {
        System.out.print("|");
        for (char aux : boardArray) {
            if (aux == '#') {
                System.out.print(" ");
                System.out.print("|");
            } else {
                System.out.print(aux);
                System.out.print("|");
            }
        }
        System.out.println("");
    }
    
    public String boardToString() {
        String string = "|";
        for (char aux : boardArray) {
            if (aux == '#') {
                string += " |";
            } else {
                string += aux;
                string += "|";
            }
        }
        return string;
    }
}
