package exam03_15November2015;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/16/2016.
 */
public class pr_04_LogParser {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("\\s*\\{\\s*\"Project\"\\s*:\\s*\\[\"(.+)\"\\]\\s*,\\s*\"\\s*Type\\s*\"\\s*:\\s*\\[\\s*\"(.+)\"\\s*\\]\\s*,\\s*\"\\s*Message\"\\s*:\\s*\\[\\s*\"(.+)\"\\]");
        TreeMap<String, Integer> numberOfErrors = new TreeMap<>();
        HashMap<String, HashMap<String, ArrayList<String>>> messages = new HashMap<>();

        while (true) {
            String line = sc.nextLine();
            if (!line.equals("END")) {
                Matcher m = pattern.matcher(line);
                while (m.find()) {
                    if (numberOfErrors.containsKey(m.group(1))) {
                        numberOfErrors.put(m.group(1), numberOfErrors.get(m.group(1)) + 1);
                        if (messages.get(m.group(1)).containsKey(m.group(2))) {    // messages contains message
                            messages.get(m.group(1)).get(m.group(2)).add(m.group(3));
                        } else {                                                  // messages does not contain message
                            messages.get(m.group(1)).put(m.group(2), new ArrayList<String>());
                            messages.get(m.group(1)).get(m.group(2)).add(m.group(3));
                        }
                    } else {
                        numberOfErrors.put(m.group(1), 1);
                        messages.put(m.group(1), new HashMap<String, ArrayList<String>>());
                        messages.get(m.group(1)).put(m.group(2), new ArrayList<String>());
                        messages.get(m.group(1)).get(m.group(2)).add(m.group(3));
                    }
                }

            } else {
                break;
            }
        }
        for (Map.Entry<String, HashMap<String, ArrayList<String>>> firstEntry : messages.entrySet()) {
            if (!firstEntry.getValue().containsKey("Critical")){
                firstEntry.getValue().put("Critical", new ArrayList<String>());
            }
            if (!firstEntry.getValue().containsKey("Warning")){
                firstEntry.getValue().put("Warning", new ArrayList<String>());
            }
        }

        StringBuilder sb = new StringBuilder();
        numberOfErrors.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).forEach(entry -> {
            sb.append(entry.getKey() + ":\r\n");
            sb.append("Total Errors: " + entry.getValue() + "\r\n");
            messages.entrySet().stream().forEach(project -> {
                if (project.getKey().equals(entry.getKey())) {
                    project.getValue().entrySet().stream().sorted((c,r) -> c.getKey().compareTo(r.getKey())).forEach(type -> {
                        switch (type.getKey()) {
                            case "Critical":
                                sb.append("Critical: " + type.getValue().size() + "\r\n");
                                break;
                            case "Warning":
                                sb.append("Warnings: " + type.getValue().size() + "\r\n");
                                break;
                        }
                    });
                    sb.append("Critical Messages:\r\n");
                    project.getValue().entrySet().stream().forEach(type -> {
                        if (type.getKey().equals("Critical")) {
                            if (type.getValue().size() > 0) {
                                type.getValue().sort(Comparator.<String>naturalOrder());
                                type.getValue().sort((e1, e2) -> Integer.compare(e1.length(), e2.length()));
                                for (String message : type.getValue()) {
                                    sb.append("--->" + message + "\r\n");
                                }
                            } else {
                                sb.append("--->None\r\n");
                            }
                        }
                    });
                    sb.append("Warning Messages:\r\n");
                    project.getValue().entrySet().stream().forEach(type -> {
                        if (type.getKey().equals("Warning")) {
                            if (type.getValue().size() > 0) {
                                type.getValue().sort(Comparator.<String>naturalOrder());
                                type.getValue().sort((e1, e2) -> Integer.compare(e2.length(), e1.length()));
                                for (String message : type.getValue()) {
                                    sb.append("--->" + message + "\r\n");
                                }
                            } else {
                                sb.append("--->None\r\n");
                            }
                        }
                    });
                }
            });
            sb.append("\r\n");
        });
        System.out.println(sb);
        int o = 1;

    }
}