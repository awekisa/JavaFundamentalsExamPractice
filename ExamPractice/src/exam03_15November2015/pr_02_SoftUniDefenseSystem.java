package exam03_15November2015;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/16/2016.
 */
public class pr_02_SoftUniDefenseSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = new String();
        double liters = 0d;
        Pattern pattern = Pattern.compile("([A-Z][a-z]+).*?([A-Z][a-z]*[A-Z]).*?(\\d+L)");
        while (true) {
            line = sc.nextLine();
            if (!line.equals("OK KoftiShans")) {
                Matcher m = pattern.matcher(line);
                    while(m.find()) {
                        System.out.println(m.group(1) + " brought " + (m.group(3).substring(0, m.group(3).length() - 1) + " liters of " + m.group(2).toLowerCase() + "!"));
                        liters += Double.parseDouble(m.group(3).substring(0, m.group(3).length() - 1));
                    }
            } else {
                break;
            }
        }
        System.out.printf("%.3f softuni liters", liters / 1000);
    }
}
