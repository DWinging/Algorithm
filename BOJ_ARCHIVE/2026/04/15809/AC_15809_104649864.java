/**
 * [BOJ] 15809 - 전국시대
 * - 제출 날짜: 2026년 4월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 18604 KB
 * - 시간: 184 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static int[] parents, power;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        inputCountryInfo(n);
        int remainCountry = solve(n, m);
        System.out.println(buildString(n, remainCountry));
    }

    private static void inputCountryInfo(int n) throws IOException {
        parents = new int[n + 1];
        power = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            parents[i] = i;
            power[i] = readInt();
        }
    }

    private static int solve(int n, int m) throws IOException {
        int total = n;
        while(m-- > 0) {
            int o = readInt();
            int p = readInt();
            int q = readInt();

            if(o == 1) {
                total -= union(p, q, 1);
            } else {
                total -= union(p, q, -1);
            }
        }
        
        return total;
    }

    private static int union(int a, int b, int w) {
        int pA = find(a);
        int pB = find(b);

        // 이미 적어도 한 나라가 멸망 한 경우
        if(power[pA] == -1 || power[pB] == -1) return 0;

        // 서로 전쟁을 벌였으나, 두 나라의 병력이 같은 경우
        if(power[pA] + w * power[pB] == 0) {
            power[pA] = -1;
            power[pB] = -1;
            return 2;
        }
        
        if(power[pA] >= power[pB]) {
            parents[pB] = pA;
            power[pA] = power[pA] + w * power[pB];
        } else {
            parents[pA] = pB;
            power[pB] = power[pB] + w * power[pA];
        }
        return 1;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static String buildString(int n, int total) {
        int[] res = new int[total];
        int idx = 0;
        for(int i = 1; i <= n; i++) {
            if(parents[i] == i && power[i] > 0) {
                res[idx++] = power[i];
            }
        }

        Arrays.sort(res);

        StringBuilder sb = new StringBuilder();
        sb.append(idx).append('\n');
        for(int val : res) {
            sb.append(val).append(' ');
        }
        return sb.toString();
    }
    
    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}