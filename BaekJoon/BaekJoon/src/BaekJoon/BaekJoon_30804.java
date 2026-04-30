package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BaekJoon_30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] fruit = new int[N];

        for(int i = 0; i < fruit.length; i++){
            fruit[i] = Integer.parseInt(st.nextToken());
        }

        int kind = 0;
        int[] list = new int[10];
        int max = 0;
        int cnt = 0;
        int start = 0;
        int end = 0;

        while(end < N){
            list[fruit[end]]++;
            if(list[fruit[end]] == 1){
                kind++;
            }
            if(kind == 3){
                max = Math.max(cnt, max);
                while(kind > 2){
                    list[fruit[start]]--;
                    if(list[fruit[start]] == 0){
                        kind--;
                    }
                    start++;
                    cnt--;
                }
            }
            end++;
            cnt++;
        }

        max = Math.max(max, cnt);

        System.out.println(max);
    }
}
