package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1990 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean[] primeNum = new boolean[b + 1];
        for(int i = 2; i*i <= b; i++){
            if(primeNum[i]){
                continue;
            }
            for(int j = i*2; j <= b; j += i){
                primeNum[j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = a; i <= b; i++) {
            if(!primeNum[i] && solve(i+"")){
                sb.append(i).append("\n");
            }
        }
        sb.append(-1);
        System.out.println(sb);
    }

    private static boolean solve(String num){
        int s = 0;
        int e = num.length()-1;

        while(s < e){
            if(num.charAt(s) != num.charAt(e)){
                return false;
            }
            s++;
            e--;
        }

        return true;
    }
}
