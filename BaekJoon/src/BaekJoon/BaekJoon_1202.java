package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);
        int[][] gemstones = inputGemstones(n, br);
        int[] bags = inputBag(k, br);
        System.out.println(getMaxValue(n, gemstones, bags));
    }

    private static int[][] inputGemstones(int n, BufferedReader br) throws IOException {
        int[][] gemstones = new int[n][2];
        for(int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            gemstones[i][0] = Integer.parseInt(temp[0]);
            gemstones[i][1] = Integer.parseInt(temp[1]);
        }
        Arrays.sort(gemstones, (n1, n2) -> Integer.compare(n1[0], n2[0]));
        return gemstones;
    }

    private static int[] inputBag(int k, BufferedReader br) throws IOException {
        int[] bags = new int[k];
        for(int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);
        return bags;
    }

    private static long getMaxValue(int n, int[][] gemstones, int[] bags) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long value = 0;
        int idx = 0;
        for(int bag : bags) {
            while(idx < n && bag >= gemstones[idx][0]) {
                pq.add(gemstones[idx][1]);
                idx++;
            }
            if(!pq.isEmpty()) { value += pq.poll(); }
        }
        return value;
    }
}