import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int k = readInt();
        int d = readInt();

        int[][] rules = new int[k][3];
        for(int i = 0; i < k; i++) {
            rules[i][0] = readInt();
            rules[i][1] = readInt();
            rules[i][2] = readInt();
        }

        System.out.println(binarySearch(rules, n, k, d));
    }

    private static int binarySearch(int[][] rules, int n, int k, int d) {
        int left = 0, right = n, res = 0;

        while(left <= right) {
            int mid = (left + right) >> 1;
            
            if(solve(rules, mid, k, d)) {
                res = mid;
                right = mid - 1;    
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private static boolean solve(int[][] rules, int target, int k, int d) {
        long cnt = 0;
        for(int i = 0; i < k; i++) {
            if(rules[i][0] <= target) {
                int temp = Math.min(target, rules[i][1]);
                cnt += (temp - rules[i][0]) / rules[i][2] + 1;
            }
        }

        return cnt >= d;
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