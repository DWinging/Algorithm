package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16437_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] info = new long[n + 1];
        int[] parents = new int[n + 1];
        int[] childCnt = new int[n + 1];
        inputArray(info, parents, childCnt, n, br);

        System.out.println(countSheep(info, parents, childCnt, n));
    }

    private static void inputArray(long[] info, int[] parents, int[] childCnt, int n, BufferedReader br) throws IOException {
        StringTokenizer st;
        for(int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String kind = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            info[i] = kind.equals("S") ? cnt : -cnt;
            parents[i] = p;
            childCnt[p]++;
        }
    }

    private static long countSheep(long[] info, int[] parents, int[] childCnt, int n) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 2; i <= n; i++) {
            if(childCnt[i] == 0) deque.addLast(i);
        }

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            if(cur == 1) continue;
            int p = parents[cur];

            info[p] += Math.max(info[cur], 0);
            if(--childCnt[p] == 0) deque.addLast(p);
        }

        return Math.max(info[1], 0);
    }
}