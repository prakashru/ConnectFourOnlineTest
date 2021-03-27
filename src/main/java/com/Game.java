package com;

import java.util.Scanner;

public class Game {

	public static void main(String args[]) {
		int rows, cols = 0;
		String fPlayerName;
		String sPlayerName;
		String fPlayerSym, sPlayerSym;
		Scanner sScanner = new Scanner(System.in);
		System.out.println("Enter the Size of the board in below:");
		System.out.println("No of rows required:");
		rows = sScanner.nextInt();
		System.out.println("No of Columns required:");
		cols = sScanner.nextInt();
		char[][] board = new char[rows][cols];

		System.out.println("Enter player Name in below->");
		System.out.print("First Player name:");
		fPlayerName = sScanner.next();
		System.out.print("Second Player Name:");
		sPlayerName = sScanner.next();
		System.out.println(fPlayerName + " is representing as:" + fPlayerName.charAt(0));
		System.out.println(sPlayerName + " is representing as:" + sPlayerName.charAt(0));
		System.out.println("Game board is ready to play.");
		// Initializing empty board at the beginning

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				board[i][j] = ' ';
				System.out.print(".");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		System.out.println("Board is ready to Play.");
		// Display the initial board
		printGameBoard(rows, cols, board);
		boolean winnerTurn = true;
		int position = 0;
		char f_Player = fPlayerName.charAt(0);
		char s_Player = sPlayerName.charAt(0);
		while (winnerTurn) {
			System.out.println("Player 1 " + fPlayerName + " - choose column (1-7): ");
			position = sScanner.nextInt();
			int curPosA = insertDisc(rows, position, board, f_Player);
			printGameBoard(rows, cols, board);

//			boolean isWinnerV = checkVertical(curPos, position - 1, board, f_Player);
//			if (isWinnerV) {
//				System.out.println("Vertical test ver win.");
//				break;
//			}
//			boolean isWinnerHR = checkHorizontalRightSide(curPos, position - 1, board, f_Player);
//			if (isWinnerHR) {
//				System.out.println("Vertical test right win.");
//				break;
//			}
//			
//			boolean isWinnerHL = checkHorizontalLeftSide(curPos, position - 1, board, f_Player);
//			if (isWinnerHL) {
//				System.out.println("Vertical test left win.");
//				break;
//			}
			// if(checkDiagonalUp(curPos, position - 1, board, f_Player);
			if (checkVertical(curPosA, position - 1, board, f_Player)
					|| checkHorizontalRightSide(curPosA, position - 1, board, f_Player)
					|| checkHorizontalLeftSide(curPosA, position - 1, board, f_Player)
					|| checkDiagonalUp(curPosA, position - 1, board, f_Player)) {
				System.out.println("Player [" + fPlayerName + "] Win the Game.");
				break;
			}

			System.out.println("Player 2 " + sPlayerName + " - choose column (1-7): ");
			position = sScanner.nextInt();
			int curPosB = insertDisc(rows, position, board, s_Player);
			printGameBoard(rows, cols, board);

			if (checkVertical(curPosB, position - 1, board, s_Player)
					|| checkHorizontalRightSide(curPosB, position - 1, board, s_Player)
					|| checkHorizontalLeftSide(curPosB, position - 1, board, s_Player)
					|| checkDiagonalUp(curPosB, position - 1, board, s_Player)) {
				System.out.println("Player [" + sPlayerName + "] Win the Game.");
				break;
			}
		}

	}

	private static boolean checkDiagonalUp(int rows, int pos, char[][] board, char sym) {
		int count = 0;
		int i = rows;
		int j = pos;
		// Downside
		while (i < (board[0].length - 1) && j >= 0 && board[i][j] == sym) {
			count++;
			i++;
			j--;
			if (j < 0)
				break;
		}
		// Upside
		i = rows - 1;
		j = pos + 1;
		while (i >= 0 && j < board.length && board[i][j] == sym) {
			count++;
			i--;
			j++;
		}
		if (count == 3)
			return true;

		return false;
	}

	private static int insertDisc(int rows, int pos, char[][] board, char sym) {

		int curPosRow = 0;
		for (int j = rows - 1; j >= 0; j--) {
			if (board[j][pos - 1] == ' ') {
				board[j][pos - 1] = sym;
				curPosRow = j;
				break;
			}

		}
		return curPosRow;
		// checkHorizontal();
		// checkDiagonal();

	}

	private static boolean checkHorizontalLeftSide(int curPos, int position, char[][] board, char sym) {
		boolean isWin = false;
		for (int i = 1;i<=3; i++) {
			if (board[curPos][position] != ' ' && board[curPos][position] == sym) {
				isWin = true;
				if (i == 3) {
					break;
				}
				position = position - 1;
				if (position < 0) {
					isWin = false;
					break;
				}

			} else {
				isWin = false;
			}
		}
		return isWin;
	}

	private static boolean checkHorizontalRightSide(int curPos, int position, char[][] board, char sym) {
		boolean isWin = false;
		for (int i = 1; i <= 3; i++) {
			if (board[curPos][position] != ' ' && board[curPos][position] == sym) {
				isWin = true;
				if (i == 3) {
					break;
				}
				position = position + 1;
				if (position >= board[0].length) {
					isWin = false;
					break;
				}

			} else {
				isWin = false;
				break;
			}
		}

		return isWin;

	}

	private static boolean checkVertical(int curPos, int position, char[][] board, char sym) {
		boolean isWin = false;

		for (int i = 1;i<=3; i++) {
			if (board[curPos][position] != ' ' && board[curPos][position] == sym) {
				isWin = true;
				if (i == 3)
					break;
				curPos = curPos + 1;
				if (curPos >= board.length) {
					isWin = false;
					break;
				}
			} else {
				isWin = false;
				break;
			}
		}

		return isWin;
	}

	private static void printGameBoard(int rows, int cols, char[][] board) {
		for (int i = 0; i < rows; i++) {
			System.out.print("|");
			for (int j = 0; j < cols; j++) {
				System.out.print(board[i][j]);
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println();
	}

}
