package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_2479 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] nodes = new String[n + 1];
        for(int i = 1; i <= n; i++) {
            nodes[i] = br.readLine();
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] visited = new int[n + 1];
        System.out.println(bfs(nodes, visited, n, m, s, e) ? printRoute(visited, s, e) : "-1");
    }

    private static boolean bfs(String[] nodes, int[] visited, int n, int m, int s, int e) {
        Arrays.fill(visited, -1);
        visited[s] = s;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(s);

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            String str = nodes[cur];
            if(cur == e) return true;
            for(int i = 1; i <= n; i++) {
                if(visited[i] != -1) continue;
                if(getLength(nodes[i], str, m)) {
                    visited[i] = cur;
                    deque.addLast(i);
                }
            }
        }
        return false;
    }

    private static boolean getLength(String str1, String str2, int m) {
        int cnt = 0;
        for(int i = 0; i < m; i++) {
            if(str1.charAt(i) != str2.charAt(i)) cnt++;
        }
        return cnt == 1;
    }

    private static String printRoute(int[] visited, int s, int e) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(e);
        while(visited[e] != s) {
            e = visited[e];
            deque.addFirst(e);
        }
        deque.addFirst(s);

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.pollFirst()).append(" ");
        }
        return sb.toString();
    }
}
