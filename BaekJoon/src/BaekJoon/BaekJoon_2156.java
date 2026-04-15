package BaekJoon;

import java.io.*;

public class BaekJoon_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numArr = inputWine(br, n);
        int result = countWine(numArr, n);
        System.out.println(result);
    }

    private static int[] inputWine(BufferedReader br, int n) throws IOException {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        return arr;
    }

private static int countWine(int[] arr, int n) {
    if(n == 1) return arr[0];
    if(n == 2) return arr[0] + arr[1];

    int[] dp = new int[n];
    dp[0] = arr[0];
    dp[1] = arr[0] + arr[1];
    dp[2] = maxOf(
        arr[0] + arr[1], // 첫번째, 두번째 잔을 선택한 경우
        arr[0] + arr[2], // 첫번째, 세번째 잔을 선택한 경우
        arr[1] + arr[2]  // 두번째, 세번째 잔을 선택한 경우
    );
    for(int i = 3; i < n; i++) {
        dp[i] = maxOf(
            arr[i] + arr[i - 1] + dp[i - 3], // (i-1), i 번째 잔을 선택한 경우
            arr[i] + dp[i - 2],              // (i-1)번째 잔을 선택하지 않고, i번째 잔을 선택한 경우
            dp[i-1]                          // i번째 잔을 선택하지 않는 경우
        );
    }

    return dp[n-1];
}

    private static int maxOf(int w1, int w2, int w3) {
        return Math.max(w1, Math.max(w2, w3));
    }
}
