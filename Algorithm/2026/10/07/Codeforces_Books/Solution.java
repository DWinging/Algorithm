import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args)  throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        int[] books = new int[n];
        for(int i = 0; i < n; i++) 
            books[i] = readInt();

        int res = 0, time = 0, right = 0;
        for(int left = 0; left < n; left++) {
            while(right < n && time + books[right] <= m) {
                time += books[right++];
            }

            if(right - left > res) {
                res = right - left;
            }

            time -= books[left];
        }

        System.out.println(res);
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