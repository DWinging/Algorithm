/**
 * [BOJ] 18428 - 감시 피하기
 * - 제출 날짜: 2026년 2월 5일
 * - 결과: 맞았습니다!!
 * - 메모리: 11540 KB
 * - 시간: 64 ms
 */

import java.io.IOException;
import java.util.Arrays;

class Main {

    static int[][] DICT = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int c, idx, emptyIdx, total = 3;
    static boolean flag = false;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int[] teachers = new int[10];
        Arrays.fill(teachers, -1);

        char[] arr = new char[n * n];
        inputState(arr, teachers, n);
        
        solve(arr, teachers, n);
        System.out.println(flag ? "YES" : "NO");   
    }

    private static void inputState(char[] arr, int[] teachers, int n) throws IOException {
        idx = 0;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                while(c <= ' ') c = System.in.read();
                arr[y * n + x] = (char) c;
                if(c == 'T') teachers[idx++] = y * n + x;
                c = System.in.read();
            }
        }
    }

    private static void solve(char[] arr, int[] teachers, int n) {
        for(int i = 0; i < idx; i++) {
            int y = teachers[i] / n;
            int x = teachers[i] % n;
            for(int[] d : DICT) {
                int ny = y + d[0];
                int nx = x + d[1];
                if(check(ny, nx, n) && arr[ny * n + nx] == 'S') return;
            }
        }
        backtracking(arr, teachers, 0, 0, n);
     }

    private static void backtracking(char[] arr, int[] teachers, int idx, int cnt, int n) {
        if(cnt == total) {
            if(checkStudents(arr, teachers, n)) flag = true;
            return;
        }
        for(int i = idx; i < n * n; i++) {
            if(arr[i] != 'X') continue;
            arr[i] = 'O';
            backtracking(arr, teachers, i + 1, cnt + 1, n);
            if(flag) return;
            arr[i] = 'X';            
        }
    }

    private static boolean checkStudents(char[] arr, int[] teachers, int n) {
        for(int i = 0; i < idx; i++) {
            if(!search(arr, teachers[i], n)) return false;
        }
        return true;
    }

    private static boolean search(char[] arr, int cur, int n) {
        int y = cur / n;
        int x = cur % n;
        for(int[] d : DICT) {
            for(int i = 1; i < n; i++) {
                int ny = y + d[0] * i;
                int nx = x + d[1] * i;
                if(check(ny, nx, n)) {
                    if(arr[ny * n + nx] == 'T' || arr[ny * n + nx] == 'O') break;
                    if(arr[ny * n + nx] == 'S') return false;
                } 
            }    
        }
        return true;
    }

    private static boolean check(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x < n;
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