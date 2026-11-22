import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        List<Integer>[] list = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        readInt();
        for(int i = 1; i < n; i++) {
            int p = readInt();
            list[p].add(i);
        }

        System.out.println(solve(list, 0));
    }

    private static int solve(List<Integer>[] list, int node) {
        int len = list[node].size();
        int[] val = new int[len];

        for(int i = 0; i < len; i++) {
            int child = list[node].get(i);
            val[i] = solve(list, child);
        }

        Arrays.sort(val);
        int time = 1, res = 0;
        for(int i = len - 1; i >= 0; i--) {
            int temp = val[i] + time;
            if(temp > res) res = temp; 
            time++;
        }

        return res;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = false;
        if(c == '-') {
            c = System.in.read();
            flag = true;
        }
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}