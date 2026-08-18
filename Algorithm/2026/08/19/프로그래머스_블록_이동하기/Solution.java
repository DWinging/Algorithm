import java.util.*;

class Solution {
    
    static Queue<int[]> que = new ArrayDeque<>();
    static boolean[][][] visited;
    static int n;
    
    public int solution(int[][] board) {
        int answer = -1;
        n = board.length;
        
        que.add(new int[]{0, 0, 0, 1, 0, 0});
            
        visited = new boolean[n][n][4];
        visited[0][0][0] = true;
        visited[0][1][2] = true;
        
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cy1 = cur[0];
            int cx1 = cur[1];
            int cy2 = cy1 + cur[2];
            int cx2 = cx1 + cur[3];
            int flip = cur[4];
            int time = cur[5];
            
            if((cy1 == n - 1 && cx1 == n - 1) 
               || (cy2 == n - 1 && cx2 == n - 1)) {
                return time;
            }
            
            if(flip == 0) {
                if(cx1 - 1 >= 0 && board[cy1][cx1 - 1] == 0) {
                    move(cy1, cx1 - 1, cy1, cx1, time, 0);
                }
                
                if(cx2 + 1 < n && board[cy2][cx2 + 1] == 0) {
                    move(cy2, cx2, cy2, cx2 + 1, time, 0);
                }
                
                if(cy1 + 1 < n && cy2 + 1 < n
                  && board[cy1 + 1][cx1] == 0 && board[cy2 + 1][cx2] == 0) {
                    move(cy1 + 1, cx1, cy2 + 1, cx2, time, 0);
                    move(cy1, cx1, cy1 + 1, cx1, time, 1);
                    move(cy2, cx2, cy2 + 1, cx2, time, 1);
                }
                
                if(cy1 - 1 >= 0 && cy2 - 1 >= 0
                  && board[cy1 - 1][cx1] == 0 && board[cy2 - 1][cx2] == 0) {
                    move(cy1 - 1, cx1, cy2 - 1, cx2, time, 0);
                    move(cy1 - 1, cx1, cy1, cx1, time, 1);
                    move(cy2 - 1, cx2, cy2, cx2, time, 1);
                }
            } else {
                if(cy1 - 1 >= 0 && board[cy1 - 1][cx1] == 0) {
                    move(cy1 - 1, cx1, cy1, cx1, time, 1);
                }
                
                if(cy2 + 1 < n && board[cy2 + 1][cx2] == 0) {
                    move(cy2, cx2, cy2 + 1, cx2, time, 1);
                }
                
                if(cx1 + 1 < n && cx2 + 1 < n
                  && board[cy1][cx1 + 1] == 0 && board[cy2][cx2 + 1] == 0) {
                    move(cy1, cx1 + 1, cy2, cx2 + 1, time, 1);
                    move(cy1, cx1, cy1, cx1 + 1, time, 0);
                    move(cy2, cx2, cy2, cx2 + 1, time, 0);
                }
                
                if(cx1 - 1 >= 0 && cx2 - 1 >= 0
                  && board[cy1][cx1 - 1] == 0 && board[cy2][cx2 - 1] == 0) {
                    move(cy1, cx1 - 1, cy2, cx2 - 1, time, 1);
                    move(cy1, cx1 - 1, cy1, cx1, time, 0);
                    move(cy2, cx2 - 1, cy2, cx2, time, 0);
                }
            }
        }
        
        
        return answer;
    }
    
    private void move(int y1, int x1, int y2, int x2, int time, int d) {
        if(!visited[y1][x1][d] && !visited[y2][x2][d + 2]) {
            visited[y1][x1][d] = true;
            visited[y2][x2][d + 2] = true;
            que.add(new int[]{y1, x1, d, d ^ 1, d, time + 1});
        }            
    }
}