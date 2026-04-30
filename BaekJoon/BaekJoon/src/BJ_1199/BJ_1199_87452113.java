package BJ_1199;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 2024년 12월 14일 풀이
 * BaekJoon_1854 K번째 최단경로 찾기
 * 메모리 319508 KB
 * 시간 3904 ms
 */
public class BJ_1199_87452113 {

    static int n;
    static int[][] dict;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dict = new int[n][n];
        StringTokenizer st;

        int sum;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            sum = 0;
            for(int j = 0; j < n; j++){
                dict[i][j] = Integer.parseInt(st.nextToken());
                sum += dict[i][j];
            }
            if(sum % 2 != 0) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        eulerCircuit(0);
        System.out.println(sb);
    }

    private static void eulerCircuit(int index){
        for(int i = 0; i < n; i++){
            while(dict[index][i] != 0){
                dict[index][i]--;
                dict[i][index]--;
                eulerCircuit(i);
            }
        }
        sb.append(index + 1).append(" ");
    }
}

