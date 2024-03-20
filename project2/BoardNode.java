public class BoardNode {
    
    
    private Board gameBoard;
    private BoardNode parentNode;

    public BoardNode(Board gameBoard, BoardNode parentNode) {
        this.gameBoard = gameBoard;
        this.parentNode = parentNode;
    }

    public Board getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public BoardNode getParentNode() {
        return this.parentNode;
    }

    public void setParentNode(BoardNode parentNode) {
        this.parentNode = parentNode;
    }
    
    
}
