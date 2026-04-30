package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BaekJoon_1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> maxQue = new PriorityQueue<>();
        PriorityQueue<Integer> minQue = new PriorityQueue<>(Collections.reverseOrder());
        maxQue.add(Integer.parseInt(br.readLine()));
        sb.append(maxQue.peek()).append("\n");

        for(int i = 1; i < N; i++){
            int temp = Integer.parseInt(br.readLine());
            if(temp >= maxQue.peek()){
                if(minQue.size() == maxQue.size()){
                    maxQue.add(temp);
                    sb.append(maxQue.peek()).append("\n");
                }
                else {
                    minQue.add(maxQue.poll());
                    maxQue.add(temp);
                    sb.append(minQue.peek()).append("\n");
                }
            }
            else {
                if(minQue.size() == maxQue.size()){
                    if(minQue.peek() >= temp){
                        maxQue.add(minQue.poll());
                        minQue.add(temp);
                    }
                    else {
                        maxQue.add(temp);
                    }
                    sb.append(maxQue.peek()).append("\n");
                }
                else {
                    minQue.add(temp);
                    sb.append(minQue.peek()).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
