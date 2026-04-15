package BJ_14003;

import java.io.*;
import java.util.*;
/**
 * 2025년 5월 28일 풀이
 * BaekJoon_14003 가장 긴 증가하는 부분 수열 5
 * 메모리 194896 KB
 * 시간 624 ms
 */
public class BJ_14003_97900356 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        int[] parents = new int[n];
        int lastIdx = buildLIS(arr, parents, n);
        System.out.print(buildString(parents, arr, lastIdx, n));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int buildLIS(int[] arr, int[] parents, int n) {
        int[] dp = new int[n];
        dp[0] = 0;
        parents[0] = -1;
        int cur = 0;

        for(int i = 1; i < n; i++) {
            int value = arr[i];
            if(arr[dp[cur]] < value) {
                parents[i] = dp[cur++];
                dp[cur] = i;
            }
            else {
                int idx = binarySearch(arr, dp, cur, value);
                parents[i] = (idx == 0 ? -1 : dp[idx - 1]);
                dp[idx] = i;
            }
        }

        return dp[cur];
    }

    private static int binarySearch(int[] arr, int[] dp, int cur, int target) {
        int left = 0, right = cur-1, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(arr[dp[mid]] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private static String buildString(int[] parents, int[] arr, int idx, int n) {
        boolean[] visited = new boolean[n];
        int cnt = 0;
        while(idx != -1) {
            visited[idx] = true;
            idx = parents[idx];
            cnt++;
        }
        StringBuilder sb = new StringBuilder(cnt + "\n");
        for(int i = 0; i < n; i++) {
            if(visited[i]) sb.append(arr[i]).append(" ");
        }
        return sb.toString();
    }
}

