package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1379 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] classNum = new int[n];

        ArrayList<Time> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Time(num, s, e));
        }

        list.sort((t1, t2) -> {
            if (t1.start == t2.start) return t1.end - t2.end;
            return t1.start - t2.start;
        });

        PriorityQueue<Room> que = new PriorityQueue<>((t1, t2) -> t1.end - t2.end);
        que.offer(new Room(1, 0));
        for(Time t : list) {
            if(que.peek().end <= t.start) {
                int num = que.poll().num;
                classNum[t.number-1] = num;
                que.offer(new Room(num, t.end));
            }
            else {
                classNum[t.number-1] = que.size() + 1;
                que.offer(new Room(que.size() + 1, t.end));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(que.size()).append("\n");
        for(int i : classNum) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static class Room {
        int num;
        int end;

        Room(int num, int end) {
            this.num = num;
            this.end = end;
        }
    }

    private static class Time {
        int number;
        int start;
        int end;

        Time (int number, int start, int end) {
            this.number = number;
            this.start = start;
            this.end = end;
        }
    }
}
