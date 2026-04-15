package BJ_14504;

/**
 *  2026년 3월 5일 풀이
 * BaekJoon_14504 수열과 쿼리 18
 * 메모리 96720 KB
 * 시간 1168 ms
 */
import java.io.*;
import java.util.*;

public class BJ_14504_103528834 {

    final static int SIZE = 316;
    static int[][] bucket;
    static int[] arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        init(n);
        System.out.print(solve(n));
    }

    private static void init(int n) throws IOException {
        bucket = new int[(n / SIZE) + 1][SIZE];
        arr = new int[n];
        Arrays.fill(bucket[n / SIZE], Integer.MAX_VALUE);

        for(int i = 0; i < n; i++) {
            int num = readInt();
            bucket[i / SIZE][i % SIZE] = num;
            arr[i] = num;
        }

        for(int i = 0; i <= n / SIZE; i++)
            Arrays.sort(bucket[i]);
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int command = readInt();
            if(command == 1) {
                int i = readInt() - 1;
                int j = readInt() - 1;
                int k = readInt();
                sb.append(countQuery(n, i, j, k)).append('\n');
            } else {
                int i = readInt() - 1;
                int k = readInt();
                updateQuery(i, k);
            }
        }
        return sb.toString();
    }

    private static int countQuery(int n, int s, int e, int target) {
        int cnt = 0;

        if(s / SIZE == e / SIZE) {
            for(int i = s; i <= e; i++)
                if(arr[i] > target) cnt++;

        } else {
            for(int i = s / SIZE + 1; i < e / SIZE; i++) {
                int idx = binarySearch(i, target);
                cnt += SIZE - idx;
            }

            int b = s / SIZE + 1;
            for(int i = s; i < b * SIZE; i++)
                if(arr[i] > target) cnt++;

            b = e / SIZE;
            for(int i = b * SIZE; i <= e; i++)
                if(arr[i] > target) cnt++;
        }
        return cnt;
    }

    private static void updateQuery(int i, int value) {
        int b = i / SIZE;
        int idx = binarySearch(b, arr[i]);
        bucket[b][idx-1] = value;
        Arrays.sort(bucket[b]);
        arr[i] = value;
    }

    // upper bound
    private static int binarySearch(int b, int target) {
        int left = 0, right = SIZE;
        while(left < right) {
            int mid = (left + right) / 2;
            int value = bucket[b][mid];

            if(value <= target) left = mid + 1;
            else right = mid;
        }
        return right;
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
