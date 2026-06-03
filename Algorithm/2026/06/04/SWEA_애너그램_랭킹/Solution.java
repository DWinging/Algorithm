import java.io.*;
import java.util.Arrays;

class Solution {
    
    static long[] factorial = new long[21];
    static int[] cnt = new int[26];
    static int[] str = new int[20];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        getFactorial();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            Arrays.fill(cnt, 0);
            
            while(c <= ' ') c = System.in.read();
            int idx = 0;
            while(c >= 'A' && c <= 'Z') {
                int val = c - 'A';
                str[idx++] = val;
                cnt[val]++;
                c = System.in.read();
            }

            long res = 0;
            for(int i = 0; i < idx; i++) {
                int target = str[i];

                for(int j = 0; j < target; j++) {
                    if(cnt[j] > 0) {
                        cnt[j]--;
                        
                        int remainLen = idx - 1 - i; 
                        long totalCase = factorial[remainLen];
                        
                        for(int k = 0; k < 26; k++) {
                            if(cnt[k] > 1) {
                                totalCase /= factorial[cnt[k]];
                            }
                        }
                        
                        res += totalCase;
                        cnt[j]++;
                    }
                }
                cnt[target]--;
            }
            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static void getFactorial() {
        factorial[0] = 1;
        for(int i = 1; i < factorial.length; i++) {
            factorial[i] = factorial[i - 1] * i;            
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