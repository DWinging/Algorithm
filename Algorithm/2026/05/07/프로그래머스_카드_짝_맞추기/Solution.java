class Solution {
    
    private class Card {
        int[] card1, card2;
        
        void setOnBoard(int[][] board, int val) {
            board[card1[0]][card1[1]] = val;
            board[card2[0]][card2[1]] = val;
        }
    }
    
    final int[][] DIST = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    final int KIND = 6, ROW = 4, COL = 4;
    
    Card[] cards = new Card[KIND + 1];
    int[][] que = new int[ROW * COL][3];
    int[][] visited = new int[ROW][COL];
    
    int minCnt = Integer.MAX_VALUE, mark = 0;
    
    public int solution(int[][] board, int r, int c) {
        for(int i = 1; i <= KIND; i++) {
            cards[i] = new Card();
        }
        
        int bit = 0;
        for(int i = 0; i < ROW; i++) {
            for(int j = 0; j < COL; j++) {
                int val = board[i][j];
                if(board[i][j] == 0) continue;
                
                bit |= (1 << val);
                if(cards[val].card1 == null) {
                    cards[val].card1 = new int[] {i, j};
                } else {
                    cards[val].card2 = new int[] {i, j};
                }
            }
        }
        
        dfs(board, r, c, 0, bit);
            
        int answer = minCnt;
        return answer;
    }
    
    private void dfs(int[][] board, int r, int c, int cnt, int bit) {
        if(cnt >= minCnt) return;
        
        if(bit == 0) {
            minCnt = cnt;
            return;
        }
        
        int val = 0;
        for(int b = 1; b <= KIND; b++) {
            if((bit & (1 << b)) == 0) continue;
            int next = bit ^ (1 << b);
            
            int[] card1 = cards[b].card1;
            int[] card2 = cards[b].card2;
            
            // card1 -> card2 로 탐색하는 경우
            val = bfs(board, r, c, card1);
            val += bfs(board, card1[0], card1[1], card2);
            cards[b].setOnBoard(board, 0);
            dfs(board, card2[0], card2[1], cnt + val, next);
            cards[b].setOnBoard(board, b);
            
            // card2 -> card1 로 탐색하는 경우
            val = bfs(board, r, c, card2);
            val += bfs(board, card2[0], card2[1], card1);
            cards[b].setOnBoard(board, 0);
            dfs(board, card1[0], card1[1], cnt + val, next);
            cards[b].setOnBoard(board, b);
        }
    }
    
    private int bfs(int[][] board, int r, int c, int[] target) {
        int head = 0, tail = 0;
        que[tail][0] = r;
        que[tail][1] = c;
        que[tail][2] = 0;
        tail++;
        visited[r][c] = ++mark;
        
        while(head < tail) {
            int[] cur = que[head++];
            int cy = cur[0];
            int cx = cur[1];
            int cnt = cur[2];
            
            if(cy == target[0] && cx == target[1]) return cnt + 1;
            
            for(int[] d : DIST) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                
                if(!check(ny, nx, ROW, COL)) continue;
                
                // 방향키만 누르는 경우
                tail = checkNextPoint(ny, nx, cnt, tail);
                
                // ctrl + 방향키
                while (true) {
                    if (board[ny][nx] != 0) break;
                    
                    int ty = ny + d[0];
                    int tx = nx + d[1];

                    if (!check(ty, tx, ROW, COL)) break;

                    ny = ty;
                    nx = tx;
                }                
                tail = checkNextPoint(ny, nx, cnt, tail);
            }
        }
        return -1;
    }
    
    private boolean check(int y, int x, int r, int c) {
        return y >= 0 && y < r && x >= 0 && x < c;
    }
    
    private int checkNextPoint(int y, int x, int cnt, int tail) {
        if(visited[y][x] < mark) {
            visited[y][x] = mark;
            que[tail][0] = y;
            que[tail][1] = x;
            que[tail][2] = cnt + 1;
            tail++;            
        }
        return tail;
    }
}