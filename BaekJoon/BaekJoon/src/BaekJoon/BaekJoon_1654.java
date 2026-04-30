package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1654 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        long min = 0;
        long mid = 0;
        long max = 0;
        long[] list = new long[n];

        for(int i = 0; i < n; i++){
            list[i] = Integer.parseInt(br.readLine());
            if(list[i] > max){
                max = list[i];
            }
        }

        max++;

        while(min < max){
            mid = (min + max) / 2;

            long count = 0;
            for (long l : list) {
                count += (l / mid);
            }

            if(count < target){
                max = mid;
            }
            else{
                min = mid + 1;
            }
        }

        System.out.println(min-1);
    }
}
