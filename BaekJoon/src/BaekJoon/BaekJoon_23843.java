package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class BaekJoon_23843 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> que = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        long[] times = new long[n];
        for(int i = 0; i < n; i++){
            times[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(times);

        long max = 0;
        for(int i = 0; i < m; i++){
            que.offer((long) 0);
        }

        for(int i = n-1; i >= 0; i--){
            long time = que.poll() + times[i];
            max = Math.max(max, time);
            que.offer(time);
        }

        System.out.println(max);
    }
}
