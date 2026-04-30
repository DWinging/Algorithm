package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16439 {

    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][k];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < k; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Stack<Integer> stack = new Stack<>();
        dfs(arr, stack, n, k, 0, 0);

        bw.write(String.valueOf(total));
        bw.flush();
        bw.close();
    }

    private static void dfs(int[][] arr, Stack<Integer> stack, int n, int k, int idx, int cnt) {
        if(cnt == 3) {
            int sumValue = 0;
            for(int i = 0; i < n; i++) {
                sumValue += maxValue(arr, stack, i);
            }
            total = Math.max(total, sumValue);
            return;
        }
        for(int i = idx; i < k; i++) {
            stack.push(i);
            dfs(arr, stack, n, k, i + 1, cnt + 1);
            stack.pop();
        }
    }

    private static int maxValue(int[][] arr, Stack<Integer> stack, int idx) {
        int value = 0;
        for(int i : stack) {
            value = Math.max(arr[idx][i], value);
        }
        return value;
    }
}
