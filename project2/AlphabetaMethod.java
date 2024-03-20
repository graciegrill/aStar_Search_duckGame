	public int alphabeta(BoardNode node, int depth, int alpha, int beta, boolean minimax) {
		if (depth == 0 || node.state.gameOver()) {
			return node.state.value();
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