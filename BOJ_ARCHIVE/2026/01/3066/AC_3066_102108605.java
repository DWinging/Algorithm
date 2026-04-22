/**
 * [BOJ] 3066 - 브리징 시그널
 * - 제출 날짜: 2026년 1월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 29736 KB
 * - 시간: 240 ms
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = inputArray(n, br);
            bw.write(countPort(arr, n) + "\n");    
        }
        bw.flush();
        bw.close();
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        return arr;
    }

    private static int countPort(int[] arr, int n) {
        int[] tails = new int[n];
        tails[0] = arr[0];
        int cnt = 1;

        for(int i = 1; i < n; i++) {
            if(tails[cnt-1] < arr[i]) tails[cnt++] = arr[i];
            else {
                int idx = binarySearch(tails, arr[i], cnt);
                tails[idx] = Math.min(tails[idx], arr[i]);
            }
        }
        return cnt;
    }

    private static int binarySearch(int[] tails, int value, int cnt) {
        int left = 0, right = cnt-1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(tails[mid] < value) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}