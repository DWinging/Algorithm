package SWEA_수_만들기;
import java.util.*;
import java.io.*;

class Solution {

    static final int MAX_SIZE = 10;
    static final int X = 0, D = 1;

    static Map<Integer, Integer> visited = new HashMap<>();
    static int[] arr = new int[MAX_SIZE];
    static int c, res;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
                
            for(int i = 0; i < n; i++) {
                arr[i] = readInt();
            }
            visited.clear();

            int target = readInt();
            res = target;
            solve(n, target, 0);

            sb.append('#').append(t).append(' ').append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static void solve(int n, int target, int cnt) {
        if(target < 0 || cnt >= res) return;
        if (visited.containsKey(target) && visited.get(target) <= cnt) return;
        visited.put(target, cnt);

        if(target == 0 || target == 1) {
            if(res > cnt + target) res = cnt + target;
            return;
        }

        for(int i = 0; i < n; i++) {
            int val = target / arr[i];
            int mod = target % arr[i];

            solve(n, val, cnt + mod);
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