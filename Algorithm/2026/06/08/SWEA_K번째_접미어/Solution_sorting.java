import java.util.*;
import java.io.*;

class Solution {

    static final int MAX_SIZE = 400;
    static int[] input = new int[MAX_SIZE];
    static int c, total = 0;
    
    static class Suffix implements Comparable<Suffix> {
        int start;
        int len; 
    
        public Suffix(int start, int len) {
            this.start = start;
            this.len = len;
        }
    
        @Override
        public int compareTo(Suffix o) {
            int i = this.start;
            int j = o.start;
            
            while (i < total && j < total) {
                if (input[i] != input[j]) {
                    return input[i] - input[j];
                }
                i++;
                j++;
            }
     
            return o.len - this.len;
        }
    }


    static Suffix[] suffix = new Suffix[MAX_SIZE];

    static {
        for(int i = 0; i < MAX_SIZE; i++) {
            suffix[i] = new Suffix(0, 0);
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int k = readInt();
            inputChar();

            Arrays.sort(suffix, 0, total);
            sb.append('#').append(t).append(' ');
            buildString(sb, k);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void inputChar() throws IOException {
        total = 0;
        while(c <= ' ') c = System.in.read();
        while(c >= 'a' && c <= 'z') {
            suffix[total].start = total;
            suffix[total].len = total;
            input[total] = c;
            
            total++;
            c = System.in.read();
        }
    }

    private static void buildString(StringBuilder sb, int k) {
        for(int i = suffix[k - 1].start; i < total; i++) {
            sb.append((char)input[i]);
        }
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