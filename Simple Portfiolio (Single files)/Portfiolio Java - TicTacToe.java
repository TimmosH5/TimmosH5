import java.util.Scanner;

public class TicTacToe {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
            System.out.println("\nLet's play tic tac toe");

            String[][] board = {{"_", "_", "_"}, {"_", "_", "_"}, {"_", "_", "_"}}; 


            String move = " ";

            for (int t = 0; t < 10; t++) {
              if (t % 2 == 0) {
                move = "X";
                } else {
                move = "O";
                }
            System.out.println("\nTurn number: " + t + ", move: " + move);
            printBoard(board);

            if (t == 9) {
              System.out.println("Its a tie!");
              break;
            }

            if (move.equals("X")) {
              System.out.println("Choose row (1-3) and column (1-3) for your move.");
              askUserX(board);
              checkWin(board);
              if (checkWin(board) == 3) {
                printBoard(board);
                System.out.println("X wins!");
                break;
              }

            }

            if (move.equals("O")) {
              System.out.println("Choose row (1-3) and column (1-3) for your move.");
              askUserO(board);
              checkWin(board);
              if (checkWin(board) == -3) {
                printBoard(board);
                System.out.println("O wins!");
                break;
              }

            }

            }

            scan.close();

          }

        public static void askUserX(String[][] board) {
          int positionRow = scan.nextInt()- 1;
          int positionColumn = scan.nextInt() -1;
              while (!board[positionRow][positionColumn].equals("_")) {
                System.out.println("Invalid move - try again");
                positionRow = scan.nextInt() - 1;
                positionColumn = scan.nextInt() - 1;
              }
              if (board[positionRow][positionColumn] == "_") {
                board[positionRow][positionColumn] = "X";
              }
            }
      
        public static void askUserO(String[][] board) {
          int positionRow = scan.nextInt()- 1;
          int positionColumn = scan.nextInt() -1;
              while (!board[positionRow][positionColumn].equals("_")) {
                System.out.println("Invalid move - try again");
                positionRow = scan.nextInt() - 1;
                positionColumn = scan.nextInt() - 1;
              }
              if (board[positionRow][positionColumn] == "_") {
                board[positionRow][positionColumn] = "O";
              }
            }


        public static void printBoard(String[][] board) {
          for (int i = 0; i < board.length; i++) {
          System.err.println(" ");
          System.out.print("\t" + board[i][0] + " ");
          System.out.print("\t" + board[i][1] + " ");
          System.out.print("\t" + board[i][2] + " ");
          System.err.println("\n");
        }
        }

        public static int checkWin(String[][] board) {
          checkRows(board);
          if (checkRows(board) == 3) {
            return 3;
          } if (checkRows(board) == -3) {
            return -3;
          }
          checkColumns(board);
          if (checkColumns(board) == 3) {
            return 3;
          } if (checkColumns(board) == -3) {
            return -3;
          }
          checkDiagonal_L(board);
          if (checkDiagonal_L(board) == 3) {
            return 3;
          } if (checkDiagonal_L(board) == -3) {
            return -3;
          }
          checkDiagonal_R(board);
          if (checkDiagonal_R(board) == 3) {
            return 3;
          } if (checkDiagonal_R(board) == -3) {
            return -3;
          }
          return 0;

    }

        public static int checkRows(String[][] board) {
          int count = 0;
            for (int i = 0; i < board.length; i++) {
              count = 0;
              for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("X")) {
                  count = count + 1;
                } else if (board[i][j].equals("O")) {
                  count = count - 1;
                } if (count == 3 || count == -3) {
                  return count;
                }
              }
            }
            return 0;
        }
       
// here the loop checks over every column [.][i] with the nested loop [j][i] - remember [row][column]
// so the outer loop locks the row first, so that the inner loop can check the column up and down - [0][1-2-3j]
// if no result, it moves to the next column [1][1-2-3j]
// every time the inner  j has no result in the column, the start of the outer loop resets the wincheck counter to 0.
// but in the case the column counter is 3 OR - 3, the functions stops and returns a win counter directly to store in the function.
// if everything is checked in every column, the standard functionality (in case there's no winner) is to return 0 anyway.
// in this way, the columns dont provide a winner and can the checking of wins continue in other functions (diagonals/rows). 

        public static int checkColumns(String[][] board) {
          int count = 0;
            for (int i = 0; i < board[0].length; i++) {
              count = 0; 
              for (int j = 0; j < board.length; j++) {
                if (board[j][i].equals("X")) {
                  count = count + 1;
                } else if (board[j][i].equals("O")) {
                  count = count - 1;
                } 
              }
              if (count == 3 || count == -3) {
                return count;
              }
            }
          return 0;
        }

          public static int checkDiagonal_L(String[][] board) {
            int count = 0;
              for (int i = 0; i < board.length; i++) {
                  if (board[i][i].equals("X")) {
                    count = count + 1;
                  } else if (board[i][i].equals("O")) {
                    count = count - 1;
                  } if (count == 3 || count == -3) {
                    return count;
                  }
                }
              return 0;
            }
   
            public static int checkDiagonal_R(String[][] board) {
              int count = 0;
                for (int i = 0; i < board.length; i++) {
                    if (board[i][2-i].equals("X")) {
                      count = count + 1;
                    } else if (board[i][2-i].equals("O")) {
                      count = count - 1;
                    } if (count == 3 || count == -3) {
                      return count;
                    }
                  }
                return 0;
              }
     
 

}
