package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] list = new int[n];

        for(int i = 0; i < n; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        int end = 0;
        int cnt = 0;
        int sum = 0;

        for(int start = 0; start < n; start++){
            while(sum < target && end < n){
                sum += list[end];
                end++;
            }
            if(sum == target){
                cnt++;
            }
            sum -= list[start];
        }

        System.out.println(cnt);
    }
}
