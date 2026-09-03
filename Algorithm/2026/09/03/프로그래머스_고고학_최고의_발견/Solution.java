class Solution {
    
    final static int[][] DIST = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    int res = Integer.MAX_VALUE;
        
    public int solution(int[][] clockHands) {
        int n = clockHands.length;
        int[][] arr = new int[n][n];
        
        searchFirstRow(arr, clockHands, n, 0, 0);
         
        return res;
    }
    
    private void searchFirstRow(int[][] arr, int[][] clockHands, int n, int d, int cnt) {
        
        if(cnt > res) return;
        
        if(n == d) {
            copyBoard(clockHands, arr, n);
            simulate(arr, n, cnt);
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            rotate(clockHands, n, 0, d, i);            
            searchFirstRow(arr, clockHands, n, d + 1, cnt + i);
            rotate(clockHands, n, 0, d, 4 - i);
        }
    }
    
    private void copyBoard(int[][] clockHands, int[][] arr, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = clockHands[i][j];
            }
        }
    }
    
    private void simulate(int[][] arr, int n, int cnt) {
        for(int y = 1; y < n; y++) {
            for(int x = 0; x < n; x++) {
                if(arr[y - 1][x] > 0) {
                    cnt += 4 - arr[y - 1][x];
                    rotate(arr, n, y, x, 4 - arr[y - 1][x]);
                }
            }
        }
        
        for (int x = 0; x < n; x++) {
            if (arr[n - 1][x] != 0) return;
        }
        
        if(res > cnt) res = cnt;
    }
    
    private void rotate(int[][] arr, int n, int y, int x, int val) {
        for(int[] d : DIST) {
            int ny = y + d[0];
            int nx = x + d[1];
            
            if(ny >= 0 && ny < n && nx >= 0 && nx < n) {
                arr[ny][nx] = (arr[ny][nx] + val) % 4;                
            }
        }
    }
}