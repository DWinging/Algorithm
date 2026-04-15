package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_3273 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        int target = Integer.parseInt(br.readLine());

        Arrays.sort(num);

        int end = n-1;
        int cnt = 0;

        for(int i = 0; i < n; i++){
            while(end > i){
                int temp = num[i] + num[end];
                if(temp == target){
                    cnt++;
                    break;
                }
                else if(temp > target){
                    end--;
                }
                else {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
