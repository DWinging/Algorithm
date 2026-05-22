import java.io.*;

class Solution {

    static final int MAX_SIZE = 5_000;

    static int[] counts = new int[MAX_SIZE + 1];
    
    static StringBuilder sb = new StringBuilder();
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            for(int i = 1; i <= MAX_SIZE; i++) counts[i] = 0;
            int n = readInt();
            countingNum(n);
            
            sb.append('#').append(t);
            solve(n);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void countingNum(int n) throws IOException {
        while(n-- > 0) {
            int num = readInt();
            counts[num]++;
        }
    }

    private static void solve(int n) {
        int num = searchNextNum(1);

        while(n > 0) {
            if(counts[num] == n) {
                while(counts[num]-- > 0) sb.append(' ').append(num);
                break;
            }

            if(counts[num + 1] == 0) {
                n -= counts[num];
                while(counts[num]-- > 0) sb.append(' ').append(num);
                num = searchNextNum(num + 1);
            } else {
                if(counts[num] + counts[num + 1] == n) {
                    int temp = num + 1;
                    while(counts[temp]-- > 0) sb.append(' ').append(temp);
                    while(counts[num]-- > 0) sb.append(' ').append(num);
                    n = 0;
                } else {
                    n -= counts[num] + 1;
                    while(counts[num]-- > 0) sb.append(' ').append(num);
    
                    int temp = searchNextNum(num + 2);
                    sb.append(' ').append(temp);
                    counts[temp]--;
    
                    num = num + 1;
                }    
            }            
        }
    }

    private static int searchNextNum(int num) {
        for(int i = num; i <= MAX_SIZE; i++) {
            if(counts[i] > 0) {
                return i;
            }
        }
        return -1;
    }
    
    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' &&  c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}