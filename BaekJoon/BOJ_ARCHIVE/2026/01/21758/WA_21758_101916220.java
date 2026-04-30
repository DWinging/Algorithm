/**
 * [BOJ] 21758 - 꿀 따기
 * - 제출 날짜: 2026년 1월 14일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        int idx = getMaxIdxAndgetSum(arr, n);
        System.out.println(solve(arr, n, idx));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int getMaxIdxAndgetSum(int[] arr, int n) {
        int idx = 0, value = 0;
        for(int i = 1; i < n - 1; i++) {
            if(arr[i] >= value) {
                value = arr[i];
                idx = i;
            }
            arr[i] += arr[i-1];
        }
        arr[n-1] += arr[n-2];
        return idx;
    }

    private static int solve(int[] arr, int n, int idx) {
        int value = (arr[idx] - arr[0]) + (arr[n-2] - arr[idx - 1]);

        int start = arr[0];
        int end = arr[n-1];
        for(int i = 1; i < n-1; i++) {
            int temp = (end - arr[i]) * 2 + arr[i-1] - start;
            value = Math.max(value, temp);
        }
        return value;
    }
}