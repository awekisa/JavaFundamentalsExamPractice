package exam04_26_October_2015;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/18/2016.
 */
public class pr_02_WeirdScript {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = (sc.nextInt());
        sc.nextLine();
        char keyLetter = inputToKeyLetter(input);
        StringBuilder build = new StringBuilder();
        build.append(keyLetter);
        build.append(keyLetter);
        String key = build.toString();
        String line = new String();
        build.delete(0, build.length());
        Pattern pattern = Pattern.compile(key + "(.*?)" + key);
        while (true) {
            line = sc.nextLine();
            if (!line.equals("End")) {
                build.append(line);

            } else {
                break;
            }
        }
        Matcher m = pattern.matcher(build.toString());
        while (m.find()) {
            if (!m.group(1).equals("")) {
                System.out.println(m.group(1));
            }
        }
    }

    private static char inputToKeyLetter(int input) {
        input %= 52;

        char keyLetter = '\0';
        if (input >= 1 && input <= 26) { // a-z
            keyLetter = (char) (input + 96);
        } else if (input >= 27 && input <= 52) { //  A-Z
            keyLetter = (char) (input + 64 - 26);
        } else {   // input % 52 = 0
            keyLetter = (char) (52 + 64 - 26);
        }
        return keyLetter;
    }
}
