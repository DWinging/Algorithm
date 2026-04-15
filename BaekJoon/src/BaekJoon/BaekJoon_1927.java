package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return - Integer.compare(o1,o2);
            }
        });

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            int temp = Integer.parseInt(br.readLine());

            if(temp == 0){
                if(minHeap.isEmpty()){
                    sb.append(0 + "\n");
                }
                else {
                    sb.append(minHeap.remove()).append("\n");
                }
            }
            else{
                minHeap.add(temp);
            }
        }

        System.out.println(sb);
    }
}
