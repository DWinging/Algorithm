package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1065 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = Math.min(n, 10);
        for(int i = 11; i <= n; i++) {
            if(check(i)) cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean check(int n) {
        String num = String.valueOf(n);
        int w = (num.charAt(1) - '0') - (num.charAt(0) - '0');
        for(int i = 2; i < num.length(); i++) {
            if((num.charAt(i) - '0') - (num.charAt(i - 1) - '0') != w) return false;
        }
        return true;
    }
}
