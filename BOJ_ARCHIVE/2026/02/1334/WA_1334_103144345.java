/**
 * [BOJ] 1334 - 다음 팰린드롬 수
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 런타임 에러 (NumberFormat)
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int num = Integer.parseInt(str);
        num += (int) Math.pow(10, (str.length() - 1) / 2);
        str = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length() / 2; i++) sb.append(str.charAt(i));
        if(str.length() % 2 == 1) sb.append(str.charAt(str.length() / 2));
        for(int i = str.length() / 2 - 1; i >= 0; i--) sb.append(str.charAt(i));
        System.out.println(sb);
    }
}