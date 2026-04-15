package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class BaekJoon_12764_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[n];

        ArrayList<Time> time = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            time.add(new Time(s, e));
        }

        Collections.sort(time, (t1, t2) -> t1.start - t2.start);
        PriorityQueue<Computer> usingCom = new PriorityQueue<>((c1, c2) -> c1.time - c2.time);
        PriorityQueue<Computer> emptyCom = new PriorityQueue<>((c1, c2) -> c1.num - c2.num);

        int index = 0;
        for(Time t : time) {
            while(!usingCom.isEmpty() && t.start >= usingCom.peek().time) {
                emptyCom.offer(usingCom.poll());
            }

            int num = !emptyCom.isEmpty() ? emptyCom.poll().num : index++;
            cnt[num]++;
            usingCom.offer(new Computer(num, t.end));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n && cnt[i] != 0; i++){
            sb.append(cnt[i]).append(" ");
        }

        System.out.println(index + "\n" + sb);
    }

    private static class Computer {
        int num;
        int time;

        Computer(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    private static class Time {
        int start;
        int end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
