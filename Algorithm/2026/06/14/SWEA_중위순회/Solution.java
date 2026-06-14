import java.util.*;
import java.io.*;

class Solution {

    private static class Node {
        String val;
        int leftNode = -1, rightNode = -1;

        void inputInfo(String val, int leftNode, int rightNode) {
            this.val = val;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    static final int MAX_NODE = 100;
    static final int ROOT = 1;

    static StringBuilder sb = new StringBuilder();
    static Node[] tree = new Node[MAX_NODE + 1];
    static { for(int i = 1; i <= MAX_NODE; i++) tree[i] = new Node(); }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = 10;
        
        for(int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                tree[num].inputInfo(st.nextToken(), -1, -1);
                
                if(st.hasMoreTokens()) {
                    tree[num].leftNode = Integer.parseInt(st.nextToken());

                    if(st.hasMoreTokens()) {
                        tree[num].rightNode = Integer.parseInt(st.nextToken());
                    }
                }
            }

            sb.append('#').append(t).append(' ');
            solve(ROOT);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void solve(int node) {
        if(tree[node].leftNode != -1) solve(tree[node].leftNode);
        sb.append(tree[node].val);
        if(tree[node].rightNode != -1) solve(tree[node].rightNode);
    }
}