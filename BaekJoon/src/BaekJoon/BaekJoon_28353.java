package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_28353 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cats = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] cat = new int[cats];
        for(int i = 0; i < cats; i++){
            cat[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cat);

        int s = 0;
        int e = cat.length-1;
        int cnt = 0;
        while(s < e){
            while(s < e && cat[s] + cat[e] > weight){
                e--;
            }
            if(s < e){
                s++;
                e--;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
