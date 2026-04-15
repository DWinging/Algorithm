package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BaekJoon_10282 {

    static int n, d, c;
    static int[] dict;
    static boolean[] visit;
    static ArrayList<ArrayList<Com_10282>> computer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int num1, num2, time, max, cnt;

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dict = new int[n+1];
            visit = new boolean[n+1];
            computer = new ArrayList<>();
            for(int i = 0; i <= n; i++){
                computer.add(new ArrayList<>());
                dict[i] = Integer.MAX_VALUE;
            }

            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());
                num1 = Integer.parseInt(st.nextToken());
                num2 = Integer.parseInt(st.nextToken());
                time = Integer.parseInt(st.nextToken());

                computer.get(num2).add(new Com_10282(num1, time));
            }

            dijkstra(c);

            max = 0;
            cnt = 0;
            for(int i = 1; i <= n; i++){
                if(dict[i] != Integer.MAX_VALUE){
                    cnt++;
                    max = Math.max(max, dict[i]);
                }
            }
            sb.append(cnt).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        dict[start] = 0;
        PriorityQueue<Com_10282> que = new PriorityQueue<>();
        que.offer(new Com_10282(start, 0));

        while(!que.isEmpty()){
            Com_10282 com = que.poll();
            int cur = com.end;
            if(!visit[cur]){
                visit[cur] = true;
                for(Com_10282 temp : computer.get(cur)){
                    if(!visit[temp.end] && dict[temp.end] > dict[cur] + temp.time){
                        dict[temp.end] = dict[cur] + temp.time;
                        que.offer(new Com_10282(temp.end, dict[temp.end]));
                    }
                }
            }
        }
    }
}

class Com_10282 implements Comparable<Com_10282> {
    int end;
    int time;

    Com_10282(int end, int time){
        this.end = end;
        this.time = time;
    }

    @Override
    public int compareTo(Com_10282 o) {
        return time - o.time;
    }
}
