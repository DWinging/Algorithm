package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_17612 {

    private static class Info implements Comparable<Info> {
        int id, time, pos;

        Info(int id, int time, int pos) {
            this.id = id;
            this.time = time;
            this.pos = pos;
        }

        @Override
        public int compareTo(Info info) {
            if(this.time != info.time) return Integer.compare(this.time, info.time);
            else return Integer.compare(this.pos, info.pos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] arr = inputWait(n, br);
        PriorityQueue<Info> pq = new PriorityQueue<>();
        settingQueue(pq, arr, n, k);
        System.out.println(calculate(pq, arr));
    }

    private static int[][] inputWait(int n, BufferedReader br) throws IOException{
        int[][] arr = new int[n][2];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // id
            arr[i][1] = Integer.parseInt(st.nextToken()); // time
        }
        return arr;
    }

    private static void settingQueue(PriorityQueue<Info> pq, int[][] arr, int n, int k) {
        for(int i = 0; i < Math.min(n, k); i++) {
            pq.add(new Info(arr[i][0], arr[i][1], i));
        }
    }

    private static long calculate(PriorityQueue<Info> pq, int[][] arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        long total = 0, order = 1;
        int time, idx = pq.size();

        while(!pq.isEmpty()) {
            time = pq.peek().time;
            while(!pq.isEmpty() && pq.peek().time == time) {
                Info info = pq.poll();
                deque.addLast(info.id);

                if(idx < arr.length) {
                    int id = arr[idx][0];
                    int t = arr[idx][1] + time;
                    pq.add(new Info(id, t, info.pos));
                    idx++;
                }
            }

            while(!deque.isEmpty()) {
                total += deque.pollLast() * order;
                order++;
            }
        }

        return total;
    }
}