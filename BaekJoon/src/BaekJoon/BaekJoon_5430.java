package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_5430 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int cnt = 0; cnt < t; cnt++){
            Deque<Integer> deque = new ArrayDeque<>();
            String[] cmd = br.readLine().split("");
            boolean reverse = false;

            int n = Integer.parseInt(br.readLine());

            String input = br.readLine();
            input = input.substring(1, input.length()-1);

            StringTokenizer st = new StringTokenizer(input, ",");
            for(int i = 0; i < n; i++){
                deque.add(Integer.parseInt(st.nextToken()));
            }


            boolean check = true;
            for(int i = 0; i < cmd.length; i++){
                if(cmd[i].equals("R")){
                    reverse = !reverse;
                }
                else {
                    if(deque.isEmpty()){
                        sb.append("error\n");
                        check = false;
                        break;
                    }
                    else{
                        if(reverse){
                            deque.pollLast();
                        }
                        else{
                            deque.pollFirst();
                        }
                    }
                }
            }

            if(check){
                sb.append("[");
                int max = deque.size()-1;
                for(int i = 0; i < max; i++){
                    if(reverse){
                        sb.append(deque.pollLast()).append(",");
                    }
                    else{
                        sb.append(deque.pollFirst()).append(",");
                    }
                }
                if(!deque.isEmpty()){
                    sb.append(deque.poll()).append("]\n");
                }
                else{
                    sb.append("]\n");
                }

            }
        }

        System.out.println(sb);
    }
}
