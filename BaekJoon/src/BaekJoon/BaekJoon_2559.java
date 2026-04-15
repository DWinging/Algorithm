package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(st.nextToken());

        int[] list = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }


        int max = 0;
        for(int i = 0; i < len; i++) {
            max += list[i];
        }
        int sum = max;
        for(int i = len; i < n; i++){
            sum = sum - list[i-len] + list[i];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
