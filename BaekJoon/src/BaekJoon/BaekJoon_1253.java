package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []list = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        int cnt = 0;
        for(int i = 0; i < n; i++){
            int s = 0;
            int e = n-1;
            while(s < e){
                int sum = list[s] + list[e];
                if(sum == list[i]){
                    if(s == i){
                        s++;
                    }
                    else if(e == i){
                        e--;
                    }
                    else {
                        cnt++;
                        break;
                    }
                }
                else if(sum < list[i]){
                    s++;
                }
                else {
                    e--;
                }
            }
        }

        System.out.println(cnt);
    }
}
