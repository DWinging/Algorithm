package BJ_1306;

import java.util.*;
import java.io.*;
/**
 * 2026년 1월 23일 풀이
 * BaekJoon_1306 달려라 홍준
 * 메모리 318964 KB
 * 시간 1168 ms
 */
public class BJ_1306_102222423 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = inputArray(n, st, br);

        solve(arr, n, m, bw);
        bw.flush();
        bw.close();
    }

    private static int[] inputArray(int n, StringTokenizer st, BufferedReader br) throws IOException {
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static void solve(int[] arr, int n, int m, BufferedWriter bw) throws IOException{
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int power = 0;
        int[] cnt = new int[1_000_001];
        for(int i = 0; i < 2 * m - 1; i++) {
            pq.add(arr[i]);
            cnt[arr[i]]++;
        }

        int left = 0, right = 2 * m - 1;
        bw.write(pq.peek() + " ");
        while(right < n) {
            cnt[arr[right]]++;
            cnt[arr[left]]--;
            pq.add(arr[right]);

            while(!pq.isEmpty() && cnt[pq.peek()] == 0) {
                pq.poll();
            }
            bw.write(pq.peek() + " ");
            right++;
            left++;
        }
    }
}
