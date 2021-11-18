package com.chess.engine.player;

import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.Move;

public class MoveTransition 
{
	@SuppressWarnings("unused")
	private final Board transitionBoard;
	@SuppressWarnings("unused")
	private final Move move;
	@SuppressWarnings("unused")
	private final MoveStatus moveStatus;
	
	public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus)
	{
		this.transitionBoard = transitionBoard;
		this.move = move;
		this.moveStatus = moveStatus;
	}
	
	public MoveStatus getMoveStatus()
	{
		return this.moveStatus;
	}
}
