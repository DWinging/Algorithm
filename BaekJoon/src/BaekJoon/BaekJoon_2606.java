package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2606 {

    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> com;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        com = new ArrayList<ArrayList<Integer>>();

        visit = new boolean[n+1];

        for(int i = 0; i < n+1; i++){
            com.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            com.get(n1).add(n2);
            com.get(n2).add(n1);
        }

        System.out.println(bfs(1) - 1);
    }

    static int bfs(int index){
        Queue<Integer> que = new LinkedList<>();
        que.add(index);

        int cnt = 0;

        while(!que.isEmpty()){
            int node = que.poll();

            if(visit[node]){
                continue;
            }
            visit[node] = true;
            cnt += 1;

            for(int i = 0; i < com.get(node).size(); i++){
                int temp = com.get(node).get(i);
                if(!visit[temp]){
                    que.add(temp);
                }
            }
        }

        return cnt;
    }
}
