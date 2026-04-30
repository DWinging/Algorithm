package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_30802 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] tList = br.readLine().split(" ");

        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for(int i = 0; i < tList.length; i++){
            int temp = Integer.parseInt(tList[i]);
            if(temp % t == 0){
                cnt += temp / t;
            }
            else {
                cnt += temp / t + 1;
            }
        }

        System.out.println(cnt);
        System.out.println(n / p + " " + n % p);
    }
}
