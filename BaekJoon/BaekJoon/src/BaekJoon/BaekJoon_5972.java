package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node_5972 implements Comparable<Node_5972> {
    int end;
    int weight;

    Node_5972(int end, int weight){
        this.end = end;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node_5972 o) {
        return weight - o.weight;
    }
}

public class BaekJoon_5972 {
    static int n, m;
    static int[] cow;
    static boolean[] visit;
    static ArrayList<ArrayList<Node_5972>> route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cow = new int[n+1];
        visit = new boolean[n+1];
        route = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            route.add(new ArrayList<>());
            cow[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            route.get(a).add(new Node_5972(b, c));
            route.get(b).add(new Node_5972(a, c));
        }

        dijkstra();
        System.out.println(cow[n]);
    }

    private static void dijkstra(){
        cow[1] = 0;
        PriorityQueue<Node_5972> que = new PriorityQueue<>();
        que.offer(new Node_5972(1, 0));

        while(!que.isEmpty()){
            Node_5972 node = que.poll();
            int cur = node.end;
            if(!visit[cur]){
                visit[cur] = true;
                for(Node_5972 temp : route.get(cur)){
                    if(!visit[temp.end] && cow[temp.end] > cow[cur] + temp.weight){
                        cow[temp.end] = cow[cur] + temp.weight;
                        que.offer(new Node_5972(temp.end, cow[temp.end]));
                    }
                }
            }
        }
    }
}
