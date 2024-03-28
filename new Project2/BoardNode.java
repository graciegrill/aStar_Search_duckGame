
import java.util.ArrayList;

public class BoardNode {
	private BoardNode parent;
	private Board state;
	private ArrayList<BoardNode> children;

	//Boardnode constructor 1
	public BoardNode(Board state) {
		this.parent = null;
		this.state = state;
	}
	//Boardnode constructor 2
	public BoardNode(BoardNode parent, Board state, int depth) {
		this.parent = parent;
		this.state = state;
	}

	/*
	 * This is the utility function. It utilizes four elements of the game to value a move. First, it finds the maximum value that the computer can take
	 * and it subtracts the maximum move that the other player can make assuming that choice is made. Then, the difference between the computer and other player
	 * is added. The idea is that the wider the positive difference between scores and the choices that maximize the score of the computer 
	 * and minimize the score of the other player are reflected in the score.
	 */
	public int value(boolean Player) {
		
		int max = 0;
		//if computer is the row player
        if (Player) {
            max = this.state.maxValueInRow(this.state.getCurrentRow()) - this.state.maxValueInCol(this.state.getCurrentCol())
                    +this.state.getRowPlayer().getScore() - this.state.getColPlayer().getScore();
		//if the computer is the column player
        } else {
            max = this.state.maxValueInCol(this.state.getCurrentCol()) - this.state.maxValueInRow(this.state.getCurrentRow())
                    +this.state.getColPlayer().getScore() - this.state.getRowPlayer().getScore();
			
        }
		return max;
	}
	//Calls alphabeta with the appropriate parameters, simplifies the call in board
	public int alphabeta(boolean Player) {
		return alphabeta(this, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true, Player).move;
	}
	//Alphabeta method -returns a Move object which contains an integer value and a move
	public Move alphabeta(BoardNode node, int depth, int alpha, int beta, boolean minimax, boolean Player) {
		//if game is over or we reached our depth
		if (depth == 0 || node.state.gameOver()) {
			Move v = new Move();
			//Loss
			if (node.state.gameOver() && ((Player && node.state.getRowPlayer().getScore() <= node.state.getColPlayer().getScore()) || 
                    (!Player && node.state.getColPlayer().getScore() <= node.state.getRowPlayer().getScore()))) {
                v.value = -10000;
				//win
            } else if (node.state.gameOver() && ((Player && node.state.getRowPlayer().getScore() >= node.state.getColPlayer().getScore()) || 
                    (!Player && node.state.getColPlayer().getScore() >= node.state.getRowPlayer().getScore()))) {
                v.value = 10000;
            }
			//Everything else in between 
			else{
				v.value = node.value(Player);

			}
			//Sets the move if the computer is the row player
			if(this.getState().isRowsTurn()){
				v.move = node.getState().maxLocInRow(node.getState().getCurrentRow());
			}
			//Sets the move if the computer is the column player
			else{
				v.move = node.getState().maxLocInCol(node.getState().getCurrentCol());
			}
			return v;
		}
		//max value 
		if (minimax) {
			Move v = new Move();
			v.value = Integer.MIN_VALUE;
			node.children = node.generateChildren(depth);
			for (int i = 0; i < node.children.size(); i++) {
				Move childMove = alphabeta(node.children.get(i), depth-1, alpha, beta, false, Player);
        if (v.value < childMove.value) {
            v.value = childMove.value;
			if(node.getChildren().get(i).getState().isRowsTurn()){
            v.move = node.getChildren().get(i).getState().getCurrentRow();
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
			//minimum value
		} else {
			Move v = new Move();
			v.value = Integer.MAX_VALUE;
			node.children = node.generateChildren(depth);			
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
	//generates children of a particular node
	public ArrayList<BoardNode> generateChildren(int depth) {
		ArrayList<BoardNode> children = new ArrayList<BoardNode>();
		if (depth == 0) {
			return children;
		}
		if (this.state.isRowsTurn()) {
			for (int i = 0; i < this.state.getCells().length; i++) {
				Board newState = state.clone(); //clones new board each time
				if(!newState.getCells()[newState.getCurrentRow()][i].isSelected()) {
					//makes isolated move and updates score
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
