/**
 * [BOJ] 10808 - 알파벳 개수
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 11444 KB
 * - 시간: 64 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[26]; int c;
        while((c = System.in.read()) > ' ') arr[c-'a']++;
        for(int i : arr) sb.append(i).append(' ');
        System.out.print(sb);
    }
}