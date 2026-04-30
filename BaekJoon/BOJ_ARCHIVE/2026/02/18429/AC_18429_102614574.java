/**
 * [BOJ] 18429 - 근손실
 * - 제출 날짜: 2026년 2월 4일
 * - 결과: 맞았습니다!!
 * - 메모리: 11664 KB
 * - 시간: 80 ms
 */

import java.io.IOException;
import java.util.Arrays;

class Main {    

    static int[] arr;
    static int total = 0;
    static int n, k, c;  
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        n = readInt();
        k = readInt();
        
        arr = new int[n];
        inputArray();
        Arrays.sort(arr);
        backTracking(0, 500);
        System.out.println(total);
    }

    private static void inputArray() throws IOException {
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
    }
    
    private static void backTracking(int cnt, int w) {
        if(cnt == n-1) {
            total++;
            return;
        }
        for(int i = n - 1; i >= 0; i--) {
            if(arr[i] < 0) continue;
            int value = w + arr[i] - k;
            if(value < 500) break;
            int temp = arr[i];
            arr[i] = -1;
            backTracking(cnt + 1, value);
            arr[i] = temp;
        }
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}