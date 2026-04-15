package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1463 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> dp = new ArrayList<Integer>();

        dp.add(0);
        dp.add(0);
        dp.add(1);
        dp.add(1);

        for(int i = 4; i <= n; i++){
            int temp = dp.get(i-1) + 1;

            if(i % 2 == 0){
                temp = Math.min(temp, dp.get(i/2) + 1);
            }

            if(i % 3 == 0){
                temp = Math.min(temp, dp.get(i/3) + 1);
            }

            dp.add(temp);
        }

        System.out.println(dp.get(n));
    }
}
