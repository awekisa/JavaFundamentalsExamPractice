package exam05_4October2015;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/22/2016.
 */
public class pr_01_DragonSharp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        sc.nextLine();
        String line = new String();
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("([a-z]+)\\s([a-z]+)?\\s?\\(?([\\d<>=]+)?\\)?\\s([a-z]+)\\s(\\d+)?\\s?([a-z]+)?\\s?(\".+\")\\s*?(;)");
        Matcher m = pattern.matcher(line);
        int count = 1;
        for (int i = 0; i < number; i++) {
            line = sc.nextLine();
            if (m.find()) {
                if (m.group(args[args.length - 1]).equals(";") && m.group(args[args.length - 2]).toCharArray()[0] == '"' && m.group(args[args.length - 2]).toCharArray()[m.group(args[args.length - 2]).length()] == '"'){

                }
                else{
                    System.out.printf("Compile time error @ line %d%n", count);
                    break;
                }
            }
        }
    }
}
