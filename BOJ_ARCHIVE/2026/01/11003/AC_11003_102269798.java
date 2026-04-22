/**
 * [BOJ] 11003 - 최솟값 찾기
 * - 제출 날짜: 2026년 1월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 550756 KB
 * - 시간: 2064 ms
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] order = new int[5_000_000];
        int left = 0, right = 0;

        for(int i = 0; i < n; i++) {
            while(left < right && arr[i] <= arr[order[right - 1]]) {
                right--;
            }

            order[right++] = i;

            while(order[left] <  i - m + 1) {
                left++;
            }
            bw.write(arr[order[left]] + " ");
        }
        bw.flush();
        bw.close();
    }
}
