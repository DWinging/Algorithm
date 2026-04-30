/**
 * [BOJ] 2470 - 두 용액
 * - 제출 날짜: 2026년 2월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 12812 KB
 * - 시간: 136 ms
 */

import java.util.*;
import java.io.IOException ;

class Main {

    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }

        System.out.println(solve(arr, n));
    }

    private static String solve(int[] arr, int n) {
        int left = 0, right = n-1;
        Arrays.sort(arr);

        int n1 = arr[left], n2 = arr[right];
        int value = 2_000_000_001;
        while(left < right) {
            int s = arr[left] + arr[right];
            
            if(s == 0) {
                n1 = arr[left];
                n2 = arr[right];
                break;
            }

            if(Math.abs(s) < value) {
                value = Math.abs(s);
                n1 = arr[left];
                n2 = arr[right];
            }

            if(s > 0) {
                right--;
            }
            else {
                left++;
            }
        }
        return n1 + " " + n2;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') {
            c = System.in.read();
        }

        boolean neg = false;
        if(c == '-') {
            neg = true;
            c = System.in.read();
        }
        
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }
        return neg ? -n : n;
    }
}