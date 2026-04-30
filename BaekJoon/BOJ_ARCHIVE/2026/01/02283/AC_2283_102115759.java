/**
 * [BOJ] 2283 - 구간 자르기
 * - 제출 날짜: 2026년 1월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 16220 KB
 * - 시간: 108 ms
 */

import java.util.*;
import java.io.*;

class Main {

    final static int MAX_RANGE = 1_000_000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = inputArray(n, st, br);

        System.out.println(solve(arr, k));
    }

    private static int[] inputArray(int n, StringTokenizer st, BufferedReader br) throws IOException {
        int[] arr = new int[MAX_RANGE + 2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a]++;
            arr[b]--;
        }

        for(int i = 1; i < arr.length; i++) {
            arr[i] += arr[i-1];
        }
        return arr;
    }

    private static String solve(int[] arr, int k) {
        int left = 0, right = 0, value = 0;
        while(left < arr.length && right < arr.length) {
            value += arr[right++];

            while(value > k) {
                value -= arr[left++];
            }
            
            if(value == k) 
                return left + " " + right;
        }
        return "0 0";
    }
}