/**
 * [BOJ] 11003 - 최솟값 찾기
 * - 제출 날짜: 2026년 1월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 644568 KB
 * - 시간: 1992 ms
 */

import java.util.StringTokenizer;
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

        int[] order = new int[n];
        int left = 0, right = 0;

        for(int i = 0; i < n; i++) {
            while(left < right && arr[i] <= arr[order[right - 1]]) {
                right--;
            }

            order[right++] = i;

            while(order[left] <  i - m + 1) {
                left++;
            }
            sb.append(arr[order[left]]).append(" ");
        }
        System.out.println(sb);
    }
}
