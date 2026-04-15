package BaekJoon;

import java.io.*;

public class BaekJoon_11658 {

    static int[][] tree, arr;
    static int N, M, c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        M = readInt();

        // 펜윅 트리는 1-based 인덱스가 필수입니다 (0이면 무한 루프)
        tree = new int[N + 1][N + 1];
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int val = readInt();
                update(i, j, val);
            }
        }

        System.out.print(solve());
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            int command = readInt();
            if (command == 0) {
                int y = readInt();
                int x = readInt();
                int k = readInt();
                update(y, x, k);
            } else {
                int y1 = readInt();
                int x1 = readInt();
                int y2 = readInt();
                int x2 = readInt();

                // 2차원 구간 합 공식: sum(y2, x2) - sum(y1-1, x2) - sum(y2, x1-1) + sum(y1-1, x1-1)
                int result = getSum(y2, x2)
                        - getSum(y1 - 1, x2)
                        - getSum(y2, x1 - 1)
                        + getSum(y1 - 1, x1 - 1);
                sb.append(result).append('\n');
            }
        }
        return sb.toString();
    }

    // 값 변경 함수
    private static void update(int y, int x, int val) {
        int diff = val - arr[y][x]; // 기존 값과의 차이 계산
        arr[y][x] = val; // 원본 배열 갱신

        for (int i = y; i <= N; i += i & -i) {
            for (int j = x; j <= N; j += j & -j) {
                tree[i][j] += diff;
            }
        }
    }

    // (1, 1)부터 (y, x)까지의 누적합 함수
    private static int getSum(int y, int x) {
        int res = 0;
        for (int i = y; i > 0; i -= i & -i) {
            for (int j = x; j > 0; j -= j & -j) {
                res += tree[i][j];
            }
        }
        return res;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}