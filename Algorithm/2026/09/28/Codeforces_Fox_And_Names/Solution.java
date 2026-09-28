import java.util.*;
import java.io.*;

class Main {

    final static int LEN = 26;
    
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();

        List<Integer>[] list = new ArrayList[LEN];
        int[] cnt = new int[LEN];

        for(int i = 0; i < LEN; i++) {
            list[i] = new ArrayList<>();
        }

        int[][] str = new int[2][101];
        inputString(str[0]);

        int idx = 0;
        boolean flag = true;
        for(int i = 1; i < n; i++) {
            inputString(str[idx ^ 1]);
            flag &= compare(str, idx, idx ^ 1, list, cnt);
            idx ^= 1;
        }

        System.out.println(flag ? topologicalSort(list, cnt) : "Impossible");
    }

    private static void inputString(int[] str) throws IOException {
        while(c <= ' ') c = System.in.read();
        int idx = 0;
        while(c >= 'a' && c <= 'z') {
            str[++idx] = c - 'a';
            c = System.in.read();
        }
        str[0] = idx;
    }

    private static boolean compare(
        int[][] str,
        int idx1,
        int idx2,
        List<Integer>[] list,
        int[] cnt
    ) {
        int total = Math.min(str[idx1][0], str[idx2][0]);
        for(int i = 1; i <= total; i++) {
            int val1 = str[idx1][i];
            int val2 = str[idx2][i];
            if(val1 != val2) {
                list[val1].add(val2);
                cnt[val2]++;
                return true;
            }
        }

        return str[idx1][0] <= str[idx2][0];
    }

    private static String topologicalSort(
        List<Integer>[] list,
        int[] cnt
    ) {
        int[] que = new int[LEN];
        int left = 0, right = 0;

        for(int i = 0; i < LEN; i++) {
            if(cnt[i] == 0) {
                que[right++] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(left < right) {
            int cur = que[left++];
            sb.append((char) (cur + 'a'));

            for(int next : list[cur]) {
                cnt[next]--;
                if(cnt[next] == 0) {
                    que[right++] = next;
                }
            }
        }

        return right == LEN ? sb.toString() : "Impossible";
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