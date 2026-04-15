package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_11578 {

    static int team = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] solve = new int[n + 1];

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            while(temp-- > 0) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(list, solve, n, m, 0, 0, 0);
        System.out.println(team == Integer.MAX_VALUE ? -1 : team);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> list, int[] solve, int n, int m, int num, int cnt, int idx) {
        if(cnt == n) {
            team = Math.min(team, num);
            return;
        }
        for(int i = idx; i < m; i++) {
            cnt += marking(list.get(i), solve, 1);
            dfs(list, solve, n, m, num + 1, cnt, i + 1);
            cnt -= marking(list.get(i), solve, -1);
        }
    }

    private static int marking(ArrayList<Integer> list, int[] solve, int w) {
        int cnt = 0;
        for(int i : list) {
            solve[i] += w;
            if(w == 1 && solve[i] == 1) cnt++;
            if(w == -1 && solve[i] == 0) cnt++;
        }
        return cnt;
    }
}
