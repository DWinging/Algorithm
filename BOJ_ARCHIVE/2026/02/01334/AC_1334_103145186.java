/**
 * [BOJ] 1334 - 다음 팰린드롬 수
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11528 KB
 * - 시간: 64 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solve(str));
    }

    private static String solve(String str) {
        int[] num = new int[str.length() + 1];
        if(checkAllNine(num, str)) {
            StringBuilder sb = new StringBuilder().append('1');
            for (int i = 0; i < str.length() - 1; i++) {
                sb.append('0');
            }
            sb.append('1');
            return sb.toString();
        }

        int len = str.length();
        int left = len / 2 + 1;
        String mirrored = makePalindrome(num, left, len % 2 != 0);

        if (mirrored.compareTo(str) > 0) {
            return mirrored;
        }

        int target = (len + 1) / 2;
        num[target] += 1;
        while(num[target] == 10) {
            num[target--] = 0;
            num[target] += 1;
        }
        return makePalindrome(num, left, len % 2 != 0);
    }

    private static boolean checkAllNine(int[] num, String str) {
        boolean flag = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '9') flag = false;
            num[i + 1] = c - '0';
        }
        return flag;
    }

    private static String makePalindrome(int[] num, int left, boolean isOdd) {
        StringBuilder sb = new StringBuilder();
        if(num[0] != 0) sb.append(num[0]);
        for(int i = 1; i < left; i++) sb.append(num[i]);
        if(isOdd) sb.append(num[left]);
        for(int i = left-1; i >= 1; i--) sb.append(num[i]);
        if(num[0] != 0) sb.append(num[0]);
        return sb.toString();
    }
}