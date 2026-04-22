/**
 * [BOJ] 13544 - 수열과 쿼리 3
 * - 제출 날짜: 2026년 3월 6일
 * - 결과: 맞았습니다!!
 * - 메모리: 18708 KB
 * - 시간: 1560 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static int SIZE = 1000;
    static int[][] bucket;
    static int[] arr;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        init(n);
        System.out.print(solve());
    }

    private static void init(int n) throws IOException {
        bucket = new int[(n / SIZE) + 1][SIZE];
        arr = new int[n];

        Arrays.fill(bucket[n / SIZE], 1_000_000_005);

        for(int i = 0; i < n; i++) {
            int num = readInt();
            bucket[i / SIZE][i % SIZE] = num;
            arr[i] = num;
        }

        for(int i = 0; i <= (n / SIZE); i++) {
            Arrays.sort(bucket[i]);
        }
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt(), ans = 0;
        while(m-- > 0) {
            int i = (readInt() ^ ans) - 1;
            int j = (readInt() ^ ans) - 1;
            int k = (readInt() ^ ans);
            ans = searchQuery(i, j, k);
            sb.append(ans).append('\n');
        }
        return sb.toString();
    }

    private static int searchQuery(int s, int e, int target) {
        int cnt = 0;
        if(s / SIZE == e / SIZE) {
            cnt += search(s, e + 1, target);
        } else {
            for(int i = (s / SIZE) + 1; i < e / SIZE; i++) {
                int idx = binarySearch(i, target);
                cnt += (SIZE - idx);
            }

            cnt += search(s, (s / SIZE + 1) * SIZE, target);
            cnt += search((e / SIZE) * SIZE, e + 1, target);
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

    private static int search(int s, int e, int target) {
        int cnt = 0;
        while(s < e) if(arr[s++] > target) cnt++;
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
