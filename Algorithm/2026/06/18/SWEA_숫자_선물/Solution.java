import java.io.*;

class Solution {

    static final int MAX_RANGE = 100_001;
    static int[] num = new int[MAX_RANGE];
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for (int t = 1; t <= T; t++) {
            int len = inputNum();
            int x = readInt();
            int y = readInt();

            sb.append('#').append(t).append(' ').append(solve(len, x, y)).append('\n');
        }
        System.out.print(sb);
    }

    private static String solve(int len, int x, int y) {
        StringBuilder sb = new StringBuilder();
        int lastY = -1;
        
        for (int i = 0; i < len; i++) {
            if (num[i] == y) {
                sb.append(y);
                lastY = i;
            } else if (num[i] > y) {
                while (sb.length() < len) sb.append(y);
                break;
            } else if (num[i] > x) {
                if (i == 0 && x == 0) {
                    sb.setLength(0);
                    break;
                }
                sb.append(x);
                while (sb.length() < len) sb.append(y);
                break;
            } else if (num[i] == x) {
                sb.append(x);
            } else {
                if (lastY > -1 && !(lastY == 0 && x == 0)) {
                    sb.setLength(lastY);
                    sb.append(x);
                    while (sb.length() < len) sb.append(y);
                    break;
                } else {
                    sb.setLength(0);
                    break;
                }
            }
        }

        if (sb.length() == 0) {
            if (len == 1) return "-1";
            for (int k = 0; k < len - 1; k++) {
                sb.append(y);
            }
        }

        String res = sb.toString();
        return ("".equals(res) || "0".equals(res)) ? "-1" : res;
    }

    private static int inputNum() throws IOException {
        while (c <= ' ') c = System.in.read();
        int idx = 0;
        while (c >= '0' && c <= '9') {
            num[idx++] = c & 15;
            c = System.in.read();
        }
        return idx;
    }

    private static int readInt() throws IOException {
        while (c <= ' ') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}