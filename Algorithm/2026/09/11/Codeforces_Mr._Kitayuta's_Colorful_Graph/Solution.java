import java.util.*;
import java.io.*;

class Main {

    private static class Node {
        int x, color;

        public Node(int x, int color) {
            this.x = x;
            this.color = color;
        }
    }
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        List<Node>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            int a = readInt();
            int b = readInt();
            int color = readInt();

            list[a].add(new Node(b, color));
            list[b].add(new Node(a, color));
        }

        StringBuilder sb = new StringBuilder();
        int q = readInt();
        int[][] visited = new int[n + 1][m + 1];
        for(int i = 1; i <= q; i++) {
            int s = readInt();
            int e = readInt();
            sb.append(bfs(list, visited, s, e, i)).append('\n');
        }
        System.out.println(sb);
    }

    private static int bfs(
        List<Node>[] list,
        int[][] visited,
        int s, int e, int mark
    ) {
        Queue<Node> que = new ArrayDeque<>();
        int cnt = 0;

        for(Node next : list[s]) {
            que.add(next);
            visited[next.x][next.color] = mark;
        }

        while(!que.isEmpty()) {
            Node cur = que.poll();
            int x = cur.x;
            int color = cur.color;
            if(x == e) {
                cnt++;
                continue;
            }

            for(Node next : list[x]) {
                if(color == next.color && visited[next.x][color] < mark) {
                    que.add(next);
                    visited[next.x][color] = mark;
                }
            }
        }

        return cnt;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}