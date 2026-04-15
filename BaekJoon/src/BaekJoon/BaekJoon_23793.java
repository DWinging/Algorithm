package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_23793 {

    static int[] dict;
    static ArrayList<ArrayList<Node>> graph;
    static final int INF = 1000000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        dict = new int[n+1];
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
            dict[i] = INF;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        dijkstra(x, y);
        int zValue = dict[z] != INF ? dict[z] : -1;
        int yValue = dict[y];
        dijkstra(y, 0);
        yValue = yValue != INF && dict[z] != INF ? yValue + dict[z] : -1;
        System.out.println(yValue + " " + zValue);
    }

    private static void dijkstra(int start, int avoidNode) {
        Arrays.fill(dict, INF);
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        que.add(new Node(start, 0));
        dict[start] = 0;
        boolean[] visit = new boolean[dict.length];

        while(!que.isEmpty()) {
            Node node = que.poll();
            int cur = node.end;
            if(!visit[cur]) {
                visit[cur] = true;
                if(cur == avoidNode) {
                    continue;
                }
                for(Node next : graph.get(cur)) {
                    if(!visit[next.end] && dict[next.end] > next.weight + dict[cur]){
                        dict[next.end] = next.weight + dict[cur];
                        que.add(new Node(next.end, dict[next.end]));
                    }
                }
            }
        }

    }

    private static class Node {
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
