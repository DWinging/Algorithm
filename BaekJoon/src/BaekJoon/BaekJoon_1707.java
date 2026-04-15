package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1707 {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            st = new StringTokenizer(br.readLine());
            visit = new int[Integer.parseInt(st.nextToken()) + 1];
            graph = new ArrayList<>();

            for(int i = 0; i < visit.length; i++){
                graph.add(new ArrayList<>());
            }

            int e = Integer.parseInt(st.nextToken());
            for(int i = 0; i < e; i++){
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                graph.get(num1).add(num2);
                graph.get(num2).add(num1);
            }

            String answer = "YES";
            for(int i = 0; i < visit.length; i++){
                if(visit[i] == 0){
                    if(!bfs(i)){
                        answer = "NO";
                        break;
                    }
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static Boolean bfs(int index){
        int color = 1;
        Queue<Integer> que = new LinkedList<>();
        que.add(index);
        visit[index] = color;
        while(!que.isEmpty()){
            int v = que.poll();

            for(int i = 0; i < graph.get(v).size(); i++){
                int temp = graph.get(v).get(i);
                if(visit[temp] == 0){
                    que.add(temp);
                    visit[temp] = visit[v] * -1;
                }
                else if(visit[temp] == visit[v]){
                    return false;
                }
            }
        }

        return true;
    }
}
