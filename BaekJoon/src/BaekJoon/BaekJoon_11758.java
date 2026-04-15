package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] dots = new int[3][2];

        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }

        int temp = (dots[1][0] - dots[0][0]) * (dots[2][1] - dots[0][1]) - (dots[1][1] - dots[0][1]) * (dots[2][0] - dots[0][0]);
        if(temp > 0) {
            System.out.println(1);
        }
        else if(temp < 0){
            System.out.println(-1);
        }
        else {
            System.out.println(0);
        }
    }
}
