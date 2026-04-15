package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2166 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        long[] x = new long[N + 1];
        long[] y = new long[N + 1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        x[N] = x[0];
        y[N] = y[0];

        double sum = 0;
        for(int i = 0; i < N; i++){
            sum += x[i] * y[i + 1];
            sum -= x[i + 1] * y[i];
        }

        System.out.printf("%.1f", Math.abs(sum / 2));
    }
}
