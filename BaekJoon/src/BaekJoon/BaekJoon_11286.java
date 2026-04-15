package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1) == Math.abs(o2)){
                    return o1 - o2;
                }
                else {
                    return Math.abs(o1) - Math.abs(o2);
                }
            }
        });
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0){
            int temp = Integer.parseInt(br.readLine());
            if(temp == 0){
                if(que.isEmpty()){
                    sb.append(0).append("\n");
                }
                else {
                    sb.append(que.poll()).append("\n");
                }
            }
            else {
                que.offer(temp);
            }
        }

        System.out.println(sb);
    }
}
