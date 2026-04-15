package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_12834 {

    static int n, v, k;
    static boolean[] visit;
    static int[] dict;
    static ArrayList<ArrayList<Node>> route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] h = new int[n];
        for(int i = 0; i < n; i++){
            h[i] = Integer.parseInt(st.nextToken());
        }

        route = new ArrayList<>();
        for(int i = 0; i <= v; i++){
            route.add(new ArrayList<>());
        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            route.get(n1).add(new Node(n2, w));
            route.get(n2).add(new Node(n1, w));
        }

        int total = 0;
        for(int i : h){
            dijkstra(i);
            total += dict[a] != Integer.MAX_VALUE ? dict[a] : -1;
            total += dict[b] != Integer.MAX_VALUE ? dict[b] : -1;
        }
        System.out.println(total);
    }

    private static void dijkstra(int index){
        dict = new int[v+1];
        Arrays.fill(dict, Integer.MAX_VALUE);
        dict[index] = 0;
        visit = new boolean[v+1];
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(index, 0));

        while(!que.isEmpty()) {
            Node node = que.poll();
            int cur = node.end;
            if(!visit[cur]) {
                visit[cur] = true;
                for(Node temp : route.get(cur)){
                    if(!visit[temp.end] && dict[temp.end] > temp.weight + dict[cur]){
                        dict[temp.end] = temp.weight + dict[cur];
                        que.add(new Node(temp.end, dict[temp.end]));
                    }
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
