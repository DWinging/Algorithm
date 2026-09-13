import java.io.*;
import java.util.*;

class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();

        while (t-- > 0) {
            int n = readInt();
            int m = readInt();
            int L = readInt();

            int[][] hurdle = new int[n][2];
            for (int i = 0; i < n; i++) {
                hurdle[i][0] = readInt();
                hurdle[i][1] = readInt();
            }

            int[][] power = new int[m][2];
            for (int i = 0; i < m; i++) {
                power[i][0] = readInt();
                power[i][1] = readInt();
            }

            sb.append(solve(hurdle, power)).append('\n');
        }

        System.out.print(sb);
    }

    private static int solve(int[][] hurdle, int[][] power) {
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        long jump = 1;
        int powerIdx = 0;
        int count = 0;

        for (int[] h : hurdle) {
            int left = h[0];
            int right = h[1];

            // 현재 장애물 이전에 획득 가능한 파워업 추가
            while (powerIdx < power.length && power[powerIdx][0] < left) {
                pq.offer(power[powerIdx][1]);
                powerIdx++;
            }

            // [left, right] 장애물을 완전히 넘어가기 위해 필요한 점프력
            long need = right - left + 2L;

            // 부족하면 지금까지 획득 가능한 파워업 중 가장 큰 것부터 사용
            while (jump < need && !pq.isEmpty()) {
                jump += pq.poll();
                count++;
            }

            if (jump < need) {
                return -1;
            }
        }

        return count;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') {
            c = System.in.read();
        }

        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}