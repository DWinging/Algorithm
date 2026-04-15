package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_9694 {

    static int[] dict, idxList;
    static ArrayList<ArrayList<Person>> person;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc = 1; tc <= testCase; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            dict = new int[m];
            idxList = new int[m];
            person = new ArrayList<>();
            for(int i = 0; i < m; i++){
                person.add(new ArrayList<>());
            }

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                person.get(p1).add(new Person(p2, w));
                person.get(p2).add(new Person(p1, w));
            }

            sb.append("Case #").append(tc).append(": ").append(dijkstra(0, m-1)).append("\n");
        }

        System.out.println(sb);
    }

    private static String dijkstra(int s, int e) {
        Arrays.fill(dict, INF);
        Arrays.fill(idxList, -1);
        dict[s] = 0;
        PriorityQueue<Person> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.value, v2.value));
        pq.offer(new Person(s, 0));

        while(!pq.isEmpty()) {
            Person p = pq.poll();
            int cur = p.num;
            int curValue = p.value;
            if(cur == person.size()-1) break;
            if(curValue > dict[cur]) continue;
            for(Person next : person.get(cur)) {
                int nextValue = curValue + next.value;
                if(dict[next.num] > nextValue) {
                    dict[next.num] = nextValue;
                    idxList[next.num] = cur;
                    pq.offer(new Person(next.num, nextValue));
                }
            }
        }

        if(dict[e] == INF) return "-1";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int idx = e;
        while(idx != -1) {
            stack.push(idx);
            idx = idxList[idx];
        }
        for(int i = stack.size()-1; i >= 0; i--) {
            sb.append(stack.get(i)).append(" ");
        }
        return sb.toString();
    }

    private static class Person {
        int num;
        int value;

        Person(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }
}
