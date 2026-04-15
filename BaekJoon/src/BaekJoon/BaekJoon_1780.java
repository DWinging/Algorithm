package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1780 {
    static int[][] paper;
    static int n1 = 0;
    static int n2 = 0;
    static int n3 = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        paper = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, N, 0, N);
        System.out.println(n1 + "\n" + n2 + "\n" + n3);
    }

    private static void solve(int x1, int x2, int y1, int y2){
        if(check(x1, x2, y1, y2)){
            if(paper[x1][y1] == -1){
                n1++;
            }
            else if(paper[x1][y1] == 0){
                n2++;
            }
            else {
                n3++;
            }
        }
        else {
            int midX1 = (x2- x1) / 3 + x1;
            int midX2 = (x2 - x1) * 2 / 3 + x1;
            int midY1 = (y2 - y1) / 3 + y1;
            int midY2 = (y2 - y1) * 2 / 3 + y1;

            solve(x1, midX1, y1, midY1);
            solve(x1, midX1, midY1, midY2);
            solve(x1, midX1, midY2, y2);

            solve(midX1, midX2, y1, midY1);
            solve(midX1, midX2, midY1, midY2);
            solve(midX1, midX2, midY2, y2);

            solve(midX2, x2, y1, midY1);
            solve(midX2, x2, midY1, midY2);
            solve(midX2, x2, midY2, y2);
        }
    }

    private static boolean check(int x1, int x2, int y1, int y2){
        int temp = paper[x1][y1];
        for(int i = x1; i < x2; i++){
            for(int j = y1; j < y2; j++){
                if(paper[i][j] != temp){
                    return false;
                }
            }
        }

        return true;
    }
}
