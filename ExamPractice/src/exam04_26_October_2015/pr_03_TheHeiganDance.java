package exam04_26_October_2015;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xxx on 4/18/2016.
 */
public class pr_03_TheHeiganDance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double playerDamage = sc.nextDouble();
        sc.nextLine();
        int cloudDamage = 3500;
        boolean secondCloud = false;
        int eruptionDamage = 6000;
        int playerHealth = 18500;
        double heiganHealth = 3000000d;
        boolean playerAlive = true;
        boolean heiganAlive = true;
        boolean spellHit = false;
        boolean matrix[][] = new boolean[15][15];
        String line = new String();
        int playerX = 7;
        int playerY = 7;
        String killingSpell = "";
        int resultX = 0;
        int resultY = 0;

        while (true) {
            if (!heiganAlive) {   // if Heigan is dead
                break;
            }
            if (!playerAlive){
                break;
            }
            line = sc.nextLine();
            if (!line.equals("")) {
                heiganHealth -= playerDamage;
                if (heiganHealth <= 0){
                    heiganAlive = false;
                }
                if (secondCloud) {
                    playerHealth -= cloudDamage;
                    secondCloud = false;
                    if (playerHealth <= 0) {
                        playerAlive = false;
                        killingSpell = "Plague Cloud";

                    }
                }
                if (!heiganAlive) {
                    break;
                }
                if (!playerAlive){
                    break;
                }
                String[] spell = line.split(" ");
                int xSpell = Integer.parseInt(spell[1]);
                int ySpell = Integer.parseInt(spell[2]);
                if (spell[0].equals("Cloud")) {
                    matrix = cloudArea(matrix, xSpell, ySpell);
                    if (matrix[playerX][playerY]) {   // player is hit
                        if (playerX - 1 >= 0 && !matrix[playerX-1][playerY]) {  // escape up
                            playerX--;
                        } else if (playerX <= 14 && !matrix[playerX][playerY+1]) {  // escape right
                            playerY++;
                        } else if (playerX + 1 <= 14 && !matrix[playerX+1][playerY]) {  // escape down
                            playerX++;
                        } else if (playerY - 1 >= 0 && !matrix[playerX][playerY-1]) {   // escape left
                            playerY--;
                        } else {   // player hit
                            playerHealth -= cloudDamage;
                            secondCloud = true;
                            if (playerHealth <= 0){
                                playerAlive = false;
                                killingSpell = "Plague Cloud";
                                resultX = playerX;
                                resultY = playerY;
                            }
                        }
                    }
                    Arrays.stream(matrix).forEach(row -> {
                        Arrays.fill(row, false);
                    });
                } else {// erruption
                    matrix = cloudArea(matrix, xSpell, ySpell);
                    if (matrix[playerX][playerY]) {   // player is hit
                        if (playerX - 1 >= 0 && !matrix[playerX-1][playerY]) {  // escape up
                            playerX--;
                        } else if (playerY + 1 <= 14 && !matrix[playerX][playerY+1]) {  // escape right
                            playerY++;
                        } else if (playerX + 1 <= 14 && !matrix[playerX+1][playerY]) {  // escape down
                            playerX++;
                        } else if (playerY - 1 >= 0 && !matrix[playerX][playerY-1]) {   // escape left
                            playerY--;
                        } else {   // player hit
                            playerHealth -= eruptionDamage;
                            if (playerHealth <= 0){
                                playerAlive = false;
                                killingSpell = "Eruption";
                                resultX = playerX;
                                resultY = playerY;
                            }
                        }
                    }
                    Arrays.stream(matrix).forEach(row -> {
                        Arrays.fill(row, false);
                    });
                }
            } else {
                break;
            }
            resultX = playerX;
            resultY = playerY;
        }
        if (heiganAlive){
            System.out.printf("Heigan: %.2f%n", heiganHealth);
        }
        else{
            System.out.printf("Heigan: Defeated!%n");
        }
        if (playerAlive){
            System.out.printf("Player: %d%n", playerHealth);
        }
        else{
            System.out.printf("Player: Killed by %s%n", killingSpell);
        }
        System.out.printf("Final position: %d, %d", resultX, resultY);
        int i = 0;
    }

    private static boolean[][] cloudArea(boolean[][] matrix, int x, int y) {
        for (int xC = x - 1; xC <= x + 1; xC++) {
            for (int yC = y - 1; yC <= y + 1; yC++) {
                if (xC >= 0 && xC < 15 && yC >= 0 && yC < 15) {
                    matrix[xC][yC] = true;
                }
            }
        }
        return matrix;
    }
}
