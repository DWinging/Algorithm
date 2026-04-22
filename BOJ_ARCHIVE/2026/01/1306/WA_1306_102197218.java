/**
 * [BOJ] 1306 - 달려라 홍준
 * - 제출 날짜: 2026년 1월 22일
 * - 결과: 시간 초과
 */

import java.util.*;
import java.io.*;

class Main {

    final static int RANGE = 1_000_000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = inputArray(n, st, br);
        System.out.println(solve(arr, n, m));
    }

    private static int[] inputArray(int n, StringTokenizer st, BufferedReader br) throws IOException {
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static String solve(int[] arr, int n, int m) {
        StringBuilder sb = new StringBuilder();
        
        int[] cnt = new int[RANGE + 1];
        int power = 0;
        for(int i = 0; i < 2 * m - 1; i++) {
            cnt[arr[i]]++;
            power = Math.max(arr[i], power);
        }

        int left = 0, right = 2 * m - 1;
        sb.append(power).append(" ");
        while(right < n) {
            cnt[arr[right]]++;
            cnt[arr[left]]--;
            if(arr[right] > power) power = arr[right];
            if(arr[left] == power && cnt[arr[left]] == 0) {
                power = findValue(cnt, power);
            }
            sb.append(power).append(" ");
            right++;
            left++;
        }
        return sb.toString();
    }

    private static int findValue(int[] arr, int power) {
        for(int i = power; i >= 0; i--) {
            if(arr[i] > 0) return i;
        }
        return 0;
    }
}