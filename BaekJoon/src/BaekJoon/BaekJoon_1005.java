package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] time = new int[n+1];
            int[] dp = new int[n+1];
            int[] cnt = new int[n+1];
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            list.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                time[i] = Integer.parseInt(st.nextToken());
                list.add(new ArrayList<>());
            }

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                list.get(n1).add(n2);
                cnt[n2]++;
            }

            Queue<Integer> que = new LinkedList<>();
            for(int i = 1; i <= n; i++){
                if(cnt[i] == 0) {
                    que.offer(i);
                    dp[i] = time[i];
                }
            }

            while(!que.isEmpty()) {
                int idx = que.poll();
                for(int i : list.get(idx)) {
                    if(--cnt[i] == 0){
                        que.offer(i);
                    }
                    dp[i] = Math.max(dp[i], time[i] + dp[idx]);
                }
            }

            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }
}
