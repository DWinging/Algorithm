package BaekJoon;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BaekJoon_1325 {

    static int nodes;
    static int[] value;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int max = 0;

        list = new ArrayList<ArrayList<Integer>>();
        nodes = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        value = new int[nodes+1];

        for(int i = 0; i <= nodes; i++){
            list.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < edge; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            list.get(num2).add(num1);
        }

        for(int i = 1; i <= nodes; i++){
            max = Math.max(max, bfs(i));
        }

        for(int i = 1; i < value.length; i++){
            if(value[i] == max){
                sb.append(i + " ");
            }
        }

        System.out.println(sb);
    }

    public static int bfs(int node){
        int cnt = 0;
        boolean[] visit = new boolean[nodes+1];
        Queue<Integer> que = new LinkedList<>();
        que.add(node);
        visit[node] = true;

        while(!que.isEmpty()){
            int n = que.poll();

            for(int i : list.get(n)){
                if(!visit[i]){
                    visit[i] = true;
                    que.add(i);
                    cnt++;
                }
            }
        }
        value[node] = cnt;

        return cnt;
    }

}
