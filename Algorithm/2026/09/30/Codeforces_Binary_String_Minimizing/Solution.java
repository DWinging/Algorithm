import java.io.*;

public class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int t = readInt();

        while (t-- > 0) {
            int n = readInt();
            long k = readLong();
            int pendingOnes = 0;

            while (c <= ' ') {
                c = System.in.read();
            }

            while (n > 0 && k > 0) {
                n--;

                int value = c & 15;
                c = System.in.read();

                if (value == 1) {
                    pendingOnes++;
                    continue;
                }

                long remain = k - pendingOnes;
                k -= pendingOnes;

                if (remain >= 0) {
                    sb.append('0');
                    continue;
                }

                while (remain++ < 0) {
                    sb.append('1');
                    pendingOnes--;
                }

                sb.append('0');
            }

            while (pendingOnes-- > 0) {
                sb.append('1');
            }

            while (n-- > 0) {
                int value = c & 15;
                c = System.in.read();

                sb.append(value);
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

    private static int readInt() throws IOException {
        while (c <= ' ') {
            c = System.in.read();
        }

        int n = 0;

        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }

    private static long readLong() throws IOException {
        while (c <= ' ') {
            c = System.in.read();
        }

        long n = 0;

        while (c >= '0' && c <= '9') {
            n = n * 10 + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}