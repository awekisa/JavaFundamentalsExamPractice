package exam01_13March2016;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/4/2016.
 */
public class pr_03_BasicMarkUpLanguage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = new String();
        List<String> collectLines = new ArrayList<>();
        Pattern pattern = Pattern.compile("(inverse|reverse|repeat).+?\"(.+?)\"");
        int count = 0;
        int countLines = 0;
        while (true) {
            if (!(line = sc.nextLine()).equals("<stop/>")) {
//              line = line.replaceAll("\\s+","");
                if (!line.equals("")) {
                    collectLines.add(line);
                }
                else{
                    continue;
                }

                Matcher m = pattern.matcher(collectLines.get(countLines));
                if (m.find()) {
                    if (m.group(1).equals("inverse")) {
                        StringBuilder sb = new StringBuilder();
                        char[] word = m.group(2).toCharArray();
                        for (Character ch : word) {
                            if (ch.toString().matches("[\\s\\d]")){
                                sb.append(ch);
                            }
                            else if (Character.isUpperCase(ch)) {
                                sb.append(Character.toLowerCase(ch));
                            } else if (Character.isLowerCase(ch)) { // lowerCase
                                sb.append(Character.toUpperCase(ch));
                            }
                        }
                        count++;
                        System.out.printf("%d. %s%n", count, sb.toString());

                    } else if (m.group(1).equals("reverse")) {
                        char[] word = m.group(2).toCharArray();
                        char[] reversed = new StringBuilder(new String(word)).reverse().toString().toCharArray();
                        count++;
                        System.out.printf("%d. %s%n", count, String.valueOf(reversed));

                    } else if (m.group(1).equals("repeat")) {
                        Pattern p = Pattern.compile("(\\d+).+?\"(.+)\"");
                        Matcher match = p.matcher(collectLines.get(countLines));
                        if (match.find()) {
                            for (int i = 0; i < Integer.parseInt(match.group(1)); i++) {
                                count++;
                                System.out.printf("%d. %s%n", count, match.group(2));
                            }
                        }
                    }
                }
                countLines++;
            } else {
                break;
            }
        }
        int i = 1;
    }
}