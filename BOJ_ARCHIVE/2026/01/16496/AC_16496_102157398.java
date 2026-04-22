/**
 * [BOJ] 16496 - 큰 수 만들기
 * - 제출 날짜: 2026년 1월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 18560 KB
 * - 시간: 176 ms
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<String> arr = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arr.add(st.nextToken());
        }
        
        Collections.sort(arr, (str1, str2) -> {
            if(str1.length() == str2.length()) return str1.compareTo(str2);

            for(int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
                char c1 = str1.charAt(i);
                char c2 = str2.charAt(i);
                if(c1 > c2 || c1 < c2) {
                    return Integer.compare(c1 - '0', c2 - '0');
                }
            }

            return (str1 + str2).compareTo(str2 + str1);
        });

        StringBuilder sb = new StringBuilder();
        for(int i = n - 1; i >= 0; i--) {
            sb.append(arr.get(i));
        }
        System.out.println(sb.toString().charAt(0) == '0' ? 0 : sb);
    }
}