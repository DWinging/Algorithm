class Solution {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;

        int end = (1 << n) - 1;
        boolean[][] visited = new boolean[n][1 << n];

        Queue<int[]> que = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int bit = 1 << i;

            que.add(new int[]{i, bit, 0});
            visited[i][bit] = true;
        }

        while (!que.isEmpty()) {
            int[] state = que.poll();

            int cur = state[0];
            int bit = state[1];
            int cnt = state[2];

            if (bit == end) return cnt;

            for (int next : graph[cur]) {
                int nextBit = bit | (1 << next);

                if (!visited[next][nextBit]) {
                    visited[next][nextBit] = true;
                    que.add(new int[]{next, nextBit, cnt + 1});
                }
            }
        }

        return -1;
    }
}