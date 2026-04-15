package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1697 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Integer> que = new LinkedList<>();

        int[] visit = new int[100001];

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        que.add(start);
        visit[start] = 1;

        while(!que.isEmpty()){
            int point = que.poll();

            if(point == end){
                System.out.println(visit[point] - 1);
                break;
            }

            if(point - 1 > 0 && visit[point - 1] == 0){
                visit[point - 1] = visit[point] + 1;
                que.add(point - 1);
            }

            if(point + 1 < visit.length && visit[point + 1] == 0){
                visit[point + 1] = visit[point] + 1;
                que.add(point + 1);
            }

            if(point * 2 < visit.length && visit[point * 2] == 0){
                visit[point * 2] = visit[point] + 1;
                que.add(point * 2);
            }
        }
    }
}
