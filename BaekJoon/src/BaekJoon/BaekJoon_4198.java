package BaekJoon;

import java.io.*;

public class BaekJoon_4198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(n == 0 ? 0 : solve(n, br));
    }

    private static int solve(int n, BufferedReader br) throws IOException {
        int[] trains = inputTrain(n, br);
        int[] lis = getArray(trains, n, 1);
        int[] lds = getArray(trains, n, -1);
        return maxOf(lis, lds, n);
    }

    private static int[] inputTrain(int n, BufferedReader br) throws IOException {
        int[] trains = new int[n];
        for(int i = n-1; i >= 0; i--) trains[i] = Integer.parseInt(br.readLine());
        return trains;
    }

    private static int[] getArray(int[] trains, int n, int mod) {
        int[] arr = new int[n];
        int[] dp = new int[n];
        dp[0] = trains[0] * mod;
        arr[0] = 1;
        int cnt = 0;
        for(int i = 1; i < n; i++) {
            int value = trains[i] * mod;
            int idx = cnt + 1;
            if(dp[cnt] < value) cnt++;
            else idx = binarySearch(dp, cnt, value);
            dp[idx] = value;
            arr[i] = idx + 1;
        }
        return arr;
    }

    private static int binarySearch(int[] dp, int cnt, int train) {
        int left = 0, right = cnt - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(dp[mid] < train) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private static int maxOf(int[] lis, int[] lds, int n) {
        int value = 0;
        for(int i = 0; i < n; i++) {
            value = Math.max(value, lis[i] + lds[i] - 1);
        }
        return value;
    }
}