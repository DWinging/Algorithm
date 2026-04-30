package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node_11799 implements Comparable<Node_11799> {
    int start;
    int end;
    long weight;

    Node_11799(int start, int end, long weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node_11799 o) {
        return (int) (weight - o.weight);
    }
}
public class BaekJoon_11799 {

    static int n, m;
    static ArrayList<ArrayList<Node_11799>> graph;
    static long[] dict;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st;

        visit = new int[n+1];
        dict = new long[n+1];

        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
            dict[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            graph.get(s).add(new Node_11799(s, e, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        sb.append(dict[end]).append("\n");
        while(visit[start] != end){
            list.add(end);
            end = visit[end];
        }
        list.add(start);
        sb.append(list.size()).append("\n");
        for(int i = list.size()-1; i >= 0; i--){
            sb.append(list.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static void dijkstra(int s){
        dict[s] = 0;
        PriorityQueue<Node_11799> que = new PriorityQueue<>();
        que.add(new Node_11799(s, s, 0));

        while(!que.isEmpty()){
            Node_11799 node = que.poll();
            int cur = node.end;
            if(visit[cur] == 0){
                visit[cur] = node.start;
                for(Node_11799 temp : graph.get(cur)){
                    if(visit[temp.end] == 0 && dict[temp.end] > temp.weight + dict[cur]){
                        dict[temp.end] = temp.weight + dict[cur];
                        que.offer(new Node_11799(temp.start, temp.end, dict[temp.end]));
                    }
                }
            }
        }
    }
}
