import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        
        int n = readInt();
        int m = readInt();
        
        int[] parents = new int[n + 1];
        int[] cnt = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
            cnt[i] = 1;
        }

        while(m-- > 0) {
            int num = readInt();
            if(num == 0) continue;
            
            int a = readInt();
            while(num-- > 1) {
                int b = readInt();
                union(parents, cnt, a, b);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            int p = find(parents, i);
            sb.append(cnt[p]).append(' ');
        }
        System.out.println(sb);
    }

    private static void union(
        int[] parents,
        int[] cnt,
        int a,
        int b
    ) {
        int pA = find(parents, a);
        int pB = find(parents, b);

        if(pA != pB) {
            if(cnt[pA] > cnt[pB]) {
                parents[pB] = pA;
                cnt[pA] += cnt[pB];
            } else {
                parents[pA] = pB;
                cnt[pB] += cnt[pA];
            }
        }
    }

    private static int find(int[] parents, int p) {
        if(parents[p] == p) return p;
        else return parents[p] = find(parents, parents[p]);
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