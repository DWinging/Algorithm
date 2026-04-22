/**
 * [BOJ] 2800 - 괄호 제거
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 14216 KB
 * - 시간: 96 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb;
    static Set<String> result = new TreeSet<>();
    static char[] arr;
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
        sb = new StringBuilder(arr.length);
        for(int bit = 1; bit <= (1 << total) - 1; bit++) {
            toggle(bit, ' ', ' ');
            buildString();
            toggle(bit, '(', ')');
        }
    }

    private static void buildString() {
        sb.setLength(0);
        for (char c : arr) {
            if (c == ' ') continue;
            sb.append(c);
        }
        if(sb.length() == arr.length) return;
        result.add(sb.toString());
    }

    private static void toggle(int bit, char o, char c) {
        for(int i = 0; i < total; i++) {
            if((bit & (1 << i)) != 0) {
                arr[open[i]] = o;
                arr[close[i]] = c;
            }
        }
    }
}
