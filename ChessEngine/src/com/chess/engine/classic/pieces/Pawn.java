package com.chess.engine.classic.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.BoardUtils;
import com.chess.engine.classic.board.Move;
import com.google.common.collect.ImmutableList;
import static com.chess.engine.classic.board.Move.*;

public class Pawn extends Piece
{
	private final static int[] CANDIDATE_MOVE_COORDINATE = { 8, 16 };
	
	public Pawn(final Alliance pieceAllianace, final int piecePosition) 
	{
		super(piecePosition, pieceAllianace);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) 
	{
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE)
		{
			final int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset ) ;
			
			if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				continue;
			}
			
			if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
			{
				//TODO more work to do here (deal with promotions)!!!
				legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
			} 
			else if(currentCandidateOffset == 16 && this.isFirstMove() && 
					(BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack() ) || 
					(BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite() ) ) 
			{
				final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8 );
				if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && 
						!board.getTile(candidateDestinationCoordinate).isTileOccupied()) 
				{
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				}
			}
			else if(currentCandidateOffset == 7 && 
					!(  ( BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ) ||
							( BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()  )  )  )
			{
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied())
				{
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					
					if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
					{
						//TODO more to do here
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					}
				}
			}
			else if(currentCandidateOffset == 9 &&
					!(  ( BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack() ) ||
						    ( BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()  )  )  )
			{
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied())
				{
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					
					if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
					{
						//TODO more to do here
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString() { return PieceType.PAWN.toString(); }
}
