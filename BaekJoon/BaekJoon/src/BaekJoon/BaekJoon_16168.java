package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_16168 {

    static int v, e;
    static int[][] visit;
    static String result = "NO";
    static ArrayList<ArrayList<Integer>> dict;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        visit = new int[v][v];
        dict = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < e; i++){
            dict.add(new ArrayList<>());
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken())-1;
            int v2 = Integer.parseInt(st.nextToken())-1;
            dict.get(v1).add(v2);
            dict.get(v2).add(v1);
        }

        for(int i = 0; i < v; i++){
            dfs(i, i, 0);
        }

        System.out.println(result);
    }

    private static void dfs(int v1, int v2, int cnt){
        if(cnt == e) {
            result = "YES";
            return;
        }
        for(int i : dict.get(v1)){
            if(visit[v1][i] == v2|| visit[i][v1] == v2) continue;
            visit[v1][i] = visit[i][v1] = v2;
            dfs(i, v2, cnt+1);
        }
    }
}
