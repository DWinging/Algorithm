/**
 * [BOJ] 11003 - 최솟값 찾기
 * - 제출 날짜: 2026년 1월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 855064 KB
 * - 시간: 2180 ms
 */

import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new ArrayDeque();

        for(int i = 0; i < n; i++) {
            while(!deque.isEmpty() && arr[i] <= arr[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.addLast(i);

            while(deque.peekFirst() <  i - m + 1) {
                deque.pollFirst();
            }
            sb.append(arr[deque.peekFirst()]).append(" ");
        }
        System.out.println(sb);
    }
}
