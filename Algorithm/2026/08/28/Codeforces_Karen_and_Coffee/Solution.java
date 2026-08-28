import java.util.*;
import java.io.*;

class Main {

    static final int INF = 200_000;
    static int[] arr = new int[INF + 2];
    static int[] recipe = new int[INF + 2];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int n = readInt();
        int k = readInt();
        int q = readInt();

        int min = INF, max = -1;
        while(n-- > 0) {
            int l = readInt();
            int r = readInt();
            if(min > l) min = l;
            if(max < r) max = r;

            arr[l]++;
            arr[r + 1]--;
        }

        for(int i = 1; i <= INF; i++) {
            arr[i] += arr[i - 1];
            recipe[i] = recipe[i - 1] + (arr[i] >= k ? 1 : 0);
        }

        while(q-- > 0) {
            int l = readInt();
            int r = readInt();

            int cnt = recipe[r] - recipe[l - 1];
            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
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