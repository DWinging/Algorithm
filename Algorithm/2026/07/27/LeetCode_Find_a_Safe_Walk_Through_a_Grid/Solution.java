class Solution {

    private class Node {
        int y, x, h;

        Node(int y, int x, int h) {
            this.y = y;
            this.x = x;
            this.h = h;
        }
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        if(health - grid.get(0).get(0) <= 0) return false;
        int inf = health + 1;
        Deque<Node> deque = new ArrayDeque<>();
        deque.addFirst(new Node(0, 0, health - grid.get(0).get(0)));
        grid.get(0).set(0, inf);

        int n = grid.size(), m = grid.get(0).size();
        int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int cy = node.y;
            int cx = node.x;
            int ch = node.h;

            if(cy == n - 1 && cx == m - 1) return true;

            for(int[] d : dist) {
                int ny = cy + d[0];
                int nx = cx + d[1];

                if(check(ny, nx, n, m) && ch - grid.get(ny).get(nx) > 0) {
                    if(grid.get(ny).get(nx) == 0) {
                        deque.addFirst(new Node(ny, nx, ch));
                    } else {
                        deque.addLast(new Node(ny, nx, ch - 1));
                    }
                    grid.get(ny).set(nx, inf);
                }
            }
        }

        return false;
    }

    private boolean check(int y, int x, int n, int m) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}