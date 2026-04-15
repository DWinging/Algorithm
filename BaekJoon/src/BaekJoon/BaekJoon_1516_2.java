package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1516_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Building[] buildings = new Building[n+1];
        int[] cnt = new int[n+1];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
            while(true) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == -1) break;
                cnt[i]++;
                list.get(temp).add(i);
            }
        }

        PriorityQueue<Building> que = new PriorityQueue<>((b1, b2) -> b1.time - b2.time);
        for(int i = 1; i <= n; i++){
            if(cnt[i] == 0) {
                que.offer(buildings[i]);
            }
        }

        while(!que.isEmpty()) {
            int now = que.poll().num;
            for(int next : list.get(now)) {
                cnt[next]--;
                if(cnt[next] == 0) {
                    buildings[next].time += buildings[now].time;
                    que.offer(new Building(next, buildings[next].time));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(buildings[i].time).append("\n");
        }

        System.out.println(sb);
    }

    private static class Building {
        int num;
        int time;

        Building(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}
