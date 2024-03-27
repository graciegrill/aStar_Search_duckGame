
import java.util.ArrayList;

public class BoardNode {
	private BoardNode parent;
	private Board state;
	private ArrayList<BoardNode> children;
	private int depth =4;

	
	public BoardNode(Board state) {
		this.parent = null;
		this.state = state;
	}
	public BoardNode(BoardNode parent, Board state, int depth) {
		this.parent = parent;
		this.state = state;
	}

	
	public int value() {
		//int value = 0;
		int max = 0;
		BoardNode p1 = this;
		for (int i = 0; i<this.depth; i++){
			p1 = p1.getParent();
		}
		if (p1.getState().isRowsTurn()){
			
		}
		/* if (this.state.isRowsTurn()) {
			max = this.state.maxValueInRow(this.state.getCurrentRow());
		} else {
			max = this.state.maxValueInCol(this.state.getCurrentCol());
		} */
		return max;
	}
	
	public int alphabeta(boolean Player) {
		return alphabeta(this, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true, Player).move;
	}
	
	public Move alphabeta(BoardNode node, int depth, int alpha, int beta, boolean minimax, boolean Player) {
		long start = System.currentTimeMillis();
    	long maxDuration = 10000; // Maximum duration in milliseconds (10 seconds)
		if (depth == 0 || node.state.gameOver() || System.currentTimeMillis() - start > maxDuration) {
			Move v = new Move();
			if (node.state.gameOver() && ((Player && node.state.getRowPlayer().getScore() <= node.state.getColPlayer().getScore()) || 
                    (!Player && node.state.getColPlayer().getScore() <= node.state.getRowPlayer().getScore()))) {
                v.value = -10000;
            } else if (node.state.gameOver() && ((Player && node.state.getRowPlayer().getScore() > node.state.getColPlayer().getScore()) || 
                    (!Player && node.state.getColPlayer().getScore() > node.state.getRowPlayer().getScore()))) {
                v.value = 10000;
            }
			if(this.getState().isRowsTurn()){
				v.move = node.getState().maxLocInRow(node.getState().getCurrentRow());
			}
			else{
				v.move = node.getState().maxLocInCol(node.getState().getCurrentCol());
			}
			v.value = node.value();
			return v;
		}
		if (minimax) {
			Move v = new Move();
			v.value = Integer.MIN_VALUE;
			node.children = generateChildren(depth);
			for (int i = 0; i < node.children.size(); i++) {
				Move childMove = alphabeta(node.children.get(i), depth-1, alpha, beta, false, Player);
        if (v.value < childMove.value) {
            v.value = childMove.value;
			if(node.getChildren().get(i).getState().isRowsTurn()){
            v.move = node.getChildren().get(i).getState().getCurrentRow(); // Set the move index or identifier
			}
			else{
				v.move = node.getChildren().get(i).getState().getCurrentCol();

			}
        }
				if (v.value > beta) {
					break;
				}
				alpha = Math.max(alpha, v.value);
			}
			return v;
		} else {
			Move v = new Move();
			v.value = Integer.MAX_VALUE;
			node.children = generateChildren(depth);			
			for (int i = 0; i < node.children.size(); i++) {
				Move childMove = alphabeta(node.children.get(i), depth-1, alpha, beta, true, Player);
        if (v.value > childMove.value) {
            v.value = childMove.value;
            v.move = i; // Set the move index or identifier
        }
				if (v.value < alpha) {
					break;
				}
				beta = Math.min(beta, v.value);
			}
			return v;
		}
	}
	
	public ArrayList<BoardNode> generateChildren(int depth) {
		ArrayList<BoardNode> children = new ArrayList<BoardNode>();
		if (depth == 0) {
			return children;
		}
		if (this.state.isRowsTurn()) {
			for (int i = 0; i < this.state.getCells().length; i++) {
				Board newState = state.clone(); //cloning new board each time PROBLEM!!!!!
				if(!newState.getCells()[newState.getCurrentRow()][i].isSelected()) {
					newState.makeIsolatedMove(newState.getCurrentRow(), i);
					BoardNode newnode = new BoardNode(this, newState, depth-1);
					children.add(newnode);
				}
			}
		} else {
			for (int i = 0; i < this.state.getCells().length; i++) {
				Board newState = state.clone();
				if(!newState.getCells()[i][newState.getCurrentCol()].isSelected()) {
					newState.makeIsolatedMove(i, newState.getCurrentCol());
					BoardNode newnode = new BoardNode(this, newState, depth-1);
					children.add(newnode);
				}
			}
		}
		return children;
	}
	
	public BoardNode getParent() {
		return this.parent;
	}
	public ArrayList<BoardNode> getChildren() {
		return this.children;
	}
	public Board getState() {
		return this.state;
	}
	public void setParent(BoardNode parent) {
		this.parent = parent;
	}
	public void setChildren(ArrayList<BoardNode> children) {
		this.children = children;
	}
	public void setState(Board state) {
		this.state = state;
	}	
	
}
