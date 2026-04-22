/**
 * [BOJ] 15971 - 두 로봇
 * - 제출 날짜: 2026년 2월 5일
 * - 결과: 100점
 * - 메모리: 29104 KB
 * - 시간: 224 ms
 */

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    final static int COST = 1001;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int s = readInt();
        int e = readInt();

        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        inputNodes(nodes, n);
        System.out.println(bfs(nodes, n, s, e));
    }

    private static void inputNodes(ArrayList<ArrayList<Integer>> nodes, int n) throws IOException {
        for(int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < n - 1; i++) {
            int n1 = readInt();
            int n2 = readInt();
            int cost = readInt();
            nodes.get(n1).add(n2 * COST + cost);
            nodes.get(n2).add(n1 * COST + cost);
        }
    }

    private static int bfs(ArrayList<ArrayList<Integer>> nodes, int n, int s, int e) {
        int[] queue = new int[n];
        int[] value = new int[n];
        boolean[] visited = new boolean[n + 1];
        int head = 0, tail = 1;
        queue[head] = s * COST;
        value[head] = 0;
        visited[s] = true;

        while(head < tail) {
            int cur = queue[head] / COST;
            int maxCost = queue[head] % COST;
            int total = value[head];
            head++;

            if(cur == e) return total - maxCost;

            for(int next : nodes.get(cur)) {
                int node = next / COST;
                int nextCost = next % COST;
                if(visited[node]) continue;
                queue[tail] = node * COST + Math.max(maxCost, nextCost);
                value[tail] = total + nextCost;
                visited[node] = true;
                tail++;
            }
        }
        return -1;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
