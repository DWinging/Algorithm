/**
 * [BOJ] 1647 - 도시 분할 계획
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 55240 KB
 * - 시간: 720 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    private static class City implements Comparable<City> {
        int from, to, cost;
        public City(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(City e) {
            return Integer.compare(this.cost, e.cost);
        }
    }

    static PriorityQueue<City> pq;
    static int[] parents;
    static int c, n, m;

    public static void main(String[] args) throws IOException {
        init();
        inputRoad();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        n = readInt();
        m = readInt();

        parents = new int[n + 1];
        for(int i = 1; i <= n; i++) parents[i] = i;
    }

    private static void inputRoad() throws IOException {
        ArrayList<City> list = new ArrayList<>();
        int a, b, c;
        while(m-- > 0) {
            a = readInt();
            b = readInt();
            c = readInt();

            list.add(new City(a, b, c));
        }
        pq = new PriorityQueue<>(list);
    }

    private static int solve() {
        int edgeCnt = 0, sum = 0;
        while(edgeCnt < n - 2) {
            City city = pq.poll();

            if(union(city.from, city.to)) {
                sum += city.cost;
                edgeCnt++;
            }
        }
        return sum;
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return false;
        parents[pa] = pb;
        return true;
    }

    private static int find(int node) {
        if(parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
