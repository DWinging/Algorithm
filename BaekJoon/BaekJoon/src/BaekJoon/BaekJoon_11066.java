package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_11066 {

    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            bw.write(solve(br) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] fileList = inputFileList(n, br);
        int[] sumArr = getSumArr(n, fileList);
        return getMinValue(n, sumArr, fileList);
    }

    private static int[] inputFileList(int n, BufferedReader br) throws IOException{
        int[] files = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }
        return files;
    }

    private static int[] getSumArr(int n, int[] fileList) {
        int[] sumArr = new int[n];
        sumArr[0] = fileList[0];
        for(int i = 1; i < n; i++) {
            sumArr[i] = sumArr[i-1] + fileList[i];
        }
        return sumArr;
    }

    private static int getMinValue(int n, int[] sumArr, int[] fileList) {
        int[][] values = new int[n][n];

        for(int i = 0; i < n-1; i++) {
            values[i][i + 1] = fileList[i] + fileList[i + 1];
        }

        for(int len = 3; len <= n; len++) {
            for(int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                values[i][j] = INF;
                for(int k = i; k < j; k++) {
                    int temp = values[i][k] + values[k + 1][j] + sumOf(i, j, sumArr);
                    values[i][j] = Math.min(values[i][j], temp);
                }
            }
        }
        return values[0][n-1];
    }

    private static int sumOf(int i, int j, int[] sumArr) {
        return i == 0 ? sumArr[j] : sumArr[j] - sumArr[i-1];
    }
}
