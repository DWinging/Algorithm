package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_18231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> nodes = inputArray(n, m, br);

        int[] cities = inputCities(br);
        System.out.println(checkMap(nodes, cities));
    }

    private static ArrayList<ArrayList<Integer>> inputArray(int n, int m, BufferedReader br) throws IOException {
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        StringTokenizer st;
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes.get(u).add(v);
            nodes.get(v).add(u);
        }

        return nodes;
    }

    private static int[] inputCities(BufferedReader br) throws IOException {
        int k = Integer.parseInt(br.readLine());
        int[] cities = new int[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cities);
        return cities;
    }

    private static String checkMap(ArrayList<ArrayList<Integer>> nodes, int[] cities) {
        ArrayList<Integer> list = new ArrayList<>();
        Set<Integer> destroy = new HashSet<>();
        for(int city : cities) {
            destroy.add(city);
        }

        Set<Integer> set = new HashSet<>();
        for(int city : cities) {
            if(checkBomb(nodes.get(city), destroy, set)) {
                list.add(city);
                set.add(city);
            }
        }

        return set.size() != destroy.size() ? "-1" : printCities(list);
    }

    private static boolean checkBomb(ArrayList<Integer> cities, Set<Integer> destroy, Set<Integer> set) {
        for(int city : cities) {
            if(!destroy.contains(city)) return false;
        }

        set.addAll(cities);
        return true;
    }

    private static String printCities(ArrayList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for(int city : list) {
            sb.append(city).append(" ");
        }
        return sb.toString();
    }
}
