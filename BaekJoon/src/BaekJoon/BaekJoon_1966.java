package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1966 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<int[]> que = new LinkedList<>();
            int n = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                que.add(new int[]{num , j});
            }

            int cnt = 0;
            while(true){
                int[] value = que.poll();
                boolean check = true;
                for(int[] q : que){
                    if(q[0] > value[0]){
                        check = false;
                        break;
                    }
                }

                if(check){
                    cnt++;
                    if(value[1] == index){
                        break;
                    }
                }
                else{
                    que.add(value);
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
