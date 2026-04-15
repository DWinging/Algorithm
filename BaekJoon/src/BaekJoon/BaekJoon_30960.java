package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_30960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] cost = new int[n];
        int total = arr[1] - arr[0];
        for(int i = 1; i < n; i++) {
            cost[i] = arr[i] - arr[i-1];
            if(i % 2 == 0) total += cost[i];
        }

        int minSum = total;
        for(int i = 4; i < n; i += 2) {
            total += (cost[i - 1] - cost[i - 2]);
            minSum = Math.min(minSum, total);
        }
        bw.write(String.valueOf(minSum));
        bw.flush();
        bw.close();
    }
}
