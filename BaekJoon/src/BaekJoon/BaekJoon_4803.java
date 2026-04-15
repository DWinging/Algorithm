package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_4803 {

    static int[] visit;
    static ArrayList<ArrayList<Integer>> matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int cnt = 0;
        while(true){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            cnt += 1;

            if(n1 == 0 && n2 == 0){
                break;
            }

            visit = new int[n1 + 1];
            matrix = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i < n1 + 1; i++){
                matrix.add(new ArrayList<Integer>());
            }

            for(int i = 0; i < n2; i++){
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                matrix.get(num1).add(num2);
                matrix.get(num2).add(num1);
            }

            int trees = 0;
            for(int i = 1; i < n1 + 1; i++){
                if(visit[i] == 0){
                    trees += bfs(i);
                }
            }

            bw.write("Case " + cnt + ": ");
            if(trees == 0){
                bw.write("No trees.\n");
            }
            else if(trees == 1){
                bw.write("There is one tree.\n");
            }
            else{
                bw.write("A forest of " + trees + " trees.\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static int bfs(int index) {
        Queue<Integer> que = new LinkedList<>();
        que.add(index);

        int nodes = 0;
        int vertex = 0;
        visit[index] = 1;

        while(!que.isEmpty()){
            int num = que.poll();

            nodes += 1;

            for(int i = 0; i < matrix.get(num).size(); i++){
                vertex += 1;
                int temp = matrix.get(num).get(i);
                if(visit[temp] == 0){
                    visit[temp] = 1;
                    que.add(temp);
                }
            }
        }

        if(nodes > vertex / 2){
            return 1;
        }
        else{
            return 0;
        }
    }
}
