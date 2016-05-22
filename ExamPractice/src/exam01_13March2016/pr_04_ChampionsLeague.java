package exam01_13March2016;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxx on 4/5/2016.
 */
public class pr_04_ChampionsLeague {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = new String();
        TreeMap<String, Integer> teamsWins = new TreeMap<>();
        TreeMap<String, List<String>> teamOpps = new TreeMap<>();
        int win = 0;
        Pattern pattern = Pattern.compile("([\\w\\s]+)\\s+\\|\\s+([\\w\\s]+)\\s+\\|\\s+(\\d+):(\\d+).+(\\d+):(\\d+)");
        Matcher m = pattern.matcher("");
        while (!(line = sc.nextLine()).equals("stop")) {
            m.reset(line);
            if (m.find()) {
                if (teamOpps.containsKey(m.group(1))) {
                    teamOpps.get(m.group(1)).add(m.group(2));
                } else { // no team
                    teamOpps.put(m.group(1), new ArrayList<>());
                    teamOpps.get(m.group(1)).add(m.group(2));
                }
                if (teamOpps.containsKey(m.group(2))) {
                    teamOpps.get(m.group(2)).add(m.group(1));
                } else { // no team
                    teamOpps.put(m.group(2), new ArrayList<>());
                    teamOpps.get(m.group(2)).add(m.group(1));
                }


                if (firstWin(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)))) {
                    if (teamsWins.containsKey(m.group(1))) {
                        win = teamsWins.get(m.group(1)).intValue();
                        teamsWins.put(m.group(1), win + 1);
                    } else {
                        teamsWins.put(m.group(1), 1);
                    }
                } else { // first team loses
                    if (teamsWins.containsKey((m.group(2)))) {
                        win = teamsWins.get(m.group(2)).intValue();
                        teamsWins.put(m.group(2), win + 1);
                    } else {
                        teamsWins.put(m.group(2), 1);
                    }

                }

            }
        }
        for (Map.Entry key : teamOpps.entrySet()) {
            boolean notFound = true;
            for (Map.Entry winner : teamsWins.entrySet()) {

                if (key.getKey().equals(winner.getKey())) {
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                teamsWins.put(key.getKey().toString(), 0);
            }
        }
        StringBuilder sb = new StringBuilder();

        teamsWins.entrySet().stream().sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue())).forEach(entry -> {
            sb.append(entry.getKey() + "\r\n");
            sb.append("- Wins: " + entry.getValue() + "\r\n");
            sb.append("- Opponents: ");

            String team = entry.getKey();
            teamOpps.entrySet().stream().forEach(entryOpps -> {
                if (entryOpps.getKey().equals(team)) {
                    entryOpps.getValue().stream().sorted((o1, o2) -> o1.compareTo(o2) ).forEach(opp -> {
                        sb.append(opp + ", ");
                    });
                    sb.replace(sb.length()-2, sb.length(), "\r\n");
                }
            });
        });
        String result = sb.toString();
        System.out.println(result);
    }


    private static List<String> Losers(TreeMap<String, Integer> teamsWins, TreeMap<String, List<String>> teamOpps) {
        List<String> losers = new ArrayList<>();
        for (String key : teamOpps.keySet()) {
            boolean loser = true;
            for (String winer : teamsWins.keySet()) {

                if (key.equals(winer)) {
                    loser = false;
                    break;
                }
            }
            if (loser) {
                losers.add(key);
            }
        }
        return losers;
    }

    public static boolean firstWin(int firstHomeGoals, int secondAwayGoals, int secondHomeGoals, int firstAwayGoals) {
        boolean win = false;
        if (firstHomeGoals + firstAwayGoals > secondAwayGoals + secondHomeGoals) {
            win = true;
        } else if (firstHomeGoals + firstAwayGoals == secondAwayGoals + secondHomeGoals) {
            if (firstAwayGoals > secondAwayGoals) {
                win = true;
            }
        } else { // lose
            win = false;
        }

        return win;
    }
}

