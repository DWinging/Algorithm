package BJ_17071;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 2024년 12월 25일 풀이
 * BaekJoon_17071 숨바꼭질 5
 * 메모리 50564 KB
 * 시간 168 ms
 */
public class BJ_17071_87787499 {

    static int k;
    static final int INF = 500001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        System.out.println(x == k ? 0 : bfs(x));
    }

    private static int bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        boolean[][] visit = new boolean[2][INF];
        visit[0][start] = true;
        int[] dx = {2, 1, -1};
        int time = 0;

        while(!que.isEmpty()) {
            int size = que.size();
            time++;
            k += time;
            int toggle = time % 2;
            if(k >= INF) break;

            for(int i = 0; i < size; i++){
                int x = que.poll();
                for(int j = 0; j < 3; j++) {
                    int temp = j == 0 ? x * dx[j] : x + dx[j];
                    if(temp >= 0 && temp < INF && !visit[toggle][temp]) {
                        visit[toggle][temp] = true;
                        que.offer(temp);
                    }
                }
            }

            if(visit[toggle][k]) {
                return time;
            }
        }

        return -1;
    }
}
