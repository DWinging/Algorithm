package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_4256 {

    static StringBuilder postorder = new StringBuilder();
    static int[] preorder;
    static int[] inorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());


        while(testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            preorder = new int[n];
            inorder = new int[n];

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                preorder[i] = Integer.parseInt(st1.nextToken());
                inorder[i] = Integer.parseInt(st2.nextToken());
            }

            searchPostorder(0, n-1, 0, n-1);
            postorder.append("\n");
        }

        System.out.println(postorder);
    }

    private static void searchPostorder(int sp, int ep, int si, int ei) {
        if(sp <= ep && si <= ei) {
            int root = preorder[sp];
            int index = 0;
            for(int i = si; i <= ei; i++) {
                if (inorder[i] == root) {
                    index = i;
                    break;
                }
            }
            searchPostorder(sp + 1, sp + index - si, si, index - 1);
            searchPostorder(sp + index - si + 1, ep, index + 1, ei);
            postorder.append(root).append(" ");
        }
    }
}
