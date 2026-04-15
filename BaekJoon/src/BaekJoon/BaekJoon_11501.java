package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine().trim());
        while(testCase-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int cost = 0;
            long profit = 0;
            for(int i = n - 1; i >= 0; i--) {
                if(arr[i] >= cost) cost = arr[i];
                else profit += cost - arr[i];
            }
            bw.write(profit + "\n");
        }
        bw.flush();
        bw.close();
    }
}
