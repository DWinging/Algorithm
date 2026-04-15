package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_26901 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int power = Integer.parseInt(st.nextToken());
        int[] list = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        int s = 0;
        int e = n-1;
        int cnt = 0;
        while(s < e){
            while(s < e && list[s] + list[e] < power){
                s++;
            }
            if(list[s] + list[e] >= power){
                s++;
                e--;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
