package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_23309 {

    static int TOTAL = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] pre = new int[TOTAL + 1];
        int[] next = new int[TOTAL + 1];
        inputStationInfo(pre, next, n, br);

        construction(m, pre, next, br, bw);
        bw.flush();
        bw.close();
    }

    private static void inputStationInfo(int[] pre, int[] next, int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] station = new int[n];
        for(int i = 0; i < n; i++){
            station[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            int s = station[i];
            int ps = i - 1 < 0 ? n-1 : i - 1;
            int ns = (i + 1) % n;

            pre[s] = station[ps];
            next[s] = station[ns];
        }
    }

    private static void construction(int m, int[] pre, int[] next, BufferedReader br, BufferedWriter bw) throws IOException {
        StringTokenizer st;
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int i = Integer.parseInt(st.nextToken());
            int idx = 0;
            if(command.charAt(0) == 'B') {
                int j = Integer.parseInt(st.nextToken());
                idx = command.equals("BN") ? buildStation(next, pre, i, j) : buildStation(pre, next, i, j);
            }
            else {
                idx = command.equals("CN") ? demolishStation(next, pre, i) : demolishStation(pre, next, i);
            }
            bw.write(idx + "\n");
        }
    }

    private static int buildStation(int[] next, int[] pre, int i, int j) {
        int temp = next[i];
        pre[temp] = j;
        pre[j] = i;
        next[j] = next[i];
        next[i] = j;
        return temp;
    }

    private static int demolishStation(int[] next, int[] pre, int i) {
        int temp = next[i];
        next[i] = next[temp];
        pre[next[temp]] = i;
        next[temp] = 0;
        pre[temp] = 0;
        return temp;
    }
}
