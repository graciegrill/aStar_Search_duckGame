import java.util.List;
import java.util.ArrayList;
public class BoardNode {
    
    
    private Board state;
    private BoardNode parentNode;
    private ArrayList<BoardNode> children;

    public ArrayList<BoardNode> getChildren() {
        return this.children;
    }

    public void setChildren(ArrayList<BoardNode> children) {
        this.children = children;
    }

    public BoardNode(Board state, BoardNode parentNode, ArrayList<BoardNode> children) {
        this.state = state;
        this.parentNode = parentNode;
        this.children = leaves();

    }

    public Board getState() {
        return this.state;
    }

    public void setState(Board state) {
        this.state = state;
    }

    public BoardNode getParentNode() {
        return this.parentNode;
    }

    public void setParentNode(BoardNode parentNode) {
        this.parentNode = parentNode;
    }

    public ArrayList<BoardNode> leaves(){
        ArrayList<BoardNode> frontier = new ArrayList<BoardNode>();
        for (BoardNode b:this.children){
            if(b.getState().getIsRowsTurn())
                for(int i = 0; i< b.getState().getCells().length; i++){
                    Board newState = state.clone();
                    if(newState.makeMove(newState.getCurrentRow(), i, newState.getCells()[newState.getCurrentRow()][i].getValue())){ //row column
                        BoardNode newNode = new BoardNode(newState,b );
                        frontier.add(newNode);
                        b.children.add(newNode);
                    }
                }
            else{
                for(int i = 0; i< b.getState().getCells().length; i++){
                    Board newState = state.clone();
                    if(newState.makeMove(i, newState.getCurrentCol(),  newState.getCells()[i][newState.getCurrentCol()].getValue())){ //row column
                        BoardNode newNode = new BoardNode(newState,b );
                        frontier.add(newNode);
                        b.children.add(newNode);
                    }

                }

    }
}
            return frontier;
    }
    public int alphabeta(BoardNode node, int depth, int alpha, int beta, boolean minimax) {
		if (depth == 0 || node.state.gameOver()) {
			return node.value();
		}
		if (minimax) {
			int value = Integer.MIN_VALUE;
			for (int i = 0; i < node.children.size(); i++) {
				value = Math.max(value, alphabeta(node.leaves().get(i), depth-1, alpha, beta, false));
				if (value > beta) {
					break;
				}
				alpha = Math.max(alpha, value);
			}
			return value;
		} else {
			int value = Integer.MAX_VALUE;
			for (int i = 0; i < node.children.size(); i++) {
				value = Math.min(value, alphabeta(node.leaves().get(i), depth-1, alpha, beta, true));
				if (value < alpha) {
					break;
				}
				beta = Math.min(beta, value);
			}
			return value;
		}
	}

    public int value(){
        int val = 0;
        if(this.state.getIsRowsTurn()){
            

        }
        else{

        }
        return 1;
    }
    
    
}
