//Added to Board:
	public Board(Player rp, Player cp, boolean turn, int row, int col, JLabel[] clabels, JLabel[] rlabels, Cell[][] c, JLabel mlabel) {
		rowP = rp;
		colP = cp;
		isRowsTurn = turn;
		currentRow = row;
		currentCol = col;
		colLabels = clabels;
		rowLabels = rlabels;
		cells = c;
		messageLabel = mlabel;
	}
	public Board clone() {
		Player rp = rowP.clone();
		Player cp = colP.clone();
		Cell[][] c = cells.clone();
		Board b = new Board(rp, cp, isRowsTurn, currentRow, currentCol, colLabels, rowLabels, c, messageLabel);
		for(int i=0; i<cells.length; i++) {
	        for(int j=0; j<cells[i].length; j++) {
	            c[i][j].setBoard(b);
	        }
	    }
		rp.setBoard(b);
		cp.setBoard(b);
		return b;
	}
	
//Added to Cell: 
	public Cell(int val, boolean sel, int r, int c) {
		value = val;
		selected = sel;
		row = r;
		col = c;
	}
	public void setBoard(Board b) {
		brd = b;
	}
	public Cell clone() {
		return new Cell(value, selected, row, col);
	}
	
//Added to Player: 
	public Player(String n, int s, boolean comp, JLabel sl) {
		name = n;
		score = s;
		isComputer = comp;
		scoreLabel = sl;
	}
	public Player clone() {
		return new Player(name, score, isComputer, scoreLabel);
	}