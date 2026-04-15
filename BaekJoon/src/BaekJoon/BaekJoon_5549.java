package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_5549 {

    static int[][][] land;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        land = new int[n+1][m+1][3];
        for(int i = 1; i <= n; i++){
            String state = br.readLine();
            for(int j = 1; j <= m; j++){
                if(state.charAt(j-1) == 'J') {
                    land[i][j][0] = 1;
                }
                else if(state.charAt(j-1) == 'O') {
                    land[i][j][1] = 1;
                }
                else {
                    land[i][j][2] = 1;
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                for(int idx = 0; idx < 3; idx++){
                    land[i][j][idx] += land[i-1][j][idx];
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                for(int idx = 0; idx < 3; idx++){
                    land[i][j][idx] += land[i][j-1][idx];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(solve(x1, y1, x2, y2));
        }

        System.out.println(sb);
    }

    private static String solve(int x1, int y1, int x2, int y2) {
        int jungle = 0;
        int ocean = 0;
        int ice = 0;


        jungle = land[x2][y2][0] - land[x2][y1-1][0] - land[x1-1][y2][0] + land[x1-1][y1-1][0];
        ocean = land[x2][y2][1] - land[x2][y1-1][1]- land[x1-1][y2][1] + land[x1-1][y1-1][1];
        ice = land[x2][y2][2] - land[x2][y1-1][2]- land[x1-1][y2][2] + land[x1-1][y1-1][2];


        return jungle + " " + ocean + " " + ice + "\n";
    }
}
