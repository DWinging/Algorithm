/**
 * [BOJ] 10564 - 팔굽혀펴기
 * - 제출 날짜: 2026년 1월 27일
 * - 결과: 런타임 에러 (ArrayIndexOutOfBounds)
 */

import java.io.*;
import java.util.Arrays;

class Main {
    final static int MAX_RANGE = 5000;
    final static int MAX_STACK = 10000;
    static int[][] dp = new int[MAX_RANGE + 1][400];
    static int[] arr = new int[10];
    static int[] score = new int[MAX_STACK];
    static int[] total = new int[MAX_STACK];
    static int[] pre = new int[MAX_STACK];
    
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = readInt();

        while(T-- > 0) {
            int n = readInt();
            int m = readInt();
            for(int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1);
            }

            for(int i = 0; i < m; i++) {
                arr[i] = readInt();
            }        

            int top = 1;
            score[0] = 0;
            total[0] = 0;
            pre[0] = 0;
            dp[0][0] = 0;
            int answer = -1;
            while(top > 0) {
                top--;
                int s = score[top];
                int t = total[top];
                int p = pre[top];
                
                for(int i = 0; i < m; i++) {
                    int nextScore = s + arr[i];
                    int nextTotal = t + p + arr[i];
                    int nextPre = p + arr[i];

                    if(nextTotal > n) continue;
                    
                    if(nextTotal == n) {
                        answer = Math.max(answer, nextScore);
                        continue;
                    }
                    
                    if(dp[nextTotal][nextPre] < nextScore) {
                        dp[nextTotal][nextPre] = nextScore;
                        score[top] = nextScore;
                        total[top] = nextTotal;
                        pre[top] = nextPre;
                        top++;
                    }
                }
            }
            bw.write(answer +"\n");
        }
        bw.flush();
        bw.close();
    }

    public static int readInt() throws IOException {
        int c = System.in.read();
        while(c <= ' ') {
            c = System.in.read();
        }

        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n * 10) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}