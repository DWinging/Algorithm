/**
 * [BOJ] 18114 - 블랙 프라이데이
 * - 제출 날짜: 2026년 4월 27일
 * - 결과: 맞았습니다!!
 * - 메모리: 13088 KB
 * - 시간: 84 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static int input;
    
    public static void main(String[] args) throws IOException {
        input = System.in.read();
        int n = readInt();
        int c = readInt();
        int[] arr = inputArray(n);
        System.out.println(solve(arr, n, c));
    }

    private static int[] inputArray(int n) throws IOException {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        return arr;
    }

    private static int solve(int[] arr, int n, int c) {
        for(int i = 0; i < n; i++) {
            if(arr[i] == c) return 1;
        }

        Arrays.sort(arr);

        int left = 0, right = n - 1;
        while(left < right) {
            int val = arr[left] + arr[right];
            if(val == c) return 1;
            else if(val > c) right--;
            else {
                int diff = c - val;
                int start = 0, end = 0;
                if(diff < arr[left]) {
                    start = 0; end = left - 1;
                } else if(arr[left] < diff && diff < arr[right]) {
                    start = left + 1; end = right - 1;
                } else if(arr[right] < diff) {
                    start = right + 1; end = n - 1;
                }

                if(binarySearch(arr, start, end, diff)) return 1;
                left++;       
            }            
        }
        
        return 0;
    }

    private static boolean binarySearch(int[] arr, int l, int r, int target) {
        int left = l, right = r, mid = (l + r) >> 1;
        while(left <= right) {
            mid = (left + right) >> 1;
            if(arr[mid] == target) return true;

            if(arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    private static int readInt() throws IOException {
        while(input <= ' ') input = System.in.read();
        int n = 0;
        while(input >= '0' && input <= '9') {
            n = (n << 3) + (n << 1) + (input & 15);
            input = System.in.read();
        }
        return n;
    }
}