package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1904 {
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> dp = new ArrayList<>();

        dp.add(1);
        dp.add(2);

        for(int i = 2; i < n; i++){
            dp.add((dp.get(i-1) + dp.get(i-2)) % 15746);
        }

        System.out.println(dp.get(n-1));
    }
}
