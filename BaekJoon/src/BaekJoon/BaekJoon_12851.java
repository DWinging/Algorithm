package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, K));
    }

    private static String bfs(int start, int target){
        int[] visit = new int[200001];
        visit[start] = 1;
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        int cnt = 0;

        while(!que.isEmpty()){
            int x = que.poll();

            if(x == target){
                cnt++;
                continue;
            }

            int temp = x * 2;
            if(temp < visit.length){
                if(visit[temp] == 0){
                    visit[temp] = visit[x] + 1;
                    que.add(temp);
                }
                else if(visit[temp] == visit[x]+1){
                    que.add(temp);
                }
            }
            temp = x - 1;
            if(temp >= 0){
                if(visit[temp] == 0){
                    visit[temp] = visit[x] + 1;
                    que.add(temp);
                }
                else if(visit[temp] == visit[x] + 1){
                    que.add(temp);
                }
            }
            temp = x + 1;
            if(temp < visit.length){
                if(visit[temp] == 0){
                    visit[temp] = visit[x] + 1;
                    que.add(temp);
                }
                else if(visit[temp] == visit[x] + 1){
                    que.add(temp);
                }
            }
        }

        return visit[target]-1 + "\n" + cnt;
    }
}
