/**
 * [BOJ] 11003 - 최솟값 찾기
 * - 제출 날짜: 2026년 1월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 51700 KB
 * - 시간: 548 ms
 */

import java.util.*;
import java.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//Gemini 코드, 입출력 차이 확인용 코드

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력과 출력을 담당할 FastIO 객체 생성
        FastIO io = new FastIO();

        int n = io.nextInt();
        int m = io.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = io.nextInt();
        }

        // --- 여기부터 사용자님의 덱 로직 (변한 것 없음) ---
        int[] order = new int[n]; // 인덱스 저장용
        int left = 0, right = 0;

        for (int i = 0; i < n; i++) {
            // 1. 뒤에서 값 비교 (arr[i]가 더 작으면 뒤를 날림)
            while (left < right && arr[i] <= arr[order[right - 1]]) {
                right--;
            }
            
            // 2. 현재 인덱스 추가
            order[right++] = i;

            // 3. 앞에서 윈도우 벗어난 것 제거
            if (order[left] < i - m + 1) {
                left++;
            }

            // --- 출력 변경 (핵심!) ---
            // bw.write(...) 대신 io.writeInt(...) 사용
            io.writeInt(arr[order[left]]);
        }
        
        // 마지막 버퍼 비우기 (필수)
        io.flush();
    }

    // ===============================================
    // ★ 여기가 0.6초의 비밀 (FastIO 클래스)
    // ===============================================
    static class FastIO {
        private final InputStream in = System.in;
        private final OutputStream out = System.out;
        private final byte[] inBuffer = new byte[1 << 16];
        private final byte[] outBuffer = new byte[1 << 16];
        private int inPtr, inCount, outPtr;

        // [입력] 버퍼에서 한 바이트 읽기
        private int read() throws IOException {
            if (inPtr == inCount) {
                inPtr = 0;
                if ((inCount = in.read(inBuffer)) == -1) return -1;
            }
            return inBuffer[inPtr++];
        }

        // [입력] 정수 읽기 (nextInt)
        public int nextInt() throws IOException {
            int c, n = 0;
            boolean negative = false;
            while ((c = read()) <= 32); // 공백 건너뛰기
            if (c == '-') {
                negative = true;
                c = read();
            }
            do {
                n = (n << 3) + (n << 1) + (c & 15); // n*10 + (c-'0')
            } while ((c = read()) > 32);
            return negative ? -n : n;
        }

        // [출력] 정수 쓰기 (writeInt)
        public void writeInt(int n) throws IOException {
            // 버퍼 꽉 차면 비움
            if (outPtr + 12 >= outBuffer.length) flush(); 
            
            if (n < 0) {
                outBuffer[outPtr++] = (byte) '-';
                n = -n;
            }
            // 자릿수 계산 (재귀 없이 구현)
            int temp = n;
            int len = 0;
            do {
                len++;
                temp /= 10;
            } while (temp > 0);

            // 뒤에서부터 숫자 채우기
            int i = len;
            while (i > 0) {
                int q = n / 10;
                int r = n - (q * 10); // n % 10
                outBuffer[outPtr + --i] = (byte) (r + '0'); // 아스키 변환
                n = q;
            }
            outPtr += len;
            outBuffer[outPtr++] = (byte) ' '; // 공백 추가
        }

        public void flush() throws IOException {
            out.write(outBuffer, 0, outPtr);
            outPtr = 0;
        }
    }
}