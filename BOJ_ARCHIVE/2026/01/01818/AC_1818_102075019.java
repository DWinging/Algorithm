/**
 * [BOJ] 1818 - 책정리
 * - 제출 날짜: 2026년 1월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 37600 KB
 * - 시간: 256 ms
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = inputArray(n, br);

        System.out.println(lis(arr, n));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int lis(int[] arr, int n) {
        int[] lis = new int[n];
        lis[0] = arr[0];
        int len = 1;

        for(int i = 1; i < n; i++) {
            if(arr[i] > lis[len-1]) {
                lis[len++] = arr[i];
            }
            else {
                int idx = binarySearch(lis, arr[i], len);
                lis[idx] = Math.min(lis[idx], arr[i]);
            }
        }
        return n - len;
    }

    private static int binarySearch(int[] lis, int cur, int len) {
        int left = 0, right = len - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(lis[mid] < cur) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}