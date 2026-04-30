package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_1747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(2);
            System.exit(0);
        }
        while(true){
            if(check(String.valueOf(N))){
                if(checkPrime(N)){
                    System.out.println(N);
                    break;
                }
            }
            N++;
        }
    }

    private static boolean check(String n){
        int s = 0;
        int e = n.length()-1;
        while(s < e){
            if(n.charAt(s) != n.charAt(e)){
                return false;
            }
            s++;
            e--;
        }
        return true;
    }

    private static boolean checkPrime(int n){
        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }
}
