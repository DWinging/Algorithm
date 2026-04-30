package BJ_2848;

import java.io.*;
import java.util.*;
/**
 * 2026년 2월 24일 풀이
 * BaekJoon_2848 알고스팟어
 * 메모리 11628 KB
 * 시간 68 ms
 */
public class BJ_2848_103249265 {

    final static int TOTAL = 26;
    static String[] str;
    static boolean[][] check = new boolean[TOTAL][TOTAL];
    static int[] cnt = new int[TOTAL];

    public static void main(String[] args) throws IOException {
        int n = init();
        boolean flag = solve(n);
        System.out.println(flag ? topologicalSort() : "!");
    }

    private static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        str = new String[n];
        Arrays.fill(cnt, -1);
        for(int i = 0; i < n; i++) {
            str[i] = br.readLine();
            for(char c : str[i].toCharArray()) cnt[c - 'a'] = 0;
        }
        return n;
    }

    private static boolean solve(int n) {
        for(int i = 1; i < n; i++) if(!checkOrder(i)) return false;
        return true;
    }

    private static boolean checkOrder(int idx) {
        int len1 = str[idx-1].length();
        int len2 = str[idx].length();
        for(int i = 0; i < Math.min(len1, len2); i++) {
            int c1 = str[idx-1].charAt(i) - 'a';
            int c2 = str[idx].charAt(i) - 'a';
            if(c1 == c2) continue;
            if(check[c2][c1]) return false;
            if(!check[c1][c2]) {
                check[c1][c2] = true;
                cnt[c2]++;
            }
            return true;
        }
        return len1 <= len2;
    }

    private static String topologicalSort() {
        StringBuilder sb = new StringBuilder();
        int[] deque = new int[TOTAL];
        int head = 0, tail = 0, time = 0, total = 0;

        for(int i = 0; i < TOTAL; i++) {
            if(cnt[i] == 0) {
                deque[tail++] = i;
                cnt[i] = -1;
            }
        }

        while(head < tail) {
            int len = tail - head;
            while(len-- > 0) {
                int cur = deque[head++];
                sb.append((char) (cur + 'a'));
                total++;
                for(int i = 0; i < TOTAL; i++) {
                    if(check[cur][i]) cnt[i]--;
                    if(cnt[i] == 0) {
                        deque[tail++] = i;
                        cnt[i] = -1;
                    }
                }
            }
            time++;
        }

        if(total != tail) return "!";
        return time == tail ? sb.toString() : "?";
    }
}
