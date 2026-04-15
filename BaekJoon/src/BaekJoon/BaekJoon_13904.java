package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_13904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int day = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Project> que = new PriorityQueue<>((s1, s2) -> Integer.compare(s2.score, s1.score));
        for(int i = 0; i < day; i++){
            st = new StringTokenizer(br.readLine());
            que.add(new Project(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int sum = 0;
        boolean[] check = new boolean[day];
        while(!que.isEmpty()) {
            Project project = que.poll();
            int d = project.day >= day ? day-1 : project.day - 1;
            while(d >= 0) {
                if(!check[d]) {
                    sum += project.score;
                    check[d] = true;
                    break;
                }
                d--;
            }
        }

        System.out.println(sum);
    }

    private static class Project {
        int day;
        int score;

        Project (int day, int score) {
            this.day = day;
            this.score = score;
        }
    }
}
