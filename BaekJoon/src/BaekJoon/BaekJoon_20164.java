package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_20164 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        int[] answer = solve(num);
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static int[] solve(String num) {
        int cnt = countOdd(num);
        int minVal = Integer.MAX_VALUE;
        int maxVal = 0;
        if(num.length() == 1) {
            minVal = cnt;
            maxVal = cnt;
        }
        else if(num.length() == 2) {
            int n1 = num.charAt(0) - '0';
            int n2 = num.charAt(1) - '0';
            int n = n1 + n2;

            int[] temp = solve(String.valueOf(n));
            minVal = cnt + temp[0];
            maxVal = cnt + temp[1];
        }
        else {
            for(int i = 1; i < num.length() - 1; i++) {
                for(int j = i + 1; j < num.length(); j++) {
                    int n1 = Integer.parseInt(num.substring(0, i));
                    int n2 = Integer.parseInt(num.substring(i, j));
                    int n3 = Integer.parseInt(num.substring(j));
                    int n = n1 + n2 + n3;

                    int[] temp = solve(String.valueOf(n));
                    minVal = Math.min(minVal, cnt + temp[0]);
                    maxVal = Math.max(maxVal, cnt + temp[1]);
                }
            }
        }
        return new int[] {minVal, maxVal};
    }

    private static int countOdd(String num) {
        int cnt = 0;
        for(char c : num.toCharArray()) {
            if((c - '0') % 2 == 1) cnt++;
        }
        return cnt;
    }
}
