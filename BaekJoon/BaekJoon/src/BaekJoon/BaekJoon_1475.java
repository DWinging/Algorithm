package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        int[] arr = new int[10];
        for(char c : num.toCharArray()) {
            arr[c - '0']++;
        }

        arr[6] += arr[9];
        arr[6] = arr[6] % 2 == 0 ? arr[6] / 2 : arr[6] / 2 + 1;
        int cnt = 0;
        for(int i = 0; i < 9; i++){
            cnt = Math.max(cnt, arr[i]);
        }
        System.out.println(cnt);
    }
}
