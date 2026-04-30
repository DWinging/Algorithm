package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_26043 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Queue<int[]> que = new LinkedList<>();

        for(int i = 0; i < 3; i++) {
            list.add(new ArrayList<>());
        }

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            if(st.nextToken().equals("1")){
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                que.add(new int[]{num1, num2});
            }
            else {
                if(Integer.parseInt(st.nextToken()) == que.peek()[1]){
                    list.get(0).add(que.peek()[0]);
                }
                else {
                    list.get(1).add(que.peek()[0]);
                }
                que.poll();
            }
        }

        while(!que.isEmpty()){
            list.get(2).add(que.poll()[0]);
        }

        for(int i = 0; i < list.size(); i++){
            Collections.sort(list.get(i));
            if(list.get(i).size() == 0){
                sb.append("None");
            }
            else{
                for(int j : list.get(i)){
                    sb.append(j).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
