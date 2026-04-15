package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_20007 {

    static int n, m, x, y;
    static ArrayList<ArrayList<Road>> list;
    static int[] dict;
    static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        dict = new int[n];
        Arrays.fill(dict, INF);

        for(int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            list.get(s).add(new Road(e, l));
            list.get(e).add(new Road(s, l));
        }

        dijkstra(y);
        Arrays.sort(dict);

        if(dict[n-1] == INF || dict[n-1] * 2 > x) {
            System.out.println(-1);
            System.exit(0);
        }

        int cnt = 1;
        int sum = 0;

        for(int i = 1; i < n; i++){
            int temp = dict[i] * 2;
            if(temp + sum > x) {
                cnt++;
                sum = 0;
            }
            sum += temp;
        }

        System.out.println(cnt);
    }

    private static void dijkstra(int h) {
        dict[h] = 0;
        PriorityQueue<Road> pq = new PriorityQueue<>((r1, r2) -> r1.len - r2.len);
        pq.offer(new Road(h, 0));

        while(!pq.isEmpty()) {
            Road now = pq.poll();
            int cur = now.end;
            if(dict[cur] < now.len) continue;
            for(Road next : list.get(cur)) {
                int end = next.end;
                int len = dict[cur] + next.len;
                if(dict[end] > len) {
                    dict[end] = len;
                    pq.offer(new Road(end, len));
                }
            }
        }
    }

    private static class Road {
        int end;
        int len;

        Road(int end, int len) {
            this.end = end;
            this.len = len;
        }
    }
}
