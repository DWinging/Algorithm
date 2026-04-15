package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_9328 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n, m, result;
    static char[][] map;
    static int[] keys;
    static Queue<Door> door;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        door = new LinkedList<>();

        while(testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            result = 0;
            door.clear();
            map = new char[n+2][m+2];
            visit = new boolean[n+2][m+2];
            Arrays.fill(map[0], '.');
            Arrays.fill(map[n+1], '.');
            for(int i = 1; i <= n; i++){
                String temp = br.readLine();
                map[i][0] = '.';
                map[i][m+1] = '.';
                for(int j = 1; j <= m; j++){
                    map[i][j] = temp.charAt(j-1);
                }
            }

            keys = new int[26];
            String key = br.readLine();
            for(char k : key.toCharArray()){
                if(k == '0') break;
                keys[k - 'a'] = 1;
            }

            bfs(0, 0);

            while(true) {
                boolean update = false;
                int size = door.size();
                for(int i = 0; i < size; i++){
                    Door d = door.poll();
                    if(keys[map[d.x][d.y] - 'A'] != 1){
                        door.offer(d);
                        continue;
                    }
                    update = true;
                    bfs(d.x, d.y);
                }

                if(door.isEmpty() || !update) {
                    break;
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y){
        Queue<Door> que = new LinkedList<>();
        que.add(new Door(x, y));
        visit[x][y] = true;

        while(!que.isEmpty()) {
            Door cur = que.poll();
            for(int i = 0; i < 4; i++){
                int rx = cur.x + dx[i];
                int ry = cur.y + dy[i];
                if(rx >= 0 && rx < n+2 && ry >= 0 && ry < m+2 && !visit[rx][ry] && map[rx][ry] != '*'){
                    visit[rx][ry] = true;
                    if(map[rx][ry] >= 'a' && map[rx][ry] <= 'z') {
                        keys[map[rx][ry] - 'a'] = 1;
                        que.offer(new Door(rx, ry));
                    }
                    else if(map[rx][ry] >= 'A' && map[rx][ry] <= 'Z') {
                        if(keys[map[rx][ry] - 'A'] == 1) {
                            que.offer(new Door(rx, ry));
                        }
                        else {
                            door.offer(new Door(rx, ry));
                        }
                    }
                    else {
                        if(map[rx][ry] == '$') {
                            result++;
                        }
                        que.offer(new Door(rx, ry));
                    }
                }
            }
        }
    }

    private static class Door {
        int x;
        int y;

        Door(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
