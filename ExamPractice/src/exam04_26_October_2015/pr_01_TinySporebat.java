package exam04_26_October_2015;

import java.util.Scanner;

/**
 * Created by xxx on 4/18/2016.
 */
public class pr_01_TinySporebat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int health = 5800;
        int sporeBat = 30;
        int glowcaps = 0;
        int lifebloom = 200;
        boolean alive = true;
        String line = new String();

        while (true) {
            line = sc.nextLine();
            if (!line.equals("Sporeggar") && alive) {
                for (int i = 0; i < line.length() - 1; i++) {
                    if (line.toCharArray()[i] == 'L' && alive) {                // lifebloom found
                        health += lifebloom;
                    } else if (health - line.toCharArray()[i] > 0) {   // pass enemy
                        health -= line.toCharArray()[i];
                    }
                    else{
                        alive = false;
                        System.out.printf("Died. Glowcaps: %d", glowcaps);
                        break;
                    }
                }
                glowcaps += Character.getNumericValue(line.toCharArray()[line.toCharArray().length - 1]);
            } else {                                                           // end
                break;
            }
        }
        if (alive) {
            System.out.printf("Health left: %d%n", health);
            if (glowcaps >= sporeBat) {
                System.out.printf("Bought the sporebat. Glowcaps left: %d", glowcaps - sporeBat);
            } else {
                System.out.printf("Safe in Sporeggar, but another %d Glowcaps needed.", sporeBat - glowcaps);
            }
        }
    }
}
