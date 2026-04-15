package BaekJoon;

import java.util.*;
import java.io.*;

public class BakJoon_9095 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> dp = new ArrayList<Integer>();

        int n = Integer.parseInt(br.readLine());

        dp.add(0);
        dp.add(1);
        dp.add(2);
        dp.add(4);

        for(int i = 0; i < n; i++){
            int num =Integer.parseInt(br.readLine());

            if(num+1 > dp.size()){
                for(int j = dp.size(); j <= num; j++) {
                    dp.add(dp.get(j - 3) + dp.get(j - 2) + dp.get(j - 1));
                }
            }
            sb.append(dp.get(num)).append("\n");
        }

        System.out.println(sb);
    }

}
