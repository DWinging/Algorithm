package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_14719 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        int weight = 0, wall = 0;
        for(int i = 0; i < w; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(wall <= n) {
                while(!deque.isEmpty()) {
                    weight += wall - deque.pollFirst();
                }
                wall = n;
            }
            else {
                deque.addLast(n);
            }
        }

        if(!deque.isEmpty()) {
            wall = deque.pollLast();
            while(!deque.isEmpty()) {
                int n = deque.pollLast();
                if(wall >= n) {
                    weight += wall - n;
                }
                else {
                    wall = n;
                }
            }
        }

        System.out.println(weight);
    }
}
