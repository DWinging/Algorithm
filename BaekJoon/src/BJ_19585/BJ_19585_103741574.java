package BJ_19585;

import java.io.*;
/**
 *  2026년 3월 6일 풀이
 * BaekJoon_19585 전설
 * 메모리 1163256 KB
 * 시간 2476 ms
 */
public class BJ_19585_103741574 {

    static int MAX_SIZE = 4_000_000;
    static int[][] color = new int[MAX_SIZE + 1][26];
    static int[][] nick = new int[MAX_SIZE + 1][26];
    static boolean[] isEndC = new boolean[MAX_SIZE + 1];
    static boolean[] isEndN = new boolean[MAX_SIZE + 1];
    static int[] buffer = new int[2_000];
    static int colorCnt, nickCnt;

    static final int SIZE = 1 << 16;
    static byte[] inbuf = new byte[SIZE];
    static int inIdx = 0, inSize = 0;

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        inputColor(n);
        inputName(m);

        int q = readInt();
        System.out.print(solve(q));
    }

    private static void inputColor(int n) throws IOException {
        colorCnt = 1;
        while(n-- > 0) {
            int pointer = 0;
            int len = readString();
            for(int i = 0; i < len; i++) {
                int val = buffer[i];
                if(color[pointer][val] == 0) {
                    color[pointer][val] = colorCnt;
                    pointer = colorCnt++;
                } else {
                    pointer = color[pointer][val];
                }
            }
            isEndC[pointer] = true;
        }
    }

    private static void inputName(int n) throws IOException {
        nickCnt = 1;
        while(n-- > 0) {
            int pointer = 0;
            int len = readString();
            for(int i = len - 1; i >= 0; i--) {
                int val = buffer[i];
                if(nick[pointer][val] == 0) {
                    nick[pointer][val] = nickCnt;
                    pointer = nickCnt++;
                } else {
                    pointer = nick[pointer][val];
                }
            }
            isEndN[pointer] = true;
        }
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        while(n-- > 0) {
            int len = readString();
            sb.append(isChecked(len)).append('\n');
        }
        return sb.toString();
    }

    private static String isChecked(int len) {
        boolean[] validNick = new boolean[len];
        int nickPtr = 0;

        for(int i = len - 1; i >= 0; i--) {
            int val = buffer[i];
            if(nick[nickPtr][val] > 0) {
                nickPtr = nick[nickPtr][val];
                if(isEndN[nickPtr]) validNick[i] = true;
            } else {
                break;
            }
        }

        int colorPtr = 0;
        for(int i = 0; i < len - 1; i++) {
            int val = buffer[i];
            if(color[colorPtr][val] > 0) {
                colorPtr = color[colorPtr][val];
                if(isEndC[colorPtr] && validNick[i + 1]) {
                    return "Yes";
                }
            } else {
                break;
            }
        }

        return "No";
    }

    private static int read() throws IOException {
        if (inIdx == inSize) {
            inSize = System.in.read(inbuf, 0, SIZE);
            if (inSize == -1) return -1;
            inIdx = 0;
        }
        return inbuf[inIdx++];
    }

    private static int readInt() throws IOException {
        int c = read();
        while(c <= ' ') c = read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = read();
        }
        return n;
    }

    private static int readString() throws IOException {
        int c = read();
        while(c <= ' ') c = read();
        int cnt = 0;
        while(c > ' ') {
            buffer[cnt++] = c - 'a';
            c = read();
        }
        return cnt;
    }
}
