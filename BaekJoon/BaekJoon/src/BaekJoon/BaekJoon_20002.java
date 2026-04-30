package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_20002 {

    static final int MIN_VALUE = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = inputMatrix(n, br);
        System.out.println(maxOf(matrix, n));
    }

    private static int[][] inputMatrix(int n, BufferedReader br) throws IOException {
        int[][] matrix = new int[n + 1][n + 1];
        StringTokenizer st;

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                matrix[i][j] = matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
                matrix[i][j] += Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }

    private static int maxOf(int[][] matrix, int n) {
        int value = MIN_VALUE;
        for(int size = n; size > 0; size--) {
            for(int y = n; y >= size; y--) {
                for(int x = n; x >= size; x--) {
                    int ny = y - size;
                    int nx = x - size;
                    int temp = matrix[y][x] - matrix[ny][x] - matrix[y][nx] + matrix[ny][nx];
                    value = Math.max(value, temp);
                }
            }
        }
        return value;
    }
}
