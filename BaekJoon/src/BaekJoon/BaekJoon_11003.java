package BaekJoon;

import java.io.IOException;

public class BaekJoon_11003 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int n = readInt();
        int m = readInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }

        int[] order = new int[5_000_000];
        int left = 0, right = 0;

        for(int i = 0; i < n; i++) {
            while(left < right && arr[i] <= arr[order[right - 1]]) {
                right--;
            }

            order[right++] = i;

            while(order[left] <  i - m + 1) {
                left++;
            }
            sb.append(arr[order[left]]).append(" ");
        }
        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int c;
        // 공백, 줄바꿈 등은 건너뜀 (ASCII 32 이하)
        while ((c = System.in.read()) <= 32);

        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }

        int n = 0;
        // 숫자('0'~'9')가 나오는 동안 계속 읽어서 자릿수 올림
        // (c - '0') 대신 (c & 15)를 쓰면 비트 연산이라 미세하게 더 빠름
        do {
            n = (n << 3) + (n << 1) + (c & 15); // n * 10 + (c - '0')
        } while ((c = System.in.read()) > 32);

        return negative ? -n : n;
    }
}
