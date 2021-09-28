package com.chess.engine.classic.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.BoardUtils;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.Tile;
import com.google.common.collect.ImmutableList;
import static com.chess.engine.classic.board.Move.*;

public class Bishop extends Piece
{
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -7, 7, 9};
	
	public Bishop(final Alliance pieceAllianace, final int piecePosition) 
	{
		super(piecePosition, pieceAllianace);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) 
	{
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES)
		{
			int candidateDestinationCoordinate = this.piecePosition;
			
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
						isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) )
				{
					break;
				}
				
				candidateDestinationCoordinate += candidateCoordinateOffset;
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
				{
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);   
					
					if(!candidateDestinationTile.isTileOccupied()) {
						legalMoves.add( new MajorMove(board, this, candidateDestinationCoordinate) );
					}
					else
					{
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						
						if(this.pieceAlliance != pieceAlliance) { 
							legalMoves.add( new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination) ); 
						}

						break;
					}
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString() { return PieceType.BISHOP.toString(); }
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition] && ( (candidateOffset == -9) || (candidateOffset == 7) );
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && ( (candidateOffset == -7) || (candidateOffset == 9) );
	}
}






