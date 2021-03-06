package exam04_26_October_2015;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/21/2016.
 */
public class pr_04_LegendaryFarming {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = new String();
        boolean obtained = false;
        TreeMap<String, Integer> legendary = new TreeMap<>();
        legendary.put("shards", 0);
        legendary.put("fragments", 0);
        legendary.put("motes", 0);
        TreeMap<String, Integer> junk = new TreeMap<>();
        Pattern pattern = Pattern.compile("(\\d+)\\s([\\S]+)");
        while (!obtained) {
            line = sc.nextLine();
            line = line.toLowerCase();
            if (!line.equals("")) {
                Matcher m = pattern.matcher(line);
                while (m.find() && !obtained) {
                    if (m.group(2).equals("fragments") || m.group(2).equals("shards") || m.group(2).equals("motes")) {
                        int amount = legendary.get(m.group(2));
                        legendary.put(m.group(2), Integer.parseInt(m.group(1)) + amount);
                        if (legendary.get(m.group(2)) >= 250) {
                            int total = legendary.get(m.group(2));
                            switch (m.group(2)) {
                                case "fragments":
                                    System.out.printf("%s obtained!%n", "Valanyr");
                                    legendary.put(m.group(2), total - 250);
                                    break;
                                case "shards":
                                    System.out.printf("%s obtained!%n", "Shadowmourne");
                                    legendary.put(m.group(2), total - 250);
                                    break;
                                case "motes":
                                    System.out.printf("%s obtained!%n", "Dragonwrath");
                                    legendary.put(m.group(2), total - 250);
                                    break;
                            }
                            obtained = true;
                        }
                    } else {
                        if (junk.containsKey(m.group(2))) {
                            junk.put(m.group(2), junk.get(m.group(2)) + Integer.parseInt(m.group(1)));
                        } else {
                            junk.put(m.group(2), Integer.parseInt(m.group(1)));
                        }
                    }
                }
            } else {
                break;
            }
        }
        legendary.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).forEach(entry -> {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        });
        junk.entrySet().stream().forEach(entry -> {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        });
    }
}