package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9466 {

    static int[] team;
    static boolean[] visit, finished;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            team = new int[n+1];
            cnt = n;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++ ){
                team[i] = Integer.parseInt(st.nextToken());
            }

            visit = new boolean[n+1];
            finished = new boolean[n+1];
            for(int i = 1; i <= n; i++){
                if(!visit[i]) {
                    dfs(i);
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int index) {
        visit[index] = true;
        int nextIdx = team[index];
        if(!visit[nextIdx]) {
            dfs(team[index]);
        }
        else {
            if(!finished[nextIdx]) {
                cnt--;
                while(nextIdx != index) {
                    nextIdx = team[nextIdx];
                    cnt--;
                }
            }
        }
        finished[index] = true;
    }
}
