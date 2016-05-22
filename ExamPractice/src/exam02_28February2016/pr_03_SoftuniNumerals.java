package exam02_28February2016;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by xxx on 4/15/2016.
 */
public class pr_03_SoftuniNumerals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        char[] numeralString = input.toCharArray();
        StringBuilder tempData = new StringBuilder();
        String[] softUniNumbers = {"aa", "aba", "bcc", "cc", "cdc"};
        StringBuilder finalString = new StringBuilder();

        for (int c = 0; c < input.length() ; c++) {
            if (input.toCharArray()[c] == 'a'){
                if (input.toCharArray()[c+1] == 'a'){
                    tempData.append(input.toCharArray()[c]);
                            tempData.append(input.toCharArray()[c+1]);
                    c++;
                }
                else{
                   tempData.append(input.toCharArray()[c]);
                    tempData.append(input.toCharArray()[c+1]);
                    tempData.append(input.toCharArray()[c+2]);
                    c+=2;
                }
            }
            else if(input.toCharArray()[c] == 'c'){
                if(input.toCharArray()[c+1] == 'c'){
                    tempData.append(Character.valueOf(input.toCharArray()[c]));
                    tempData.append(Character.valueOf(input.toCharArray()[c+1]));
                    c++;
                }
                else{
                    tempData.append(Character.valueOf(input.toCharArray()[c]));
                    tempData.append(Character.valueOf(input.toCharArray()[c+1]));
                    tempData.append(Character.valueOf(input.toCharArray()[c+2]));
                    c+=2;
                }
            }
            else{
                tempData.append(Character.valueOf(input.toCharArray()[c]));
                tempData.append(Character.valueOf(input.toCharArray()[c+1]));
                tempData.append(Character.valueOf(input.toCharArray()[c+2]));
                c+=2;
            }
            switch (tempData.toString()){
                case "aa":
                    finalString.append(0);
                    break;
                case "aba":
                    finalString.append(1);
                    break;
                case "bcc":
                    finalString.append(2);
                    break;
                case "cc":
                    finalString.append(3);
                    break;
                case "cdc":
                    finalString.append(4);
                    break;
            }
            tempData.delete(0, tempData.length());
        }
        BigInteger result = new BigInteger(finalString.toString(), 5);
        System.out.println(result);
    }
}
