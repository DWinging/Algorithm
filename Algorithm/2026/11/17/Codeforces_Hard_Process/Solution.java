import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        
        int n = readInt();
        int k = readInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }

        int res = solve(arr, n, k);
        System.out.println(res);
        System.out.println(buildString(arr));
    }

    private static int solve(int[] arr, int n, int k) {
        Queue<Integer> que = new ArrayDeque<>();        
        int start = 0, cnt = 0;
        int left = 0, right = -1;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) {
                if(k == 0) {
                    start = i + 1;
                }
                else if(que.size() < k) {
                    que.add(i);
                } else {
                    start = que.poll() + 1;
                    que.add(i);
                }
            }

            if(cnt < i - start + 1) {
                cnt = i - start + 1;
                left = start;
                right = i;
            }
        }

        for(int i = left; i <= right; i++) {
            arr[i] = 1;
        }

        return cnt;
    }

    private static String buildString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i : arr) {
            sb.append(i).append(' ');
        }
        return sb.toString();
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