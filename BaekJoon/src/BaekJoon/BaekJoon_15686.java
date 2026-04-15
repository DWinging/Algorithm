package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_15686 {

    static int minDistance = Integer.MAX_VALUE;
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Coordinate> chickenHouse = new ArrayList<>();
        ArrayList<Coordinate> house = new ArrayList<>();
        getHouseCoordinate(chickenHouse, house, n);

        int[][] distance = getAllBetweenDistance(chickenHouse, house);
        Deque<Integer> visited = new ArrayDeque<>();
        backtracking(distance, visited, 0, m);

        System.out.println(minDistance);
    }

    private static void getHouseCoordinate(ArrayList<Coordinate> house1, ArrayList<Coordinate> house2, int n) throws IOException {
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                int type = Integer.parseInt(st.nextToken());
                if(type == 1) {
                    house2.add(new Coordinate(i, j));
                }
                else if(type == 2) {
                    house1.add(new Coordinate(i, j));
                }
            }
        }
    }

    private static int[][] getAllBetweenDistance(ArrayList<Coordinate> house1, ArrayList<Coordinate> house2) {
        int[][] distance = new int[house1.size()][house2.size()];
        for(int i = 0; i < house1.size(); i++) {
            for(int j = 0; j < house2.size(); j++) {
                distance[i][j] = getDistance(house1.get(i), house2.get(j));
            }
        }
        return distance;
    }

    private static int getDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
    }

    private static void backtracking(int[][] distance, Deque<Integer> visited, int idx, int m) {
        if(visited.size() >= m || idx >= distance.length) return;
        for(int i = idx; i < distance.length; i++) {
            visited.addLast(i);
            int dist = sumDistance(distance, visited);
            minDistance = Math.min(dist, minDistance);
            backtracking(distance, visited, i + 1, m);
            visited.pollLast();
        }
    }

    private static int sumDistance(int[][] distance, Deque<Integer> visited) {
        int sum = 0;
        int[] list = new int[distance[0].length];
        Arrays.fill(list, Integer.MAX_VALUE);
        for(int i : visited) {
            for(int d = 0; d < list.length; d++) {
                list[d] = Math.min(distance[i][d], list[d]);
            }
        }

        for(int i : list) {
            sum += i;
        }
        return sum;
    }
}

class Coordinate {
    int x, y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}