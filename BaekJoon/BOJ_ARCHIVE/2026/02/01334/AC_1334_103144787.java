/**
 * [BOJ] 1334 - 다음 팰린드롬 수
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11692 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solve(str));
    }

    private static String solve(String str) {
        boolean allNines = true;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '9') {
                allNines = false;
                break;
            }
        }
        if (allNines) {
            StringBuilder sb = new StringBuilder("1");
            for (int i = 0; i < str.length() - 1; i++) {
                sb.append("0");
            }
            sb.append("1");
            return sb.toString();
        }

        int len = str.length();
        String left = str.substring(0, (len + 1) / 2);
        String mirrored = makePalindrome(left, len % 2 != 0);

        if (mirrored.compareTo(str) > 0) {
            return mirrored;
        }
        
        BigInteger leftNum = new BigInteger(left);
        leftNum = leftNum.add(BigInteger.ONE);
        
        return makePalindrome(leftNum.toString(), len % 2 != 0);
    }

    private static String makePalindrome(String left, boolean isOdd) {
        StringBuilder sb = new StringBuilder(left);
        String rightPart = isOdd ? left.substring(0, left.length() - 1) : left;
        
        String right = new StringBuilder(rightPart).reverse().toString();
        sb.append(right);
        
        return sb.toString();
    }
}