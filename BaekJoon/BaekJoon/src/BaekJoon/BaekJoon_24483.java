package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_24483 {

    static int N, M, index;
    static long cnt = 1, sum = 0;
    static long[] visit, depth;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        index = Integer.parseInt(st.nextToken());

        visit = new long[N + 1];
        depth = new long[N + 1];
        graph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            graph.get(num1).add(num2);
            graph.get(num2).add(num1);
        }

        for(int i = 1; i < graph.size(); i++){
            Collections.sort(graph.get(i));
        }

        visit[index] = cnt++;
        depth[index] = 0;
        dfs(index);

        System.out.println(sum);
    }

    private static void dfs(int index){
        for(int i : graph.get(index)){
            if(visit[i] == 0){
                visit[i] = cnt++;
                depth[i] = depth[index] + 1;
                sum += visit[i] * depth[i];
                dfs(i);
            }
        }
    }
}
