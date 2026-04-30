package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] info = new long[n + 1];
        List<List<Integer>> tree = inputInfo(info, n, br);
        System.out.println(countSheep(tree, info, 1));
    }

    private static List<List<Integer>> inputInfo(long[] info, int n, BufferedReader br) throws IOException {
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String kind = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            int parents = Integer.parseInt(st.nextToken());

            info[i] = kind.equals("S") ? cnt : cnt * (-1);
            tree.get(parents).add(i);
        }

        return tree;
    }

    private static long countSheep(List<List<Integer>> tree, long[] info, int cur) {
        for(int i : tree.get(cur)) {
            info[cur] += countSheep(tree, info, i);
        }
        return Math.max(info[cur], 0);
    }
}
