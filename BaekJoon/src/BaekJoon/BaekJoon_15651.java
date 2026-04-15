package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15651 {

    static int N, M;
    static int[] visit, arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new int[N+1];
        arr = new int[M];

        solve(0);
        System.out.println(sb);
    }

    private static void solve(int index){
        if(index == M){
            for(int i = 0; i < M; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 1; i <= N; i++){
            if(visit[i] >= M){
                continue;
            }
            arr[index] = i;
            visit[i]++;
            solve(index + 1);
            visit[i]--;
        }
    }
}
