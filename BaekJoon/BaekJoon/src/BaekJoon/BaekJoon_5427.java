package BaekJoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_5427 {

    static int w, h;
    static int[][] visit;
    static String[] map;
    static int[] xList = {1, -1, 0, 0};
    static int[] yList = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while(testCase-- > 0){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            visit = new int[w][h];
            map = new String[w];

            Queue<int[]> fire = new LinkedList<>();
            Queue<int[]> start = new LinkedList<>();
            for(int i = 0; i < w; i++){
                map[i] = br.readLine();
                for(int j = 0; j < h; j++){
                    if(map[i].charAt(j) == '*'){
                        fire.add(new int[] {i, j});
                        visit[i][j] = 1;
                        continue;
                    }
                    if(map[i].charAt(j) == '@'){
                        start.add(new int[]{i, j});
                    }
                }
            }

            fireCheck(fire);
            bw.write(escape(start));
        }
        bw.flush();
        bw.close();
    }

    private static void fireCheck(Queue<int[]> que){


        while(!que.isEmpty()){
            int r = que.peek()[0];
            int c = que.peek()[1];
            que.poll();

            for(int i = 0; i < 4; i++){
                int x = xList[i] + r;
                int y = yList[i] + c;
                if(x >= 0 && x < w && y >= 0 && y < h && (map[x].charAt(y) == '.' || map[x].charAt(y) == '@') && (visit[x][y] == 0 || visit[x][y] > visit[r][c] + 1)){
                    visit[x][y] = visit[r][c] + 1;
                    que.add(new int[]{x, y});
                }
            }
        }
    }

    private static String escape(Queue<int[]> que){
        visit[que.peek()[0]][que.peek()[1]] = 1;

        while(!que.isEmpty()){
            int r = que.peek()[0];
            int c = que.peek()[1];
            que.poll();

            for(int i = 0; i < 4; i++){
                int x = xList[i] + r;
                int y = yList[i] + c;
                if(x >= 0 && x < w && y >= 0 && y < h && map[x].charAt(y) == '.' && (visit[x][y] == 0 || visit[x][y] > visit[r][c] + 1)){
                    visit[x][y] = visit[r][c] + 1;
                    que.add(new int[]{x, y});
                    continue;
                }
                if(x < 0 || x >= w || y < 0 || y >= h){
                    return visit[r][c] + "\n";
                }
            }
        }

        return "IMPOSSIBLE\n";
    }
}
