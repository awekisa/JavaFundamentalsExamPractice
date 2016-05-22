package exam01_13March2016;

import java.util.*;


public class pr_01_ArrangeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(", ");
        ArrayList<String> value = new ArrayList<>();
        int count = 0;
        TreeMap<String, TreeMap<String, ArrayList<String>>> result = new TreeMap<>();
        StringJoiner joinValue = new StringJoiner("");
        StringJoiner joinkeyEntry = new StringJoiner("");
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                switch (input[i].charAt(j)) {
                    case '0':
                        joinkeyEntry.add("zero");
                        joinValue.add("0");
                        break;
                    case '1':
                        joinkeyEntry.add("one");
                        joinValue.add("1");
                        break;
                    case '2':
                        joinkeyEntry.add("two");
                        joinValue.add("2");
                        break;
                    case '3':
                        joinkeyEntry.add("three");
                        joinValue.add("3");
                        break;
                    case '4':
                        joinkeyEntry.add("four");
                        joinValue.add("4");
                        break;
                    case '5':
                        joinkeyEntry.add("five");
                        joinValue.add("5");
                        break;
                    case '6':
                        joinkeyEntry.add("six");
                        joinValue.add("6");
                        break;
                    case '7':
                        joinkeyEntry.add("seven");
                        joinValue.add("7");
                        break;
                    case '8':
                        joinkeyEntry.add("eight");
                        joinValue.add("8");
                        break;
                    case '9':
                        joinkeyEntry.add("nine");
                        joinValue.add("9");
                        break;
                    default:
                        break;
                }


            }
            if (result.containsKey(joinkeyEntry.toString())) {

                result.get(joinkeyEntry.toString()).get(joinkeyEntry.toString()).add(joinValue.toString());
            } else {
                result.put(joinkeyEntry.toString(), new TreeMap<>());
                result.get(joinkeyEntry.toString()).put(joinkeyEntry.toString(), new ArrayList<>());
                result.get(joinkeyEntry.toString()).get(joinkeyEntry.toString()).add(joinValue.toString());
            }
            joinValue = new StringJoiner("");
            joinkeyEntry = new StringJoiner("");
        }
        StringBuilder sb = new StringBuilder();
        result.entrySet().stream().forEach(map -> {
            map.getValue().entrySet().stream().forEach(list -> {
                list.getValue().stream().forEach(number -> {
                    sb.append(number + ", ");
                });

            });
        });
        System.out.println(sb.substring(0, sb.length() - 2));
        int i = 1;

    }
}
