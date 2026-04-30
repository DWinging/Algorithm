package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_14284 {

    static int n, m;
    static boolean[] visit;
    static int[] dict;
    static ArrayList<ArrayList<Node_14284>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visit = new boolean[n+1];
        dict = new int[n+1];
        graph = new ArrayList<ArrayList<Node_14284>>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
            dict[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node_14284(b, w));
            graph.get(b).add(new Node_14284(a, w));
        }

        st = new StringTokenizer(br.readLine());
        dijkstra(Integer.parseInt(st.nextToken()));

        System.out.println(dict[Integer.parseInt(st.nextToken())]);
    }

    private static void dijkstra(int s){
        PriorityQueue<Node_14284> que = new PriorityQueue<>();
        que.add(new Node_14284(s, 0));
        dict[s] = 0;

        while(!que.isEmpty()){
            Node_14284 node = que.poll();
            int cur = node.end;
            if(!visit[cur]){
                visit[cur] = true;
                for(Node_14284 temp : graph.get(cur)){
                    if(!visit[temp.end] && dict[temp.end] > dict[cur] + temp.weight){
                        dict[temp.end] = dict[cur] + temp.weight;
                        que.add(new Node_14284(temp.end, dict[temp.end]));
                    }
                }
            }
        }
    }
}

class Node_14284 implements Comparable<Node_14284>{
    int end;
    int weight;

    Node_14284(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node_14284 o) {
        return weight - o.weight;
    }
}
