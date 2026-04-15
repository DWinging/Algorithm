package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_22963 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        int[] parents = new int[n];
        int[] lisInfo = getLisLength(arr, parents, n);

        if(lisInfo[0] > 3) {
            System.out.println("NO");
        }
        else {
            boolean[] visited = checkLIS(parents, lisInfo[1], n);
            System.out.print(buildString(visited, arr, lisInfo[0], n));
        }
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int[] getLisLength(int[] arr, int[] parents, int n) {
        int[] dp = new int[n];
        dp[0] = 0;
        parents[0] = -1;

        int cnt = 0;
        for(int i = 1; i < n; i++) {
            int value = arr[i];
            if(arr[dp[cnt]] <= value) {
                parents[i] = dp[cnt];
                dp[cnt + 1] = i;
                cnt++;
            }
            else {
                int idx = binarySearch(arr, dp, cnt, value);
                parents[i] = idx == 0 ? -1 : dp[idx-1];
                dp[idx] = i;
            }
        }
        return new int[]{n - (cnt + 1), dp[cnt]};
    }

    private static int binarySearch(int[] arr, int[] dp, int cnt, int target) {
        int left = 0, right = cnt-1, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(arr[dp[mid]] <= target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private static boolean[] checkLIS(int[] parents, int idx, int n) {
        boolean[] visited = new boolean[n];
        int p = idx;
        int child = parents[p];
        visited[p] = true;
        while(child != -1) {
            p = child;
            child = parents[child];
            visited[p] = true;
        }
        return visited;
    }

    private static String buildString(boolean[] visited, int[] arr, int cnt, int n) {
        StringBuilder sb = new StringBuilder("YES").append("\n");
        sb.append(cnt).append("\n");
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                int value = i == 0 ? 1 : arr[i-1];
                arr[i] = value;
                sb.append(i + 1).append(" ").append(value).append("\n");
            }
        }
        return sb.toString();
    }
}
