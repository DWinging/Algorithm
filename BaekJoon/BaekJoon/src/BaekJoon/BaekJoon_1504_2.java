package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1504_2 {

    static int[] dict;
    static boolean[] visit;
    static int n, v, INF = 200000000;
    static ArrayList<ArrayList<Node_1504>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        dict = new int[n+1];
        Arrays.fill(dict, Integer.MAX_VALUE);

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < v; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(v1).add(new Node_1504(v2, w));
            graph.get(v2).add(new Node_1504(v1, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        dijkstra(1);
        int route1 = dict[v1];
        int route2 = dict[v2];
        dijkstra(v1);
        route1 += dict[v2];
        route2 += dict[v2];
        dijkstra(n);
        route1 += dict[v2];
        route2 += dict[v1];

        System.out.println(route1 >= INF && route2 >= INF ? -1 : Math.min(route1, route2));
    }

    private static void dijkstra(int v){
        PriorityQueue<Node_1504> que = new PriorityQueue<>();
        que.offer(new Node_1504(v, 0));
        Arrays.fill(dict, INF);
        visit = new boolean[n+1];
        dict[v] = 0;

        while(!que.isEmpty()){
            Node_1504 node = que.poll();
            int cur = node.end;
            if(!visit[cur]) {
                visit[cur] = true;
                for(Node_1504 temp : graph.get(cur)){
                    if(!visit[temp.end] && dict[temp.end] > temp.weight + dict[cur]){
                        dict[temp.end] = temp.weight + dict[cur];
                        que.offer(new Node_1504(temp.end, dict[temp.end]));
                    }
                }
            }
        }
    }
}

class Node_1504 implements Comparable<Node_1504>{

    int end;
    int weight;

    Node_1504(int end, int weight){
        this.end = end;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node_1504 o) {
        return weight - o.weight;
    }
}
