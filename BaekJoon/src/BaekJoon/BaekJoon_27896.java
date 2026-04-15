package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_27896 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> que = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        int cnt = 0;
        for(int i = 0; i < n; i++){
            long x = Long.parseLong(st.nextToken());
            sum += x;
            que.offer(x);
            while(sum >= m) {
                sum -= que.poll() * 2;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
