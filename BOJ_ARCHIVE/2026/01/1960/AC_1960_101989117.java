/**
 * [BOJ] 1960 - 행렬만들기
 * - 제출 날짜: 2026년 1월 16일
 * - 결과: 맞았습니다!!
 * - 메모리: 12756 KB
 * - 시간: 100 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class XCoord implements Comparable<XCoord> {
        int x;
        int cnt;

        XCoord(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(XCoord o) {
            if(this.cnt == o.cnt) Integer.compare(this.x, o.x);
            return Integer.compare(o.cnt, this.cnt);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        int[] y = inputArray(n, st, br);
        int[] x = inputArray(n, st, br);

        PriorityQueue<XCoord> pq = inputPriorityQueue(x);

        int[][] matrix = new int[n][n];
        System.out.println(solve(pq, matrix, y, n) ? printMatrix(matrix) : -1);
    }

    private static int[] inputArray(int n, StringTokenizer st, BufferedReader br) throws IOException {
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static PriorityQueue<XCoord> inputPriorityQueue(int[] arr) {
        PriorityQueue<XCoord> pq = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 0) pq.add(new XCoord(i, arr[i]));
        }
        return pq;
    }

    private static boolean solve(PriorityQueue<XCoord> pq, int[][] matrix, int[] yArr, int n) {
        Deque<XCoord> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < yArr[i]; j++) {
                if(!pq.isEmpty()) {
                    XCoord coord = pq.poll();
                    matrix[i][coord.x] = 1;
                    coord.cnt--;
                    if(coord.cnt > 0) deque.addLast(coord);
                }
                else return false;
            }

            while(!deque.isEmpty()) {
                pq.add(deque.pollFirst());
            }
        }
        return pq.isEmpty();
    }

    private static String printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append(1).append("\n");
        for(int[] arr : matrix) {
            for(int c : arr) {
                sb.append(c);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}