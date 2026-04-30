package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node_1753 implements Comparable<Node_1753> {
    int end;
    int weight;

    Node_1753(int end, int weight){
        this.end = end;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node_1753 o) {
        return weight - o.weight;
    }
}

public class BaekJoon_1753 {

    static int v, e;
    static ArrayList<ArrayList<Node_1753>> graph;
    static int[] dict;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int startNode = Integer.parseInt(br.readLine());

        dict = new int[v+1];
        visit = new boolean[v+1];
        Arrays.fill(dict, Integer.MAX_VALUE);
        graph = new ArrayList<>();
        for(int i = 0; i <= v; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Node_1753(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        dijkstra(startNode);

        for(int i = 1; i < dict.length; i++){
            sb.append(dict[i] == Integer.MAX_VALUE ? "INF" : dict[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start){
        dict[start] = 0;
        PriorityQueue<Node_1753> que = new PriorityQueue<>();
        que.offer(new Node_1753(start, 0));

        while(!que.isEmpty()){
            Node_1753 node = que.poll();
            int cur = node.end;
            if(visit[cur]) continue;
            visit[cur] = true;
            for(Node_1753 temp : graph.get(cur)){
                if(dict[temp.end] > temp.weight + dict[cur]){
                    dict[temp.end] = temp.weight + dict[cur];
                    que.offer(new Node_1753(temp.end, dict[temp.end]));
                }
            }
        }
    }
}
