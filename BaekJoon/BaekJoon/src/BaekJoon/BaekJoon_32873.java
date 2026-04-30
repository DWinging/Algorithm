package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_32873 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        System.out.println(getStackCount(arr, n));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int getStackCount(int[] arr, int n) {
        int[] dp = new int[n];
        int cnt = 0;
        dp[0] = arr[0];

        for(int i = 1; i < n; i++) {
            int value = arr[i];
            if(dp[cnt] < value) {
                dp[cnt + 1] = value;
                cnt++;
            }
            else {
                int idx = binarySearch(dp, cnt, value);
                dp[idx] = value;
            }
        }
        return cnt + 1;
    }

    private static int binarySearch(int[] dp, int cnt, int value) {
        int left = 0, right = cnt, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(dp[mid] < value) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
