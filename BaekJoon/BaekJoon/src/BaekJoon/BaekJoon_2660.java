package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon_2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] person = new int[n+1][n+1];
        StringTokenizer st;

        for(int i = 1; i <= n; i++){
            Arrays.fill(person[i], Integer.MAX_VALUE);
            person[i][i] = 0;
        }

        while(true){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if(n1 == -1 && n2 == -1) break;
            person[n1][n2] = 1;
            person[n2][n1] =1;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(k == i || person[i][k] == Integer.MAX_VALUE) continue;
                for(int j = 1; j <= n; j++){
                    if(j == i || k == j || person[k][j] == Integer.MAX_VALUE) continue;
                    if(person[i][j] > person[i][k] + person[k][j]) {
                        person[i][j] = person[i][k] + person[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int score = n+1;
        for(int i = 1; i <= n; i++){
            int temp = 0;
            for(int j = 1; j <= n; j++){
                temp = Math.max(temp, person[i][j]);
            }
            if(score > temp){
                stack.clear();
                score = temp;
                stack.push(i);
            }
            else if(score == temp){
                stack.push(i);
            }
        }

        sb.append(score).append(" ").append(stack.size()).append("\n");
        for(int i : stack){
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
