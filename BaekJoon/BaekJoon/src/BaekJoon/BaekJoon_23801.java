package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_23801 {

    static final long INF = 2000000000000L + 1;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] dict1 = new long[n+1];
        long[] dict2 = new long[n+1];

        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            dict1[i] = INF;
            dict2[i] = INF;
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            graph.get(v1).add(new Node(v2, w));
            graph.get(v2).add(new Node(v1, w));
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());
        dict1 = dijkstra(x, dict1);
        dict2 = dijkstra(z, dict2);

        int p = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long min = INF;
        while(st.hasMoreTokens()) {
            int node = Integer.parseInt(st.nextToken());
            min = Math.min(dict1[node] + dict2[node], min);
        }

        System.out.println(min == INF ? -1 : min);
    }

    private static long[] dijkstra(int start, long[] dict) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pq.offer(new Node(start, 0));
        dict[start] = 0;
        boolean[] visit = new boolean[dict.length];
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.end;
            if(!visit[cur]) {
                visit[cur] = true;
                for(Node next : graph.get(cur)) {
                    if(!visit[next.end] && dict[next.end] > dict[cur] + next.weight){
                        dict[next.end] = dict[cur] + next.weight;
                        pq.offer(new Node(next.end, dict[next.end]));
                    }
                }
            }
        }

        return dict;
    }

    private static class Node{
        int end;
        long weight;

        Node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
