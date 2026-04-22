/**
 * [BOJ] 1334 - 다음 팰린드롬 수
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] num = new int[str.length() + 2];
        System.out.println(solve(num, str));
    }

    private static String solve(int[] num, String str) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= str.length(); i++) {
            num[i] = str.charAt(i-1) - '0';
        }

        int idx = num.length / 2;
        num[idx] += 1;
        while(num[idx] >= 10) {
            num[idx--] = 0;
            num[idx] += 1;
        }

        if(num[0] != 0) sb.append(num[0]);
        for(int i = 1; i < num.length / 2; i++) sb.append(num[i]);
        if(num.length % 2 == 1) sb.append(num[num.length / 2]);
        for(int i = num.length / 2 - 1; i >= 1; i--) sb.append(num[i]);
        if(num[0] != 0) sb.append(num[0]);
        return sb.toString();
    }
}