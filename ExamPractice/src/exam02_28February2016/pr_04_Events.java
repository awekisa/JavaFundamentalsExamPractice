package exam02_28February2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/15/2016.
 */
public class pr_04_Events {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        sc.nextLine();
        String line = new String();
        TreeMap<String, TreeMap<String, ArrayList<String>>> data = new TreeMap<>();

        Pattern pattern = Pattern.compile("(?m)^#([a-zA-Z]+):[\\s]*@([a-zA-Z]+)[\\s]*([\\d]{2}):([\\d]{2})\\b");
        for (int i = 0; i < number; i++) {
            line = sc.nextLine();
            Matcher m = pattern.matcher(line);

            if (m.find()) {
                if (availableHour(m.group(3), m.group(4))) {
                    if (data.containsKey(m.group(2)) && data.get(m.group(2)).containsKey(m.group(1))) {  // contains value and key
                        data.get(m.group(2)).get(m.group(1)).add(m.group(3) + ":" + m.group(4));
                    } else if (data.containsKey(m.group(2)) && !data.get(m.group(2)).containsKey(m.group(1))) { // cont value, no key
                        data.get(m.group(2)).put((m.group(1)), new ArrayList<String>());
                        data.get(m.group(2)).get(m.group(1)).add(m.group(3) + ":" + m.group(4));
                    } else if (!data.containsKey(m.group(2))) {  // no key
                        data.put(m.group(2), new TreeMap<String, ArrayList<String>>());
                        data.get(m.group(2)).put((m.group(1)), new ArrayList<String>());
                        data.get(m.group(2)).get(m.group(1)).add(m.group(3) + ":" + m.group(4));
                    }
                }
            }
        }

        String[] places = sc.nextLine().split(",");
        StringBuilder sb = new StringBuilder();

        Arrays.stream(places).sorted().forEach(place -> {
            if (data.containsKey(place)) {
                sb.append(place + ":\r\n");
                AtomicInteger index = new AtomicInteger(1);
                data.entrySet().stream().filter(p -> p.getKey().equals(place)).forEach(p -> {
                    p.getValue().entrySet().stream().forEach(name -> {
                        sb.append((index));
                        sb.append(". " + name.getKey() + " -> ");
                        name.getValue().stream().sorted((e1, e2) -> e1.compareTo(e2)).forEach(hour -> {
                            sb.append(hour);
                            sb.append(", ");

                        });
                        sb.delete(sb.length() - 2, sb.length());
                        sb.append("\r\n");
                        index.getAndAdd(1);
                    });
                });
            }
        });
        System.out.println(sb);


    }

    private static boolean availableHour(String hour, String minutes) {
        if ((Integer.parseInt(hour) >= 0 && Integer.parseInt(hour) <= 23) && ((Integer.parseInt(minutes) >= 0 && Integer.parseInt(minutes) <= 59))) {
            return true;
        }
        return false;
    }


}
