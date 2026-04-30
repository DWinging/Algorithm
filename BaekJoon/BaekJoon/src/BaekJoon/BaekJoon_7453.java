package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_7453 {

    final static int ARRAY_KIND = 4;
    final static int TARGET = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] array = inputArray(n, br);

        long[] sumArr1 = new long[n * n];
        long[] sumArr2 = new long[n * n];
        calculateSumOfArray(sumArr1, sumArr2, array, n);
        System.out.println(count(sumArr1, sumArr2));
    }

    private static int[][] inputArray(int n, BufferedReader br) throws IOException {
        int[][] arr = new int[n][ARRAY_KIND];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < ARRAY_KIND; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return arr;
    }

    private static void calculateSumOfArray(long[] sumArr1, long[] sumArr2, int[][] array, int n) {
        for(int i = 0; i < n; i++) {
            long n1 = array[i][0];
            long n2 = array[i][2];
            for(int j = 0; j < n; j++) {
                sumArr1[(i * n) + j] =  n1 + array[j][1];
                sumArr2[(i * n) + j] =  n2 + array[j][3];
            }
        }

        Arrays.sort(sumArr1);
        Arrays.sort(sumArr2);
    }

    private static long count(long[] sumArr1, long[] sumArr2) {
        long cnt = 0;
        int len = sumArr1.length;
        int left = 0, right = len-1;
        while(left < len && right >= 0) {
            long sum = sumArr1[left] + sumArr2[right];

            if(sum == TARGET) {
                long val1 = sumArr1[left];
                long val2 = sumArr2[right];
                long cnt1 = 0, cnt2 = 0;

                while(left < len && val1 == sumArr1[left]) {
                    cnt1++;
                    left++;
                }

                while(right >= 0 && val2 == sumArr2[right]) {
                    cnt2++;
                    right--;
                }

                cnt += cnt1 * cnt2;
            }
            else if(sum < TARGET) {
                left++;
            }
            else {
                right--;
            }
        }
        return cnt;
    }
}
