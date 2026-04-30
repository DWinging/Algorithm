package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_1826 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] gasStation = inputGasStation(n, br);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int location = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        System.out.println(countStop(location, fuel, n, gasStation));
    }

    private static int[][] inputGasStation(int n, BufferedReader br) throws IOException {
        int[][] gasStation = new int[n][2];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            gasStation[i][0] = Integer.parseInt(st.nextToken());
            gasStation[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(gasStation, (n1, n2) -> Integer.compare(n1[0], n2[0]));
        return gasStation;
    }

    private static int countStop(int location, int fuel, int n, int[][] gasStation) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int cnt = 0, idx = 0;
        while(fuel < location) {
            while(idx < n && fuel >= gasStation[idx][0]) {
                pq.add(gasStation[idx][1]);
                idx++;
            }
            if(!pq.isEmpty()) {
                fuel += pq.poll();
                cnt++;
            }
            else break;
        }
        return fuel < location ? -1 : cnt;
    }
}
