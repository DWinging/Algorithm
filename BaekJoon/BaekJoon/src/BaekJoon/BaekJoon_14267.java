package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_14267 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] score = new int[n];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        inputSuperior(list, n, br);
        inputPraise(score, m, br);
        System.out.println(calculatePraise(list, score));
    }

    private static void inputSuperior(ArrayList<ArrayList<Integer>> list, int n, BufferedReader br) throws IOException {
        for(int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        for(int i = 1; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            list.get(num-1).add(i);
        }
    }

    private static void inputPraise(int[] score, int m, BufferedReader br) throws IOException {
        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int praise = Integer.parseInt(st.nextToken());
            score[num-1] += praise;
        }
    }

    private static String calculatePraise(ArrayList<ArrayList<Integer>> list, int[] score) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(score[i]).append(" ");
            for(int num : list.get(i)) {
                score[num] += score[i];
            }
        }
        return sb.toString();
    }
}
