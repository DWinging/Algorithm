package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_17143 {

    private static class Shark {
        int y, x, speed, dict, size;

        Shark(int y, int x, int s, int d, int z) {
            this.y = y;
            this.x = x;
            this.speed = s;
            this.dict = d;
            this.size = z;
        }
    }

    static Map<Integer, Shark> shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] area = new int[r][c];
        shark = new HashMap<>();

        inputSharkInfo(r, c, m, area, br);
        System.out.println(getFishingTotalSize(r, c, m, area));
    }

    private static void inputSharkInfo(int r, int c, int m, int[][] area,BufferedReader br) throws IOException {
        StringTokenizer st;
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            shark.put(i, new Shark(y, x, s, d, z));
            area[y][x] = i;
        }
    }

    private static int getFishingTotalSize(int r, int c, int m, int[][] area) {
        int size = 0;
        for(int i = 0; i < c && !shark.isEmpty(); i++) {
            // step1
            int temp = fishingShark(area, r, i);
            if(temp != 0) {
                size += temp;
            }
            //step2
            moveShark(area, r, c);
            settingAreaAndDeadCount(area);
        }
        return size;
    }

    private static int fishingShark(int[][] area, int y, int x) {
        for(int i = 0; i < y; i++) {
            if(area[i][x] != 0) {
                int num = area[i][x];
                int size = shark.get(num).size;
                shark.remove(num);
                area[i][x] = 0;
                return size;
            }
        }
        return 0;
    }

    private static void moveShark(int[][] area, int y, int x) {
        for(Shark s : shark.values()) {
            area[s.y][s.x] = 0;
            if(s.dict == 0) s.y = movingBackward(s, s.y, y, s.speed, 1);
            else if(s.dict == 1) s.y = movingForward(s, s.y, y, s.speed, 0);
            else if(s.dict == 2) s.x = movingForward(s, s.x, x, s.speed, 3);
            else if(s.dict == 3) s.x = movingBackward(s, s.x, x, s.speed, 2);
        }
    }

    private static int movingForward(Shark s, int c, int node, int speed, int dict) {
        if(c + speed < node) {
            c += speed;
        }
        else {
            int temp = (c + speed) - node + 1;
            if ((temp / (node - 1)) % 2 == 0) {
                c = (node - 1) - temp % (node -1);
                s.dict = dict;
            }
            else {
                c = temp % (node -1);
            }
        }
        return c;
    }

    private static int movingBackward(Shark s, int c, int node, int speed, int dict) {
        if(c - speed >= 0) {
            c -= speed;
        }
        else {
            int temp = speed - c;
            if ((temp / (node - 1)) % 2 == 0) {
                c = temp % (node -1);
                s.dict = dict;
            }
            else {
                c = (node - 1) - temp % (node -1);
            }
        }
        return c;
    }

    private static void settingAreaAndDeadCount(int[][] area) {
        List<Integer> removeList = new ArrayList<>();
        for(int key : shark.keySet()) {
            int ny = shark.get(key).y;
            int nx = shark.get(key).x;
            if(area[ny][nx] == 0) {
                area[ny][nx] = key;
            }
            else {
                int idx = area[ny][nx];
                if(shark.get(key).size > shark.get(idx).size) {
                    area[ny][nx] = key;
                    removeList.add(idx);
                }
                else {
                    removeList.add(key);
                }
            }
        }

        for(int idx : removeList) {
            shark.remove(idx);
        }
    }
}
