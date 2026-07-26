import java.util.*;

class Solution {

    private class Node {
        int y, x, cnt;

        Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        visited[0][0] = 0;

        Deque<Node> deque = new ArrayDeque<>();
        deque.addFirst(new Node(0, 0, 0));

        int[][] dist = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int cy = node.y;
            int cx = node.x;
            int cnt = node.cnt;

            if(cnt > visited[cy][cx]) continue;
            if(cy == n - 1 && cx == m - 1) return cnt;

            for(int i = 1; i < 5; i++) {
                int ny = cy + dist[i][0];
                int nx = cx + dist[i][1];

                if(!check(ny, nx, n, m)) continue;

                if(grid[cy][cx] == i && visited[ny][nx] > cnt) {
                    deque.addFirst(new Node(ny, nx, cnt));
                    visited[ny][nx] = cnt;
                } else if(grid[cy][cx] != i && visited[ny][nx] > cnt + 1) {
                    deque.addLast(new Node(ny, nx, cnt + 1));
                    visited[ny][nx] = cnt + 1;
                }
            }
        }

        return visited[n - 1][m - 1];
    }

    private boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}