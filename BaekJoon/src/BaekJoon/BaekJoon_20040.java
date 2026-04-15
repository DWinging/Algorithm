package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_20040 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int result = 0;
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int x = find(Integer.parseInt(st.nextToken()));
            int y = find(Integer.parseInt(st.nextToken()));

            if(x == y) {
                result = i;
                break;
            }
            union(x, y);
        }

        System.out.println(result);
    }

    private static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        parent[x] = y;
    }
}
