/**
 * [BOJ] 1700 - 멀티탭 스케줄링
 * - 제출 날짜: 2026년 1월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11600 KB
 * - 시간: 64 ms
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = inputArray(k, st, br);
        System.out.println(solve(arr, n, k));
    }

    private static int[] inputArray(int k, StringTokenizer st, BufferedReader br) throws IOException {
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[k];
        for(int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int solve(int[] arr, int n, int k) {
        int[][] concents = new int[n][2];
        Set<Integer> set = new HashSet<>();

        int idx = 0, port = 0;
        while(idx < k && set.size() < n) {
            if(set.add(arr[idx])) concents[port++][0] = arr[idx];
            idx++;
        }

        if(idx == k) return 0;

        for(int i = 0; i < n; i++) {
            int temp = checkUsingNext(arr, concents[i][0], idx, k);
            concents[i][1] = temp;
        }

        int cnt = 0;
        for(int i = idx; i < k; i++) {
            int portNo = checkConcents(concents, arr[i], n);
            if(concents[portNo][0] != arr[i]) {
                cnt++;
            }
            concents[portNo][0] = arr[i];
            
            int temp = checkUsingNext(arr, concents[portNo][0], i + 1, k);
            concents[portNo][1] = temp;
        }

        return cnt;
    }

    private static int checkUsingNext(int[] arr, int target, int idx, int k) {
        for(int i = idx; i < k; i++) {
            if(arr[i] == target) return i;
        }
        return k;
    }

    private static int checkConcents(int[][] concents, int target, int n) {
        int idx = 0, port = -1;
        for(int i = 0; i < n; i++) {
            if(concents[i][0] == target) return i;

            if(concents[i][1] > port) {
                idx = i;
                port = concents[i][1];
            }
        }
        return idx;
    }
}