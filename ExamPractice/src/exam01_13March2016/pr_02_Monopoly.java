package exam01_13March2016;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xxx on 4/3/2016.
 */
public class pr_02_Monopoly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine();
        List<String> path = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            path.add(sc.nextLine());
        }


        int turns = 0;
        int money = 50;
        int hotels = 0;


        for (int r = 0; r < row; r++) {
            if (r % 2 == 0) {
                for (int c = 0; c < col; c++) {
                    switch (path.get(r).charAt(c)) {
                        case 'H':
                            hotels++;
                            result.add("Bought a hotel for " + money + ". Total hotels: " + hotels + ".");
                            money = 0;
                            turns++;
                            money += hotels * 10;
                            break;
                        case 'J':
                            result.add("Gone to jail at turn " + turns + ".");
                            turns += 3;
                            money += hotels * 10 * 3;
                            break;
                        case 'F':
                            turns++;
                            money += hotels * 10;
                            break;
                        case 'S':
                            if (c + r >= money) {
                                result.add("Spent " + money + " money at the shop.");
                                money = 0;
                            } else {
                                int sum = (c + 1) * (r + 1);
                                result.add("Spent " + sum + " money at the shop.");
                                money -= sum;
                            }
                            money += hotels * 10;
                            turns++;
                            break;
                        default:
                            break;
                    }
                }
            } else {
                for (int c = col - 1; c >= 0; c--) {
                    switch (path.get(r).charAt(c)) {
                        case 'H':
                            hotels++;
                            result.add("Bought a hotel for " + money + ". Total hotels: " + hotels + ".");
                            money = 0;
                            turns++;
                            money += hotels * 10;
                            break;
                        case 'J':
                            result.add("Gone to jail at turn " + turns + ".");
                            turns += 3;
                            money += hotels * 10 * 3;
                            break;
                        case 'F':
                            turns++;
                            money += hotels * 10;
                            break;
                        case 'S':
                            if (c + r >= money) {
                                result.add("Spent " + money + " money at the shop.");
                                money = 0;
                            } else {
                                int sum = (c + 1) * (r + 1);
                                result.add("Spent " + sum + " money at the shop.");
                                money -= sum;
                            }
                            money += hotels * 10;
                            turns++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        result.add("Turns " + turns);
        result.add("Money " + money);

        for (String s : result) {
            System.out.println(s);
        }
    }
}