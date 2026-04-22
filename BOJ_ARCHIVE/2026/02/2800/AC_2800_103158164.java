/**
 * [BOJ] 2800 - 괄호 제거
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 17032 KB
 * - 시간: 108 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb;
    static Set<String> result = new TreeSet<>();
    static char[] arr;
    static boolean[] visited;
    static int[] stack = new int[10];
    static int[] open = new int[10];
    static int[] close = new int[10];
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        checkBrackets();
        solve();
        sb.setLength(0);
        for(String s : result) sb.append(s).append('\n');
        System.out.print(sb);
    }

    private static void checkBrackets() {
        int top = -1;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '(') stack[++top] = i;
            if(arr[i] == ')') {
                open[total] = stack[top--];
                close[total] = i;
                total++;
            }
        }
    }

    private static void solve() {
        visited = new boolean[arr.length];
        sb = new StringBuilder(arr.length);
        backtracking(0);
    }

    private static void backtracking(int depth) {
        if(depth == total) {
            sb.setLength(0);
            for(int i = 0; i < arr.length; i++) {
                if(visited[i]) continue;
                sb.append(arr[i]);
            }
            if(sb.length() == arr.length) return;
            result.add(sb.toString());
            return;
        }

        toggle(depth, true);
        backtracking(depth + 1);
        toggle(depth, false);
        backtracking(depth + 1);
    }

    private static void toggle(int idx, boolean flag) {
        visited[open[idx]] = flag;
        visited[close[idx]] = flag;
    }
}