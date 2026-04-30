package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_19584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] idToIdx = coordinateCompression(n, br);

        long[] roads = inputRoads(idToIdx, n, m, br);
        System.out.println(createLine(roads, n));
    }

    private static int[] coordinateCompression(int n, BufferedReader br) throws IOException {
        int[][] y = new int[n][2];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            y[i][0] = Integer.parseInt(st.nextToken());
            y[i][1] = i + 1;
        }

        Arrays.sort(y, (y1, y2) -> Integer.compare(y1[0], y2[0]));

        int[] idToIdx = new int[n + 1];
        int rank = 0;
        idToIdx[0] = rank;
        for(int i = 1; i < n; i++) {
            if(y[i][0] != y[i-1][0]) rank++;
            idToIdx[y[i][1]] = rank;
        }
        return idToIdx;
    }

    private static long[] inputRoads(int[] idToIdx, int n, int m, BufferedReader br) throws IOException {
        long[] roads = new long[n + 2];
        StringTokenizer st;
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int r1 = idToIdx[u];
            int r2 = idToIdx[v];
            int len = Integer.parseInt(st.nextToken());

            roads[Math.min(r1, r2)] += len;
            roads[Math.max(r1, r2) + 1] -= len;
        }
        return roads;
    }

    private static long createLine(long[] roads, int n) {
        long value = 0, maxLen = 0;
        for(int i = 0; i < n; i++) {
            value += roads[i];
            maxLen = Math.max(value, maxLen);
        }
        return maxLen;
    }
}
