package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_9489 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n == 0 && k == 0) break;

            int[] arr = new int[n];
            int idx = InputArray(arr, n, k, br);

            int[] parents = new int[n];
            Map<Integer, ArrayList<Integer>> tree = new HashMap<>();
            settingTrees(tree, parents, arr);
            bw.write(getNodes(tree, parents, idx) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int InputArray(int[] arr, int n, int k, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == k) idx = i;
        }

        return idx;
    }

    private static void settingTrees(Map<Integer, ArrayList<Integer>> tree, int[] parents, int[] arr) {
        int p = -1, child = -1;
        parents[0] = -1;
        for(int i = 1; i < arr.length; i++) {
            if (arr[i] != child + 1) {
                p++;
                tree.put(p, new ArrayList<>());
            }
            parents[i] = p;
            tree.get(p).add(i);
            child = arr[i];
        }
    }

    private static int getNodes(Map<Integer, ArrayList<Integer>> tree, int[] parents, int k) {
        if(parents[k] <= 0) return 0;

        int parent = parents[k];
        int grandParent = parents[parent];
        int cnt = 0;
        for(int p : tree.get(grandParent)) {
            if(!tree.containsKey(p) || p == parent) continue;
            cnt += tree.get(p).size();
        }
        return cnt;
    }
}
