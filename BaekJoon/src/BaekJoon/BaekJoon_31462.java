package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_31462 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] tree = new char[n][n];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < str.length(); j++) {
                tree[i][j] = str.charAt(j);
            }
        }

        System.out.println(solve(tree, n));
    }

    private static int solve(char[][] tree, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i + 1; j++) {
                char c = tree[i][j];
                if(c == 'R') {
                    if(i + 1 == n) return 0;
                    if(tree[i + 1][j] != 'R' || tree[i + 1][j + 1] != 'R') return 0;
                    tree[i + 1][j] = 'x';
                    tree[i + 1][j + 1] = 'x';
                }
                if(c == 'B') {
                    if(i + 1 == n || j == i) return 0;
                    if(tree[i][j + 1] != 'B' || tree[i + 1][j + 1] != 'B') return 0;
                    tree[i][j + 1] = 'x';
                    tree[i + 1][j + 1] = 'x';
                }
                tree[i][j] = 'x';
            }
        }
        return 1;
    }
}
