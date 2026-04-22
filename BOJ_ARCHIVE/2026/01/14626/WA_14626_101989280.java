/**
 * [BOJ] 14626 - ISBN
 * - 제출 날짜: 2026년 1월 16일
 * - 결과: 틀렸습니다
 */

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String isbn = br.readLine();

        int sum = 0, idx = 0, m = isbn.charAt(isbn.length() - 1) - '0';
        for(int i = 0; i < isbn.length()-1; i++) {
            if(isbn.charAt(i) == '*') idx = i;
            else sum += i % 2 == 0 ? isbn.charAt(i) - '0' : (isbn.charAt(i) - '0') * 3;
        }

        System.out.println(solve(sum, idx, m));
    }    

    private static int solve(int sum, int idx, int m) {
        int w = idx % 2 == 0 ? 1 : 3;
        for(int i = 0; i < 10; i++) {
            if(m == (10 - (sum + i * w) % 10)) return i;
        }
        return -1;
    }
}