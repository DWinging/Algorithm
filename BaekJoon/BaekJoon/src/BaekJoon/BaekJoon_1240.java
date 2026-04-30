package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1240 {

    static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken())-1;
            int n2 = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            tree.get(n1).add(new Node(n2, w));
            tree.get(n2).add(new Node(n1, w));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            sb.append(bfs(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1)).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int v1, int target){
        int[] dict = new int[n];
        boolean[] visit = new boolean[n];
        visit[v1] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(v1, 0));

        while(!que.isEmpty()) {
            Node node = que.poll();
            int cur = node.end;
            if(cur == target) break;
            for(Node temp : tree.get(cur)){
                if(!visit[temp.end]) {
                    dict[temp.end] = temp.weight + dict[cur];
                    que.add(new Node(temp.end, dict[temp.end]));
                    visit[temp.end] = true;
                }
            }
        }

        return dict[target];
    }

    private static class Node {
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
}
