package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_21937 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<Integer>());
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int w1 = Integer.parseInt(st.nextToken());
            int w2 = Integer.parseInt(st.nextToken());
            list.get(w2).add(w1);
        }

        int w = Integer.parseInt(br.readLine());
        System.out.println(bfs(list, w, n));
    }

    private static int bfs(ArrayList<ArrayList<Integer>> list, int w, int n) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(w);

        boolean[] visited = new boolean[n + 1];
        int cnt = 0;
        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();

            if(list.get(cur).isEmpty()) continue;
            for(int pre : list.get(cur)) {
                if(!visited[pre]) {
                    deque.addLast(pre);
                    visited[pre] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
