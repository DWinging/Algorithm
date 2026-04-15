package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solve(str));
    }

    private static String solve(String str) {
        int len = str.length();
        int i = 0;
        while(i < len) {
            if(i + 1 < len && str.charAt(i) == '0' && str.charAt(i + 1) == '1') {
                i += 2;
            }
            else if(i + 2 < len && str.charAt(i) == '1' && str.charAt(i + 1) == '0' && str.charAt(i + 2) == '0') {
                i += 3;
                while (i < len && str.charAt(i) == '0') i++;

                if(i == len) return "NOISE";
                i++;
                while(i < len && str.charAt(i) != '0') {
                    if(i + 2 < len && str.charAt(i) == '1' && str.charAt(i + 1) == '0' && str.charAt(i + 2) == '0') {
                        break;
                    }
                    i++;
                }
            }
            else return "NOISE";
        }

        return "SUBMARINE";
    }
}