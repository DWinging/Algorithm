package BaekJoon;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BaekJoon_15652 {
    static int N;
    static int M;
    static int[] visit;
    static StringBuilder sb = new StringBuilder();
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new int[N+1];
        num = new int[N+1];
        solve(0);

        System.out.println(sb);
    }

    private static void solve(int index) {
        if (index == M) {
            for (int i = 0; i < M; i++) {
                sb.append(num[i]).append(" ");
            }
            sb.append("\n");
        } else {

            for (int i = 1; i <= N; i++) {
                if (visit[i] >= M || (index != 0 && i < num[index - 1])) {
                    continue;
                }
                visit[i] += 1;
                num[index] = i;
                solve(index + 1);
                visit[i] -= 1;
            }
        }
    }
}
