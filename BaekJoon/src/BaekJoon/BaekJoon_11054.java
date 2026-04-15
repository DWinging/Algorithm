package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);

        int[] lis = computeLIS(arr, n, 0, 1);             // 정방향 LIS
        int[] lisReverse = computeLIS(arr, n, n - 1, -1); // 역방향 LIS

        System.out.println(getMaxLen(lis, lisReverse));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        return arr;
    }

    private static int[] computeLIS(int[] arr, int n, int start, int mod) {
        int[] dp = new int[n];
        int[] lis = new int[n];
        int len = 0;

        dp[0] = arr[start];
        lis[start] = 0;

        for(int i = start + mod; i >= 0 && i < n; i += mod) {
            int value = arr[i];
            int idx = len + 1;
            if(dp[len] < value) len++;
            else idx = binarySearch(dp, len, value);
            lis[i] = idx;
            dp[idx] = value;
        }
        return lis;
    }

    private static int binarySearch(int[] dp, int cnt, int target) {
        int left = 0, right = cnt-1, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(dp[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private static int getMaxLen(int[] lis, int[] lisReverse) {
        int len = 0;
        for(int i = 0; i < lis.length; i++) {
            len = Math.max(len, lis[i] + lisReverse[i] + 1);
        }
        return len;
    }
}