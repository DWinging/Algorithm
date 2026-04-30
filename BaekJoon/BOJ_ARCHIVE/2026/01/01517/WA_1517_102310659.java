/**
 * [BOJ] 1517 - 버블 소트
 * - 제출 날짜: 2026년 1월 26일
 * - 결과: 런타임 에러 (ArrayIndexOutOfBounds)
 */

import java.util.*;
import java.io.*;

import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }

        int[] temp = new int[n];
        int left, right, mid;
        long cnt = 0;
        
        for(int i = 2; i < n * 2; i *= 2) {
            for(int cur = 0; cur < n; cur += i) {
                left = cur;
                mid = cur + (i / 2) - 1;
                right = Math.min(cur + i - 1, n - 1);

                int idxL = cur;
                int idxR = mid + 1;
                int idx = left;
                while(idxL <= mid && idxR <= right) {
                    if(arr[idxL] <= arr[idxR]) {
                        temp[idx++] = arr[idxL++];
                    }
                    else {
                        cnt += (mid - idxL + 1);
                        temp[idx++] = arr[idxR++];
                    }
                }

                while(idxL <= mid) {
                    temp[idx++] = arr[idxL++];
                }
                while(idxR <= right) {
                    temp[idx++] = arr[idxR++];
                }

                for(int j = left; j <= right; j++) {
                    arr[j] = temp[j];
                }
            }
        }
        System.out.println(cnt);
    }

    private static int readInt() throws IOException {
        int c = System.in.read();

        while (c <= ' ') {
            c = System.in.read();
        }

        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }

        return n;
    }
}

