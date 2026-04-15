package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_20955 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parents = settingParents(n);
        int cnt = removeLine(parents, m, br) + countTree(parents, n);
        System.out.println(cnt - 1);
    }

    private static int[] settingParents(int n) {
        int[] parents = new int[n + 1];
        for(int i = 1; i <= n; i++) parents[i] = i;
        return parents;
    }

    private static int removeLine(int[] parents, int m, BufferedReader br) throws IOException {
        StringTokenizer st;
        int cnt = 0;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if(!union(parents, n1, n2)) cnt++;
        }
        return cnt;
    }

    private static boolean union(int[] parents, int n1, int n2) {
        int root1 = find(parents, n1);
        int root2 = find(parents, n2);

        if(root1 == root2) return false;
        parents[root2] = root1;
        return true;
    }

    private static int find(int[] parents, int n) {
        return parents[n] == n ? n : (parents[n] =  find(parents, parents[n]));
    }

    private static int countTree(int[] parents, int n) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) if(parents[i] == i) cnt++;
        return cnt;
    }
}
