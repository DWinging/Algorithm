package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BaekJoon_2161 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> que = new LinkedList<>();
        for(int i = 1; i <= n; i++) {que.add(i);}
        while(que.size() >= 2) {
            sb.append(que.poll()).append(" ");
            que.add(que.poll());
        }
        sb.append(que.poll());
        System.out.println(sb);
    }
}