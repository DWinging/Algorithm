package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        ArrayList<Noodle> list = new ArrayList<>();
        PriorityQueue<Noodle> que = new PriorityQueue<>((n1, n2) -> {
            return n1.cnt - n2.cnt;
        });

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Noodle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list, (n1, n2) -> {
            if(n1.deadline == n2.deadline) return n2.cnt - n1.cnt;
            return n1.deadline - n2.deadline;
        });

        que.offer(list.get(0));
        for(int i = 1; i < list.size(); i++) {
            if(que.size() < list.get(i).deadline) {
                que.offer(list.get(i));
            }
            else if(que.peek().cnt <= list.get(i).cnt) {
                que.poll();
                que.offer(list.get(i));
            }
        }

        int sum = 0;
        int size = que.size();
        for(int i = 0; i < size; i++){
            sum += que.poll().cnt;
        }

        System.out.println(sum);
    }

    private static class Noodle {
        int deadline;
        int cnt;

        Noodle (int deadline, int cnt) {
            this.deadline = deadline;
            this.cnt = cnt;
        }
    }
}
