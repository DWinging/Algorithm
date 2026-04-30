package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> que = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            que.add(Long.parseLong(br.readLine()));
        }

        long sum = 0;
        long temp = 0;
        while(que.size() != 1){
            temp = que.poll() + que.poll();
            sum += temp;
            que.add(temp);
        }
        System.out.println(sum);
    }
}
