package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_25556 {

    static int[] stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        stack = new int[4];
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            if(!stackNumber(num)) {
                System.out.println("NO");
                System.exit(0);
            }
        }

        System.out.println("YES");
    }

    private static boolean stackNumber(int num) {
        for(int i = 0; i < 4; i++){
            if(num > stack[i]) {
                stack[i] = num;
                return true;
            }
        }

        return false;
    }
}
