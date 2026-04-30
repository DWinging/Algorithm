package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_22856 {

    static int[] tree;
    static int n, cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new int[n+1];
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree[node] = right;
        }
        dfs(1);
        System.out.println((n - 1) * 2 - cnt);
    }

    private static void dfs(int node) {
        if(tree[node] != -1) {
            dfs(tree[node]);
            cnt++;
        }
    }
}
