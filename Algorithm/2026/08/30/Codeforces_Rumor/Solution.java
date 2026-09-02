import java.io.*;

class Main {

    static int[] parents, value;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        parents = new int[n + 1];
        value = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
            value[i] = readInt();
        }

        while(m-- > 0) {
            int x = readInt();
            int y = readInt();

            union(x, y);
        }

        long res = 0;
        for(int i = 1; i <= n; i++) {
            if(parents[i] == i) res += value[i];
        }

        System.out.println(res);
    }

    private static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA != pB) {
            if(value[pA] > value[pB]) {
                parents[pA] = pB;
            } else {
                parents[pB] = pA;
            }
        }
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        else return parents[p] = find(parents[p]);
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