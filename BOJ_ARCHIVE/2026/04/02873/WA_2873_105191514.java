/**
 * [BOJ] 2873 - 롤러코스터
 * - 제출 날짜: 2026년 4월 27일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    static int input;

    public static void main(String[] args) throws IOException {
        input = System.in.read();
        int r = readInt();
        int c = readInt();

        if((r & 1) == 1) {
            System.out.println(buildString(r, c, 'R', 'D', 'L'));
        } else if((c & 1) == 1) {
            System.out.println(buildString(c, r, 'D', 'R', 'U'));
        } else {
            System.out.println(solve(r, c));
        }
    }

    private static String buildString(int r, int c, char d1, char d2, char d3) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < r; i++) {
            char d = (i & 1) == 0 ? d1 : d3;
            for(int j = 0; j < c - 1; j++) {
                sb.append(d);
            }
            if(i < r - 1) sb.append(d2);
        }
        return sb.toString();
    }

    private static String solve(int r, int c) throws IOException {
        int res = 1005, y = - 1, x = - 1;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                int val = readInt();
                if(((i + j) & 1) == 1 && res > val) {
                    res = val;
                    y = i; x = j;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for(int i = 0; i < r; i += 2) {
            if(i != y && (i + 1) != y) {
                if(flag) buildString(sb, c, 'R', 'D', 'L');
                else buildString(sb, c, 'L', 'D', 'R');
                if(i < r - 1) sb.append('D');
            } else {
                flag = false;
                for (int j = 0; j < x; j++) {
                    if ((j & 1) == 0) sb.append('D');
                    else sb.append('U');
                    sb.append('R');
                }

                for(int j = x; j < c - 1; j++) {
                    sb.append('R');
                    if ((j & 1) == 0) sb.append('D');
                    else sb.append('U');
                }

                if(i < r - 2) sb.append('D');
            }
        }

        return sb.toString();
    }

    private static void buildString(StringBuilder sb, int c, char d1, char d2, char d3) {
        for(int j = 0; j < c - 1; j++) sb.append(d1);
        sb.append(d2);
        for(int j = 0; j < c - 1; j++) sb.append(d3);
    }

    private static int readInt() throws IOException {
        while(input <= ' ') input = System.in.read();
        int n = 0;
        while(input >= '0' &&  input <= '9') {
            n = (n << 3) + (n << 1) + (input & 15);
            input = System.in.read();
        }
        return n;
    }
}
