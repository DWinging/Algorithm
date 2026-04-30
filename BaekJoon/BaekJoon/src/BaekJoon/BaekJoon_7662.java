package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_7662 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int testCase = Integer.parseInt(br.readLine());


        while(testCase-- > 0){
            PriorityQueue<Integer> minQue = new PriorityQueue<>();
            PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());

            int n = Integer.parseInt(br.readLine());
            int cnt = 0;

            while(n-- > 0){
                st = new StringTokenizer(br.readLine());
                String temp = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                if(temp.equals("D") && value == 1){
                    if(cnt != 0){
                        cnt--;
                        maxQue.poll();
                    }
                }
                else if(temp.equals("D") && value == -1){
                    if(cnt != 0){
                        cnt--;
                        minQue.poll();
                    }
                }
                else {
                    cnt++;
                    minQue.add(value);
                    maxQue.add(value);
                }
            }

            if(cnt == 0){
                sb.append("EMPTY").append("\n");
            }
            else{
                sb.append(maxQue.poll() + " " + minQue.poll() + "\n");
            }
        }

        System.out.println(sb);
    }
}
