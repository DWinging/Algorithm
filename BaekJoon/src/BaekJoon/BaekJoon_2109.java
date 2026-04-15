package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int days = Integer.parseInt(br.readLine());
        PriorityQueue<Talk> que = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.money, t1.money));
        StringTokenizer st;

        for(int i = 0; i < days; i++){
            st = new StringTokenizer(br.readLine());
            que.add(new Talk(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int sum = 0;
        boolean[] cheak = new boolean[days];

        while(!que.isEmpty()) {
            Talk talk = que.poll();
            int d = talk.day >= days ? days - 1 : talk.day - 1;
            while(d >= 0) {
                if(!cheak[d]) {
                    sum += talk.money;
                    cheak[d] = true;
                    break;
                }
                d--;
            }
        }

        System.out.println(sum);
    }

    private static class Talk {
        int money;
        int day;

        Talk(int money, int day) {
            this.money = money;
            this.day = day;
        }
    }
}
