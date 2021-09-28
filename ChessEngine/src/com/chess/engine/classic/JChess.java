package com.chess.engine.classic;

import com.chess.engine.classic.board.Board;

public class JChess 
{
	public static void main(String[] args)
	{
		Board board = Board.createStandardBoard();
		
		System.out.println(board);
	}
}
