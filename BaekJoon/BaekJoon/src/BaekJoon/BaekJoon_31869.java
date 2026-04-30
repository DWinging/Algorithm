package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon_31869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Info> promise = new HashMap<>();
        boolean[][] calendar = new boolean[11][7];
        StringTokenizer st;

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            promise.put(st.nextToken(), new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();

            if(promise.get(name).money > Integer.parseInt(st.nextToken())){
                promise.remove(name);
            }
            else {
                calendar[promise.get(name).m][promise.get(name).d] = true;
            }
        }

        int cnt = 0;
        int max = 0;
        for(int i = 1; i < calendar.length; i++){
            for(int j = 0; j < calendar[i].length; j++){
                if(calendar[i][j]){
                    cnt++;
                }
                else {
                    max = Math.max(cnt, max);
                    cnt = 0;
                }
            }
        }
        max = Math.max(cnt, max);
        System.out.println(max);
    }

    static class Info{
        int m;
        int d;
        int money;

        public Info(int m, int d, int money){
            this.m = m;
            this.d = d;
            this.money = money;
        }
    }
}