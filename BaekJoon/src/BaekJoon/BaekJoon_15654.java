package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_15654 {

    static int N;
    static int M;
    static boolean[] visit;
    static int[] list;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        list = new int[N];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

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
        for(int i = 0; i < N; i++){
            if(visit[i]){
                continue;
            }
            arr[index] = list[i];
            visit[i] = true;
            solve(index + 1);
            visit[i] = false;
        }
    }
}
