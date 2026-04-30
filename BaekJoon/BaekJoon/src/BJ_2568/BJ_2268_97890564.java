package BJ_2568;

import java.util.*;
import java.io.*;
/**
 * 2025년 8월 27일 풀이
 * BaekJoon_2568 전깃줄-2
 * 메모리 49412 KB
 * 시간 596 ms
 */
public class BJ_2268_97890564 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] lines = inputLines(n, br);
        int[] parents = new int[n];
        int idx = buildLIS(lines, parents, n);
        System.out.print(buildString(lines, parents, n, idx));
    }

    private static int[][] inputLines(int n, BufferedReader br) throws IOException {
        StringTokenizer st;
        int[][] lines = new int[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lines, (l1, l2) -> Integer.compare(l1[0], l2[0]));
        return lines;
    }

    private static int buildLIS(int[][] lines, int[] parents, int n) {
        int[] dp = new int[n];
        dp[0] = 0;
        parents[0] = -1;
        int cnt = 0;
        for(int i = 1; i < n; i++) {
            int value = lines[i][1];
            if(lines[dp[cnt]][1] < value) {
                parents[i] = dp[cnt];
                dp[cnt + 1] = i;
                cnt++;
            }
            else {
                int idx = binarySearch(lines, dp, value, cnt);
                parents[i] = (idx == 0 ? -1 : dp[idx - 1]);
                dp[idx] = i;
            }
        }

        return dp[cnt];
    }

    private static int binarySearch(int[][] lines, int[] dp, int value, int cnt) {
        int left = 0, right = cnt-1, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(lines[dp[mid]][1] < value) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private static String buildString(int[][] lines, int[] parents, int n, int idx) {
        Deque<Integer> deque = new ArrayDeque<>();
        while(idx != -1) {
            deque.addLast(idx);
            idx = parents[idx];
            n--;
        }

        StringBuilder sb = new StringBuilder(n + "\n");
        for(int i = 0; i < lines.length; i++) {
            if(!deque.isEmpty() && i == deque.peekLast()) deque.pollLast();
            else sb.append(lines[i][0]).append("\n");
        }
        return sb.toString();
    }
}

