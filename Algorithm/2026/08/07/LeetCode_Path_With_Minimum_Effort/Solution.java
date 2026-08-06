class Solution {

    final static int INF = 1_000_005;

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] effort = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                effort[i][j] = INF;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return (a[2] - b[2]);
        });
        pq.add(new int[]{0, 0, 0});
        effort[0][0] = 0;

        int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
 
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cy = cur[0];
            int cx = cur[1];
            int e = cur[2];

            if(cy == n - 1 && cx == m - 1) return e;
            if(effort[cy][cx] < e) continue;

            for(int[] d : dist) {
                int ny = cy + d[0];
                int nx = cx + d[1];

                if(check(ny, nx, n, m)) {
                    int h = Math.abs(heights[ny][nx] - heights[cy][cx]);
                    int val = h > e ? h : e;
                    if(effort[ny][nx] > val) {
                        pq.add(new int[]{ny, nx, val});
                        effort[ny][nx] = val;
                    }
                }
            }
        }

        return -1;
    }

    private boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}