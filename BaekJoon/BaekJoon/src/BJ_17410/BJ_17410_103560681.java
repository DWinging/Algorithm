package BJ_17410;

/**
 *  2026년 3월 6일 풀이
 * BaekJoon_17410 수열과 쿼리 1.5
 * 메모리 247608 KB
 * 시간 1984 ms
 */
import java.io.*;
import java.util.*;

public class BJ_17410_103560681 {

    final static int SIZE = 500;
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
        int temp = (n / SIZE) + 1;
        bucket = new int[temp][SIZE];
        arr = new int[n];

        Arrays.fill(bucket[n / SIZE], 100_000);

        for(int i = 0; i < n; i++) {
            int num = readInt();
            bucket[(i / SIZE)][(i % SIZE)] = num;
            arr[i] = num;
        }

        for(int i = 0; i < temp; i++)
            Arrays.sort(bucket[i]);
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int command = readInt();
            if(command == 1) {
                int idx = readInt()-1;
                int k = readInt();
                updateQuery(idx, k);
            } else {
                int i = readInt() - 1;
                int j = readInt() - 1;
                int k = readInt();
                sb.append(searchQuery(i, j, k)).append('\n');
            }
        }
        return sb.toString();
    }

    private static void updateQuery(int i, int target) {
        int bIdx = i / SIZE;
        int idx = binarySearch(bIdx, arr[i]);
        bucket[bIdx][idx - 1] = target;
        Arrays.sort(bucket[bIdx]);
        arr[i] = target;
    }

    private static int searchQuery(int s, int e, int target) {
        int cnt = 0;
        if(s / SIZE == e / SIZE) {
            for(int i = s; i <= e; i++)
                if(arr[i] > target) cnt++;
        } else {
            for(int i = (s / SIZE) + 1; i < (e / SIZE); i++) {
                int idx = binarySearch(i, target);
                cnt += (SIZE - idx);
            }

            int temp = (s / SIZE + 1) * SIZE;
            for(int i = s; i < temp; i++)
                if(arr[i] > target) cnt++;

            temp = (e / SIZE) * SIZE;
            for(int i = temp; i <= e; i++)
                if(arr[i] > target) cnt++;
        }
        return cnt;
    }

    private static int binarySearch(int bIdx, int target) {
        int left = 0, right = SIZE;
        while(left < right) {
            int mid = (left + right) / 2;
            if(bucket[bIdx][mid] <= target) left = mid + 1;
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