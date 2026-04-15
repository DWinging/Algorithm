package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16236 {

    final static int SIZE = 2;
    final static int BABY_SHARK = 9;
    final static int FISH_KIND = 6;
    static int[][] visited;
    static int[] fish;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Shark shark = new Shark(0, 0, SIZE, 0);
        fish = new int[FISH_KIND + 1];
        n = Integer.parseInt(br.readLine());
        int[][] area = inputArea(shark, br);
        visited = new int[n][n];
        System.out.println(getTime(shark, area));
    }

    private static int[][] inputArea(Shark shark, BufferedReader br) throws IOException {
        int[][] area = new int[n][n];
        StringTokenizer st;
        for(int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < n; x++) {
                int temp = Integer.parseInt(st.nextToken());
                area[y][x] = temp;
                if(temp == BABY_SHARK) {
                    shark.y = y;
                    shark.x = x;
                    area[y][x] = 0;
                }
                else if(temp >= 1 && temp <= FISH_KIND) {
                    fish[temp]++;
                }
            }
        }
        return area;
    }

    private static int getTime(Shark shark, int[][] area) {
        int total = fish[shark.size - 1];
        int time = 0;
        int round = 1;
        while(total > 0) {
            int temp = bfs(shark, area, round++);
            if(temp != -1) {
                time += temp;
                shark.stack++;
                total--;

                if(shark.size <= FISH_KIND && shark.stack == shark.size) {
                    total += fish[shark.size];
                    shark.stack = 0;
                    shark.size++;
                }
            }
            else {
                break;
            }
        }
        return time;
    }

    private static int bfs(Shark shark, int[][] area, int round) {
        int[][] dict = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(shark.y, shark.x, 0));
        int size = shark.size;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int ry = cur.y;
            int rx = cur.x;
            int t = cur.time;

            if(area[ry][rx] > 0 && area[ry][rx] < size) {
                shark.y = ry;
                shark.x = rx;
                area[ry][rx] = 0;
                return t;
            }

            for(int[] d : dict) {
                int ny = ry + d[0];
                int nx = rx + d[1];
                if(check(ny, nx) && area[ny][nx] <= size && visited[ny][nx] != round) {
                    pq.add(new Node(ny, nx, t + 1));
                    visited[ny][nx] = round;
                }
            }
        }
        return -1;
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    private static class Shark {
        int y, x, size, stack;

        Shark(int y, int x, int size, int stack) {
            this.y = y;
            this.x = x;
            this.size = size;
            this.stack = stack;
        }
    }

    private static class Node implements Comparable<Node> {
        int y;
        int x;
        int time;

        Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        public int compareTo(Node node) {
            if(this.time != node.time) return Integer.compare(this.time, node.time);
            else if(this.y != node.y) return Integer.compare(this.y, node.y);
            else return Integer.compare(this.x, node.x);
        }
    }
}

