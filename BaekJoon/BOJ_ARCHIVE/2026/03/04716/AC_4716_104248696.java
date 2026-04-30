/**
 * [BOJ] 4716 - 풍선
 * - 제출 날짜: 2026년 3월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 12856 KB
 * - 시간: 124 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static class Team implements Comparable<Team> {

        int k, a, b;

        @Override
        public int compareTo(Team e) {
            int d1 = Math.abs(this.a - this.b);
            int d2 = Math.abs(e.a - e.b);
            return Integer.compare(d2, d1);
        }
    }

    final static int MAX_SIZE = 1000;
    static Team[] teams;
    static int[] room;
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        init();
        while(true) {
            int n = readInt();
            room[0] = readInt();
            room[1] = readInt();

            if(n == 0) break;

            inputTeamInfo(n);
            int res = solve(n);
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {
        c = System.in.read();
        room = new int[2];
        teams = new Team[MAX_SIZE];
        for(int i = 0; i < MAX_SIZE; i++) {
            teams[i] = new Team();
        }
    }

    private static void inputTeamInfo(int n) throws IOException {
        for(int i = 0; i < n; i++) {
            teams[i].k = readInt();
            teams[i].a = readInt();
            teams[i].b = readInt();
        }

        Arrays.sort(teams, 0, n);
    }

    private static int solve(int n) {
        int res = 0;
        for(int i = 0; i < n; i++) {
            int k = teams[i].k;
            int a = teams[i].a;
            int b = teams[i].b;

            res += (a < b) ? moveRoom(k, a, b, 0, 1) : moveRoom(k, b, a, 1, 0);
        }

        return res;
    }

    private static int moveRoom(int k, int a, int b, int r1, int r2) {
        int res = 0;
        if(k <= room[r1]) {
            res = k * a;
            room[r1] -= k;
        } else {
            res = room[r1] * a + (k - room[r1]) * b;
            room[r2] -= (k - room[r1]);
            room[r1] = 0;
        }
        return res;
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