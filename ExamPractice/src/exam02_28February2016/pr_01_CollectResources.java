package exam02_28February2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/9/2016.
 */
public class pr_01_CollectResources {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int paths = sc.nextInt();
        int resources = 0;
        int tempScore = 0;
        List<int[]> path = new ArrayList<>();
        for (int i = 0; i < paths; i++) {
            int[] line = new int[2];
            line[0] = sc.nextInt();
            line[1] = sc.nextInt();
            path.add(line);
        }
        String[] resourceField = input.split(" ");
        Pattern pattern = Pattern.compile("([a-z]+)_(\\d+)");
        HashMap<Integer, String> collected = new HashMap<>();
        for (int i = 0; i < paths; i++) {
            int start = path.get(i)[0];
            int step = path.get(i)[1];
            boolean end = true;
            if (step >= resourceField.length) {
                step %= resourceField.length;
            }
            if (step + start >= resourceField.length) {
                start = (start + step) % resourceField.length;
            }
            while (end) {
                for (int j = start; j < resourceField.length; j += step) {
                    if (j + step >= resourceField.length) {
                        start = (j + step) % resourceField.length;
                    }
                    Matcher m = pattern.matcher(resourceField[j]);
                    if (collected.containsKey(j)) {
                        end = false;
                        collected.clear();
                        break;
                    }
                    if (m.find()) {
                        if (resourceFound(m.group(1))) { // switch method here
                            tempScore += Integer.parseInt(m.group(2));
                            collected.put(j, "collected");
                        }
                    } else if (resourceFound(resourceField[j])) {
                        tempScore += 1;
                        collected.put(j, "collected");
                    }
                }
            }
            if (tempScore > resources) {
                resources = tempScore;
                tempScore = 0;
            }
            else{
                tempScore = 0;
            }
        }
        System.out.println(resources);
        int i = 0;
    }

    public static boolean resourceFound(String found) {
        switch (found) {
            case "stone":
                return true;

            case "gold":
                return true;

            case "wood":
                return true;

            case "food":
                return true;

            default:
                return false;

        }

    }
}
