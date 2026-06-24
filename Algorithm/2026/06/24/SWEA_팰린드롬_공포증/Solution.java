import java.util.*;
import java.io.*;

class Solution {

    static int[] arr = new int[3];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            while(c <= ' ') c = System.in.read();
            for(int i = 0; i < 3; i++) arr[i] = 0;

            while(c >= 'a' && c <= 'z') {
                arr[c - 'a']++;
                c = System.in.read();
            }

            Arrays.sort(arr);
            sb.append('#').append(t).append(' ').append(arr[2] - arr[0] <= 1 ? "YES" : "NO").append('\n');
        }
        System.out.print(sb);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}