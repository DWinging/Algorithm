package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_2294 {

    final static int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        int[] moneys = new int[money + 1];
        boolean[] check = new boolean[10001];
        Arrays.fill(moneys, INF);
        moneys[0] = 0;

        for(int i = 0; i < n; i++){
            int coin = Integer.parseInt(br.readLine());
            if(!check[coin] && coin <= money) {
                check[coin] = true;
            }
        }

        for(int i = 1; i < check.length; i++){
            if(!check[i]) continue;
            for(int j = i; j <= money; j++){
                moneys[j] = Math.min(moneys[j], moneys[j - i] + 1);
            }
        }

        System.out.println(moneys[money] == INF ? -1 : moneys[money]);
    }
}
