/**
 * [BOJ] 1105 - 팔
 * - 제출 날짜: 2026년 3월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 11524 KB
 * - 시간: 64 ms
 */

import java.io.*;
import java.util.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String n1 = st.nextToken();
        String n2 = st.nextToken();
        System.out.println(solve(n1, n2));
    }

    private static int solve(String n1, String n2) {
        if(n1.length() != n2.length()) return 0;

        int cnt = 0;
        for(int i = 0; i < n1.length(); i++) {
            if(n1.charAt(i) != n2.charAt(i)) break;
            else if(n1.charAt(i) == '8') cnt++;
        }
        return cnt;
    }
}