class Solution {

    private static final int INF = -200_000_005;

    public int findMaxValueOfEquation(int[][] points, int k) {
        int n = points.length;

        int[] deque = new int[n];
        int front = 0;
        int rear = 0;

        int res = INF;

        for (int i = 0; i < n; i++) {
            int cx = points[i][0];
            int cy = points[i][1];

            while (front < rear) {
                int idx = deque[front];

                if (cx - points[idx][0] > k) {
                    front++;
                } else {
                    break;
                }
            }

            if (front < rear) {
                int idx = deque[front];
                int value = points[idx][1] - points[idx][0];

                res = Math.max(res, value + cy + cx);
            }

            int currentValue = cy - cx;

            while (front < rear) {
                int idx = deque[rear - 1];
                int value = points[idx][1] - points[idx][0];

                if (value <= currentValue) {
                    rear--;
                } else {
                    break;
                }
            }

            deque[rear++] = i;
        }

        return res;
    }
}