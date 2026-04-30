package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_24484 {

    static int N, M;
    static long sum = 0, cnt = 1;
    static int[] visit;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int index = Integer.parseInt(st.nextToken());
        visit = new int[N+1];

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            list.get(num1).add(num2);
            list.get(num2).add(num1);
        }

        for(int i = 1; i < list.size(); i++){
            list.get(i).sort(Collections.reverseOrder());
        }

        visit[index] = 1;
        dfs(index);
        System.out.println(sum);
    }

    private static void dfs(int index){
        for(int i : list.get(index)){
            if(visit[i] == 0){
                sum += ++cnt * visit[index];
                visit[i] = visit[index] + 1;
                dfs(i);
            }
        }
    }
}
