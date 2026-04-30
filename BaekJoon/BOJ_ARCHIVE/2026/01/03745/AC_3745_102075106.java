/**
 * [BOJ] 3745 - 오름세
 * - 제출 날짜: 2026년 1월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 28808 KB
 * - 시간: 224 ms
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        String input = "";
        int n = 0;
        while ((input = br.readLine()) != null) {
            input = input.trim();
            if (input.isEmpty()) continue;
            n = Integer.parseInt(input);

            int[] arr = inputArray(n, st, br);
            bw.write(getLIS(arr, n) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int[] inputArray(int n, StringTokenizer st, BufferedReader br) throws IOException {
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int getLIS(int[] arr, int n) {
        int[] tails = new int[n];
        tails[0] = arr[0];
        int len = 1;

        for(int i = 1; i < n; i++) {
            if(arr[i] > tails[len-1]) {
                tails[len++] = arr[i];
            }
            else {
                int idx = binarySeach(tails, arr[i], len);
                tails[idx] = Math.min(tails[idx], arr[i]);
            }
        }
        return len;
    }

    private static int binarySeach(int[] tails, int cur, int len) {
        int left = 0, right = len - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(tails[mid] < cur) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}