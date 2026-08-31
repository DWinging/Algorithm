import java.util.*;
import java.io.*;

class Main {

    static final int INF = 1_000_300_005;
    
    static int[] arr = new int[300_001];
    static int[] stack = new int[300_001];
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        while(T-- > 0) {
            int n = readInt();
            int k = readInt();

            for(int i = 1; i <= n; i++) { arr[i] = INF; }

            PriorityQueue<int[]> pq = new PriorityQueue<>((l1, l2) -> {
                return l1[1] - l2[1];
            });

            int top = -1;
            for(int i = 0; i < k; i++) {
                stack[++top] = readInt();
            }

            for(int i = 0; i < k; i++) {
                int a = stack[i];
                int t = readInt();

                pq.add(new int[]{a, t, 1});
                pq.add(new int[]{a, t, -1});
                arr[a] = t;
            }

            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int idx = cur[0];
                int t = cur[1];
                int d = cur[2];

                if(arr[idx] < t) continue;

                if(idx + d > 0 && idx + d <= n && arr[idx + d] > t + 1) {
                    pq.add(new int[]{idx + d, t + 1, d});
                    arr[idx + d] = t + 1;
                }
            }

            for(int i = 1; i <= n; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
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