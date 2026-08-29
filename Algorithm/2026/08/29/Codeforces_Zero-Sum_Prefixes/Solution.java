import java.util.*;
import java.io.*;

class Main {

    final static int INF = 200_000;
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        
        int[] arr = new int[INF];       
        HashMap<Long, Integer> map = new HashMap<>();
        
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            for(int i = 0; i < n; i++) arr[i] = readInt();

            long sum = 0;
            int idx = 0, cnt = 0;
            while(idx < n && arr[idx] != 0) {
                sum += arr[idx++];
                if(sum == 0) cnt++;
            }

            int temp = 0;
            for(int i = idx; i < n; i++) {
                sum += arr[i];
                if(arr[i] == 0) {
                    map.clear();
                    map.put(sum, 1);
                    cnt += temp;
                    temp = 1;
                } else {
                    int val = map.getOrDefault(sum, 0) + 1;
                    map.put(sum, val);
                    if(temp < val) temp = val;
                }
            }

            cnt += temp;
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = true;
        if(c == '-') {flag = false; c = System.in.read();}
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? n : -n;
    }
}