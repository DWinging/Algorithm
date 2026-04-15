package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_4716 {

    static class Team implements Comparable<Team> {

        int k, a, b;

        @Override
        public int compareTo(Team e) {
            int d1 = this.a < this.b ? this.b - this.a : this.a - this.b;
            int d2 = e.a < e.b ? e.b - e.a : e.a - e.b;
            return Integer.compare(d2, d1);
        }
    }

    final static int MAX_SIZE = 1000;
    static Team[] teams;
    static int[] room;
    static int c, N;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        init();
        while((N = readInt()) > 0) {
            room[0] = readInt();
            room[1] = readInt();

            inputTeamInfo();
            sb.append(solve()).append('\n');
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

    private static void inputTeamInfo() throws IOException {
        for(int i = 0; i < N; i++) {
            teams[i].k = readInt();
            teams[i].a = readInt();
            teams[i].b = readInt();
        }
        Arrays.sort(teams, 0, N);
    }

    private static int solve() {
        int res = 0;
        for(int i = 0; i < N; i++) {
            int k = teams[i].k;
            int a = teams[i].a;
            int b = teams[i].b;

            if(a < b) res += calculate(k, a, b, 0, 1);
            else res += calculate(k, b, a, 1, 0);
        }
        return res;
    }

    private static int calculate(int k, int nearDist, int farDist, int nearRoom, int farRoom) {
        int res;
        if(k <= room[nearRoom]) {
            res = k * nearDist;
            room[nearRoom] -= k;
        } else {
            res = room[nearRoom] * nearDist + (k - room[nearRoom]) * farDist;
            room[farRoom] -= (k - room[nearRoom]);
            room[nearRoom] = 0;
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