package BJ_16496;

import java.util.*;
import java.io.*;
/**
 * 2026년 1월 21일 풀이
 * BaekJoon_16496 큰 수 만들기
 * 메모리 22580 KB
 * 시간 228 ms
 */
public class BJ_16496_102158034 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<String> list = new ArrayList<>();
        while(st.hasMoreTokens()) list.add(st.nextToken());

        list.sort((a, b) -> (b + a).compareTo(a + b));

        StringBuilder sb = new StringBuilder();
        for(String s : list) sb.append(s);
        System.out.println(sb.toString().charAt(0) == '0' ? 0 : sb);
    }
}
