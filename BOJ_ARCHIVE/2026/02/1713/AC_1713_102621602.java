/**
 * [BOJ] 1713 - 후보 추천하기
 * - 제출 날짜: 2026년 2월 4일
 * - 결과: 맞았습니다!!
 * - 메모리: 11424 KB
 * - 시간: 60 ms
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {    

    final static int MAX_RANGE = 100;
    static int c, n, k;
    static int[] check, arr, cnts, times;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        n = readInt();
        k = readInt();
        
        display();
        System.out.println(buildString());
    }

    private static void display() throws IOException {
        check = new int[MAX_RANGE + 1];
        Arrays.fill(check, -1);

        arr = new int[n];
        cnts = new int[n];
        times = new int[n];
        
        int size = 0;
        
        for(int i = 0; i < k; i++) {
            int num = readInt();
            if(check[num] != -1) {
                cnts[check[num]]++;
                continue;
            }
        
            if(size < n) {
                displayPicture(num, size, 1, i);
                size++;
            }
            else {
                int minIdx = searchMinIdx();
                check[arr[minIdx]] = -1;
                displayPicture(num, minIdx, 1, i);
            }
        }
    }

    private static void displayPicture(int num, int idx, int cnt, int order) {
        arr[idx] = num;
        check[num] = idx;
        cnts[idx] = cnt;
        times[idx] = order; 
    }
    
    private static int searchMinIdx() {
    	int minIdx = 0;
    	for(int j = 0; j < n; j++) {
            if(cnts[j] < cnts[minIdx]) minIdx = j;
            else if(cnts[j] == cnts[minIdx] && times[j] < times[minIdx]) minIdx = j;
        }
    	return minIdx;
    }

    private static String buildString() {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(int j : arr) {
            if(j == 0) continue;
            sb.append(j).append(' ');
        }
        return sb.toString();
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