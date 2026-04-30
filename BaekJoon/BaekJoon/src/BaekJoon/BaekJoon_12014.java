package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_12014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        int n, k;
        StringTokenizer st;
        for(int i = 1; i <= testCase; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            int[] arr = inputArray(n, br);
            int cnt = purchase(arr, n);

            bw.write("Case #" + i + "\n");
            bw.write((cnt >= k ? 1 : 0) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int purchase(int[] arr, int n) {
        int[] dp = new int[n];
        dp[0] = arr[0];
        int cnt = 0;

        for(int i = 1; i <n; i++) {
            int idx, value = arr[i];
            if(dp[cnt] < value) idx = ++cnt;
            else idx = binarySearch(dp, cnt, value);
            dp[idx] = value;
        }
        return cnt + 1;
    }

    private static int binarySearch(int[] dp, int idx, int target) {
        int left = 0, right = idx - 1, mid;

        while(left <= right) {
            mid = (left + right) / 2;
            if(dp[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
