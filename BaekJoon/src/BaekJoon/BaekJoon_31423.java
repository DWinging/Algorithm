package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_31423 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] names = inputNames(n, br);

        ArrayList<ArrayList<Integer>> list = getList(n);

        int cur = getStartNode(list, n, br);
        dfs(cur, names, list, bw);
        bw.flush();
        bw.close();
    }

    private static String[] inputNames(int n, BufferedReader br) throws IOException {
        String[] names = new String[n + 1];
        for(int i = 1; i < names.length; i++) {
            names[i] = br.readLine();
        }
        return names;
    }

    private static ArrayList<ArrayList<Integer>> getList(int n) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        return list;
    }

    private static int getStartNode(ArrayList<ArrayList<Integer>> list, int n, BufferedReader br) throws IOException {
        StringTokenizer st;
        boolean[] check = new boolean[n + 1];
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int s_i = Integer.parseInt(st.nextToken());
            int s_j = Integer.parseInt(st.nextToken());
            list.get(s_i).add(s_j);
            check[s_j] = true;
        }

        return checkNode(check);
    }

    private static int checkNode(boolean[] check) {
        for(int i = 1; i < check.length; i++) {
            if(!check[i]) {
                return i;
            }
        }
        return -1;
    }

    private static void dfs(int idx, String[] names, ArrayList<ArrayList<Integer>> list, BufferedWriter bw) throws IOException {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(idx);
        while(!deque.isEmpty()) {
            int cur = deque.pollLast();
            bw.write(names[cur]);

            ArrayList<Integer> temp = list.get(cur);
            for(int i = temp.size() - 1; i >= 0; i--) {
                deque.addLast(temp.get(i));
            }
        }
    }
}
