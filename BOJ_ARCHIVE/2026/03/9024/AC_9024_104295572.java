/**
 * [BOJ] 9024 - 두 수의 합
 * - 제출 날짜: 2026년 3월 25일
 * - 결과: 맞았습니다!!
 * - 메모리: 26964 KB
 * - 시간: 932 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] arr = new int[1_000_000];
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            int k = readInt();
            inputArr(n);
            Arrays.sort(arr, 0, n);
            sb.append(solve(n, k)).append('\n');
        }
        System.out.print(sb);
    }

    private static void inputArr(int n) throws IOException {
        while(n-- > 0) arr[n] = readInt();
    }

    private static int solve(int n, int k) {
        int cnt = 0, val = 1_000_000_000;
        int left = 0, right = n-1;
        while(left < right) {
            int s = arr[left] + arr[right];
            int temp = Math.abs(k - s);
            
            if(temp < val) {
                val = temp;
                cnt = 1;
            } else if(temp == val) {
                cnt++;
            }

            if(s < k) left++;
            else right--;
        }
        return cnt;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = c == '-';
        if(c == '-') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}
