package com.chess.engine.classic.pieces;

import java.util.Collection;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.Board;

public abstract class Piece 
{
	
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	
	Piece(final int piecePosition, final Alliance pieceAllianace)
	{
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAllianace;
		//TODO more work here!!!
		this.isFirstMove = false;
	}
	
	public Integer getPiecePosition() { return this.piecePosition; }
	
	public Alliance getPieceAlliance() { return this.pieceAlliance; }
	
	public boolean isFirstMove() { return this.isFirstMove; }
	
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	public enum PieceType 
	{
		PAWN("P"),
		KNIGHT("K"),
		BISHOP("B"),
		ROOK("R"),
		QUEEN("Q"),
		KING("K");
		
		private String pieceName;
		
		PieceType(final String pieceName)
		{
			this.pieceName = pieceName;
		}
		
		@Override
		public String toString()
		{
			return this.pieceName;
		}
	}
}
