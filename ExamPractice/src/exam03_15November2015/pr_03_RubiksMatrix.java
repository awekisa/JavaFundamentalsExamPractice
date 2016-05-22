package exam03_15November2015;

import java.util.Scanner;

/**
 * Created by xxx on 4/16/2016.
 */
public class pr_03_RubiksMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int commands = sc.nextInt();
        int count = 1;
        sc.nextLine();
        String[] line = new String[3];
        int[][] matrix = new int[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                matrix[r][c] = count;
                count++;
            }
        }
        int index = 0;
        int moves = 0;

        for (int i = 0; i < commands; i++) {
            line = sc.nextLine().split(" ");
            index = Integer.parseInt(line[0]);

            if (line[1].equals("up") || line[1].equals("down")) {
                moves = Integer.parseInt(line[2]);
                if (moves > row) {
                    moves %= row;
                    matrix = colUpdate(matrix, row, col, index, moves, line[1]); // update rows
                } else {
                    matrix = colUpdate(matrix, row, col, index, moves, line[1]); // update rows
                }
            } else { // left or right
                moves = Integer.parseInt(line[2]);
                if (moves > col) {
                    moves %= col;
                    matrix = rowUpdate(matrix, row, col, index, moves, line[1]); // update cols
                } else {
                    matrix = rowUpdate(matrix, row, col, index, moves, line[1]); // update cols
                }
            }

        }
        count = 1;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == count){    // match
                    System.out.println("No swap required");
                    count++;
                }
                else{   // no match
                    for (int r1 = 0; r1 < row; r1++) {
                        for (int c1 = 0; c1 < col; c1++) {
                            if (count == matrix[r1][c1]){
                                int temp = matrix[r][c];
                                matrix[r][c] = matrix[r1][c1];
                                matrix[r1][c1] = temp;
                                System.out.printf("Swap (%d, %d) with (%d, %d)%n", r, c, r1, c1);
                            }
                        }
                    }
                    count++;
                }
            }
        }
    }

    private static int[][] rowUpdate(int[][] matrix, int row, int col, int index, int moves, String direction) {
        int temp = 0;
        if (direction.equals("right")) {
            for (int move = 0; move < moves; move++) {
                temp = matrix[index][col - 1];
                for (int i = col - 1; i > 0; i--) {
                    matrix[index][i] = matrix[index][i - 1];
                }
                matrix[index][0] = temp;
            }
        } else {  // left
            for (int move = 0; move < moves; move++) {
                temp = matrix[index][0];
                for (int i = 0; i < col - 1; i++) {
                    matrix[index][i] = matrix[index][i + 1];
                }
                matrix[index][col - 1] = temp;
            }
        }

        return matrix;
    }

    private static int[][] colUpdate(int[][] matrix, int row, int col, int index, int moves, String direction) {
        int temp = 0;
        if (direction.equals("up")){
            for (int move = 0; move < moves; move++) {
                temp = matrix[0][index];
                for (int i = 0; i < row - 1; i++) {
                    matrix[i][index] = matrix[i+1][index];
                }
                matrix[row-1][index] = temp;
            }
        }
        else{ // down
            for (int move = 0; move < moves; move++) {
                temp = matrix[row-1][index];
                for (int i = row-1; i > 0; i--) {
                    matrix[i][index] = matrix[i-1][index];
                }
                matrix[0][index] = temp;
            }
        }
        return matrix;
    }
}
