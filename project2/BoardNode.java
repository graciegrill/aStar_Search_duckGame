package project2;

import java.util.ArrayList;

public class BoardNode {
	private BoardNode parent;
	private Board state;
	private ArrayList<BoardNode> children;
	
	public BoardNode(BoardNode parent, Board state) {
		this.parent = parent;
		this.state = state;
		this.children = generateChildren();
	}
	public BoardNode(BoardNode parent, Board state, ArrayList<BoardNode> children) {
		this.parent = parent;
		this.state = state;
		this.children = children;
	}
	
	public int value() {
		return 1; 
	}
	
	public int alphabeta() {
		return alphabeta(this, 30, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
	}
	
	public int alphabeta(BoardNode node, int depth, int alpha, int beta, boolean minimax) {
		if (depth == 0 || node.state.gameOver()) {
			return node.value();
		}
		if (minimax) {
			int value = Integer.MIN_VALUE;
			for (int i = 0; i < node.children.size(); i++) {
				value = Math.max(value, alphabeta(node.children.get(i), depth-1, alpha, beta, false));
				if (value > beta) {
					break;
				}
				alpha = Math.max(alpha, value);
			}
			return value;
		} else {
			int value = Integer.MAX_VALUE;
			for (int i = 0; i < node.children.size(); i++) {
				value = Math.min(value, alphabeta(node.children.get(i), depth-1, alpha, beta, true));
				if (value < alpha) {
					break;
				}
				beta = Math.min(beta, value);
			}
			return value;
		}
	}
	
	public ArrayList<BoardNode> generateChildren() {
		ArrayList<BoardNode> children = new ArrayList<BoardNode>();
		if (this.state.isRowsTurn()) {
			for (int i = 0; i < this.state.getCells().length; i++) {
				Board newState = state.clone();
				if(newState.canMakeMove(newState.getCurrentRow(), i)) {
					newState.makeMove(newState.getCurrentRow(), i);
					BoardNode newnode = new BoardNode(this, newState);
					children.add(newnode);
				}
			}
		} else {
			for (int i = 0; i < this.state.getCells().length; i++) {
				Board newState = state.clone();
				if(newState.canMakeMove(i, newState.getCurrentCol())) {
					newState.makeMove(i, newState.getCurrentRow());
					BoardNode newnode = new BoardNode(this, newState);
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
	@SuppressWarnings("unchecked")
	public BoardNode clone() {
		ArrayList<BoardNode> c = (ArrayList<BoardNode>) this.children.clone();
		return new BoardNode(this.parent.clone(), this.state.clone(), c);
	}
}
