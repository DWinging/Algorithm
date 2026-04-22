/**
 * [BOJ] 1244 - 스위치 켜고 끄기
 * - 제출 날짜: 2026년 3월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 11600 KB
 * - 시간: 68 ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		boolean[] result = new boolean[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int value = Integer.parseInt(st.nextToken());
			result[i] = value == 1;
		}
		
		int m = Integer.parseInt(br.readLine());
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			if(command == 1) {
				for(int i = idx; i <= n; i += idx) {
					result[i] = !result[i];
				}
			} else {
				result[idx] = !result[idx];
				int left = idx - 1;
				int right = idx + 1;
				while(1 <= left && right <= n) {
					if(result[left] == result[right]) {
						result[left] = !result[left];
						result[right] = !result[right];
					} else {
						break;
					}
					left--;
					right++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(result[i] ? 1 : 0).append(' ');
			if(i % 20 == 0) {
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
}
