
import java.util.ArrayList;

public class BoardNode {
	private BoardNode parent;
	private Board state;
	private ArrayList<BoardNode> children;

	
	public BoardNode(Board state) {
		this.parent = null;
		this.state = state;
	}
	public BoardNode(BoardNode parent, Board state, int depth) {
		this.parent = parent;
		this.state = state;
	}
	public BoardNode(BoardNode parent, Board state, ArrayList<BoardNode> children) {
		this.parent = parent;
		this.state = state;
		this.children = children;
	}
	
	public int value() {
		//int value = 0;
		int max = 0;
		if (this.state.isRowsTurn()) {
			max = this.state.maxValueInRow(this.state.getCurrentRow());
		} else {
			max = this.state.maxValueInCol(this.state.getCurrentCol());
		}
		return max;
	}
	
	public int alphabeta() {
		return alphabeta(this, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, true).move;
	}
	
	public Move alphabeta(BoardNode node, int depth, int alpha, int beta, boolean minimax) {
		long start = System.currentTimeMillis();
    	long maxDuration = 10000; // Maximum duration in milliseconds (10 seconds)
		if (depth == 0 || node.state.gameOver() || System.currentTimeMillis() - start > maxDuration) {
			Move v = new Move();
			v.value = node.value();
			return v;
		}
		if (minimax) {
			Move v = new Move();
			v.value = Integer.MIN_VALUE;
			node.children = generateChildren(depth);
			for (int i = 0; i < node.children.size(); i++) {
				Move childMove = alphabeta(node.children.get(i), depth-1, alpha, beta, false);
        if (v.value < childMove.value) {
            v.value = childMove.value;
            v.move = i; // Set the move index or identifier
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
				Move childMove = alphabeta(node.children.get(i), depth-1, alpha, beta, true);
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
	
	public BoardNode clone() {
		ArrayList<BoardNode> c = new ArrayList<BoardNode>();
		return new BoardNode(this.parent.clone(), this.state.clone(), c);
	}
}
