package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_6497 {

    static int[] parent;
    static PriorityQueue<Road> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if(v == 0 && e == 0) break;

            parent = new int[v];
            for(int i = 0; i < v; i++) {
                parent[i] = i;
            }

            que = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.w, r2.w));
            int total = 0;
            for(int i = 0; i < e; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                total += w;
                que.add(new Road(x, y, w));
            }

            int cnt = 0;
            int cost = 0;

            while(cnt < v - 1) {
                Road r = que.poll();
                int x = find(r.x);
                int y = find(r.y);

                if(x != y) {
                    union(x, y);
                    cnt++;
                    cost += r.w;
                }
            }

            sb.append(total - cost).append("\n");
        }

        System.out.println(sb);
    }

    private static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        parent[x] = y;
    }

    private static class Road {
        int x;
        int y;
        int w;

        Road(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}
