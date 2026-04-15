package BaekJoon;

import java.io.IOException;

public class BaekJoon_14939 {
    final static int SIZE = 10;
    public static void main(String[] args) throws IOException {
        int[] sign = inputArray();
        System.out.println(toggleSwitch(sign));
    }

    private static int[] inputArray() throws IOException {
        int c = System.in.read();
        int[] sign = new int[SIZE];
        for(int i = 0; i < SIZE; i++) {
            while(c != 'O' && c != '#') c = System.in.read();
            for(int j = 0; j < SIZE; j++) {
                if(c == 'O') sign[i] |= 1 << j;
                c = System.in.read();
            }
        }
        return sign;
    }

    private static int toggleSwitch(int[] sign) {
        int line1 = 0B1;
        int line2 = 0B111;
        int cnt = 0;
        for(int i = 0; i < SIZE; i++) {
            for(int j = 1; j < SIZE; j++) {
                if((sign[i] & (1 << j)) != 0) {
                    if(i - 1 >= 0) sign[i - 1] ^= (line1 << j);
                    sign[i] ^= (line2 << (j - 1));
                    if(i + 1 < SIZE) sign[i + 1] ^= (line1 << j);
                    cnt++;
                }
            }
        }

        for(int i : sign) {
            if(i > 0) return -1;
        }
        return cnt;
    }
}
