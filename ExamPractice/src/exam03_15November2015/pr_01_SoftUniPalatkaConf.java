package exam03_15November2015;

import java.util.Scanner;

/**
 * Created by xxx on 4/16/2016.
 */
public class pr_01_SoftUniPalatkaConf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int people = sc.nextInt();
        int rows = sc.nextInt();
        String[] data = new String[3];
        sc.nextLine();
        int beds = 0;
        int meals = 0;
        for (int r = 0; r < rows; r++) {
            String line = sc.nextLine();
            data = line.split(" ");
            switch (data[0]) {
                case "tents":
                    if (data[2].equals("normal")) beds += +Integer.parseInt(data[1]) * 2;
                    else /*firstClass*/ beds += Integer.parseInt(data[1]) * 3;
                    break;
                case "rooms":
                    if (data[2].equals("single")) beds += Integer.parseInt(data[1]);
                    else if (data[2].equals("double")) beds += Integer.parseInt(data[1]) * 2;
                    else /*triple*/ beds += Integer.parseInt(data[1]) * 3;
                    break;
                case "food":
                    if (data[2].equals("musaka")) meals += Integer.parseInt(data[1]) * 2;
                    break;
            }
        }
        if (people <= beds) {
            System.out.println("Everyone is happy and sleeping well. Beds left: " + (beds - people));
        } else {
            System.out.println("Some people are freezing cold. Beds needed: " + (people - beds));
        }
        if (people <= meals) {
            System.out.println("Nobody left hungry. Meals left: " + (meals - people));
        } else {
            System.out.println("People are starving. Meals needed: " + (people - meals));
        }
    }
}
