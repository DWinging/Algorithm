package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1922 {

    static int[] parent;
    static PriorityQueue<Network> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());

        parent = new int[v+1];
        for(int i = 1; i <= v; i++){
            parent[i] = i;
        }

        que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.w, n2.w));
        StringTokenizer st;
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            que.add(new Network(x, y, w));
        }

        int cost = 0;
        int cnt = 0;
        while(cnt < v-1) {
            Network net = que.poll();
            int x = find(net.x);
            int y = find(net.y);

            if(x != y) {
                union(x, y);
                cnt++;
                cost += net.w;
            }
        }

        System.out.println(cost);
    }

    private static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        parent[x] = y;
    }

    private static class Network {
        int x;
        int y;
        int w;

        Network(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}
