package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[] checkPerson = inputPersons(n, br);
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<int[]> parties = inputParty(map, m, br);
        System.out.println(countParty(map, parties, checkPerson));
    }

    private static boolean[] inputPersons(int n, BufferedReader br) throws IOException {
        boolean[] checkPerson = new boolean[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++) {
            checkPerson[Integer.parseInt(st.nextToken())] = true;
        }
        return checkPerson;
    }

    private static ArrayList<int[]> inputParty(Map<Integer, ArrayList<Integer>> map, int m, BufferedReader br) throws IOException {
        ArrayList<int[]> parties = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int[] temp = new int[size];
            for(int j = 0; j < size; j++) {
                int key = Integer.parseInt(st.nextToken());
                temp[j] = key;
                if(!map.containsKey(key)) map.put(key, new ArrayList<>());
                map.get(key).add(i);
            }
            parties.add(temp);
        }
        return parties;
    }

    private static int countParty(Map<Integer, ArrayList<Integer>> map, ArrayList<int[]> parties, boolean[] person) {
        int cnt = parties.size();
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i < person.length; i++) {
            if(person[i]) deque.addLast(i);
        }

        boolean[] visited = new boolean[cnt];
        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            if(!map.containsKey(cur)) continue;
            for(int i : map.get(cur)) {
                if(!visited[i]) {
                    visited[i] = true;
                    cnt--;
                }
                for(int j : parties.get(i)) {
                    if(!person[j]) {
                        person[j] = true;
                        deque.addLast(j);
                    }
                }
            }
        }

        return cnt;
    }
}
