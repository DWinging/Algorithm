package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2263 {

    static StringBuilder sb = new StringBuilder();
    static int[] inOrder, postOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        postOrder = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            inOrder[i] = Integer.parseInt(st1.nextToken());
            postOrder[i] = Integer.parseInt(st2.nextToken());
        }

        preOrder(0, n-1, 0, n-1);
        System.out.println(sb);
    }

    private static void preOrder(int is, int ie, int ps, int pe) {
        if(is <= ie && ps <= pe) {
            int node = postOrder[pe];
            sb.append(node).append(" ");
            int pos = is;
            for(int i = is; i <= ie; i++){
                if(inOrder[i] == node) {
                    pos = i;
                    break;
                }
            }

            preOrder(is, pos-1, ps, ps + pos - is -1);
            preOrder(pos + 1, ie, ps + pos - is, pe - 1);
        }
    }
}
