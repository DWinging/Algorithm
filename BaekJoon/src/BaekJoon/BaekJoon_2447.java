package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_2447 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] stars = new char[n][n];
        settingStars(stars, 0, n, 0, n, n / 3);
        System.out.println(buildString(stars, n));
    }

    private static void settingStars(char[][] stars, int y_s, int y_e, int x_s, int x_e, int w) {
        int idx = 1;
        for(int y = y_s; y < y_e; y += w) {
            for(int x = x_s; x < x_e; x += w) {
                if(idx != 5) {
                    if(w == 1) stars[y][x] = '*';
                    else settingStars(stars, y, y + w, x, x + w, w / 3);
                }
                idx++;
            }
        }
    }

    private static String buildString(char[][] stars, int n) {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < n ; y++) {
            for(int x = 0; x < n; x++) {
                sb.append(stars[y][x] == '*' ? '*' : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
