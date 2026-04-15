package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] time = new int[n+1];
        int[] cnt = new int[n+1];
        ArrayList<ArrayList<Integer>> building = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            building.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == -1) break;
                cnt[i]++;
                building.get(temp).add(i);
            }
        }

        Queue<Integer> que = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(cnt[i] == 0) {
                que.offer(i);
            }
        }

        int[] result = new int[n+1];
        while(!que.isEmpty()) {
            int now = que.poll();

            for(int next : building.get(now)) {
                cnt[next]--;
                result[next] = Math.max(result[next], result[now] + time[now]);
                if(cnt[next] == 0) {
                    que.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(time[i] + result[i]).append("\n");
        }

        System.out.println(sb);
    }
}
