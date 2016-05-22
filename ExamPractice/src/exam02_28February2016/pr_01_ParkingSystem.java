package exam02_28February2016;

import java.util.*;

/**
 * Created by xxx on 4/10/2016.
 */
public class pr_01_ParkingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[][] parking = new boolean[sc.nextInt()][sc.nextInt() - 1];
        sc.nextLine();

        String line = new String();
        int count = 0;
        boolean fullRow;
        int increase = 1;

        while (true) {   // more lines available
            line = sc.nextLine();
            if (!line.equals("stop")) {
                String[] data = line.split(" ");
                int startRow = Integer.parseInt(data[0]);
                int row = Integer.parseInt(data[1]);
                int col = Integer.parseInt(data[2]);
                if (startRow < row) {
                    for (int i = startRow; i <= row; i++) {   // counting rows
                        count++;
                    }
                } else if (startRow > row) {
                    for (int i = startRow; i >= row; i--) {
                        count++;
                    }
                } else {
                    count = 1;
                }
                if (parking[row][col - 1] == false) {   // wanted spot is free
                    parking[row][col - 1] = true;
                    count += col;
                    System.out.printf("%d%n", count);
                    count = 0;
                    increase = 1;
                } else {                            // wanted spot taken
                    if (freeSpotsAvailable(parking, row, col)) { // check if row have free spots
                        fullRow = false;
                    } else {
                        fullRow = true;
                    }

                    if (fullRow == false) {             // row have free spots

                        while (fullRow != true) {   // filling free spots
                            if (col - increase > 0) {   // not at first col
                                if (parking[row][col - 1 - increase] == false) {  // free spot to the left
                                    parking[row][col - 1 - increase] = true;
                                    count += col - increase;
                                    System.out.printf("%d%n", count);
                                    fullRow = !freeSpotsAvailable(parking, row, col);
                                    count = 0;
                                    break;
                                } else if (col - 1 + increase < parking[row].length) { // not at last col

                                    if (parking[row][col - 1 + increase] == false) { // free spot to the right
                                        parking[row][col - 1 + increase] = true;
                                        count += col + increase;
                                        System.out.printf("%d%n", count);
                                        fullRow = !freeSpotsAvailable(parking, row, col);
                                        count = 0;
                                        increase++;
                                        break;
                                    } else {             // no free spot to the right
                                        increase++;
                                    }
                                } else{   // at last column
                                    if (col - 1 - increase >= 0){ // not outOfRange
                                        if (parking[row][col - 1 - increase] == false){
                                            parking[row][col - 1 - increase] = true;
                                            count += col - increase;
                                            System.out.printf("%d%n", count);
                                            fullRow = !freeSpotsAvailable(parking, row, col);
                                            count = 0;
                                            increase++;
                                        }
                                        else{
                                            increase++;
                                        }

                                    }
                                }
                            } else { // at first col
                                if (col - 1 + increase < parking[row].length) { // not at last col
                                    if (parking[row][col - 1 + increase] == false) { // free spot to the right
                                        parking[row][col - 1 + increase] = true;
                                        count += col + increase;
                                        System.out.printf("%d%n", count);
                                        fullRow = !freeSpotsAvailable(parking, row, col);
                                        count = 0;
                                        increase++;
                                        break;
                                    } else {             // no free spot to the right
                                        increase++;
                                    }
                                }
                            }
                        }
                    } else {                              //  row full
                        System.out.printf("Row %d full%n", row);
                        count = 0;
                        increase = 1;
                    }
                }

            } else {           // no more lines
                break;
            }
        }
    }

    public static boolean freeSpotsAvailable(boolean[][] parking, int row, int col) {
        for (int i = 0; i < parking[row].length; i++) { // check if row have free spots
            if (parking[row][i] == false) {
                return true;
            }
        }
        return false;
    }
}




