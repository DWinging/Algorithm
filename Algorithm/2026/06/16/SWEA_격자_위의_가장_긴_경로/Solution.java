import java.io.*;

class Solution {
    static int c;
    
    public static void main(String[] args) throws Exception {
        c = System.in.read();
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        
        int[][] dp_base = new int[500][500];
        int[][] dp_left = new int[500][500];
        int[][] dp_up = new int[500][500];
        int[][] dp_final = new int[500][500];
        int[][] map = new int[500][500];

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    while(c <= ' ') c = System.in.read();
                    map[i][j] = c;
                    c = System.in.read();
                    
                    dp_base[i][j] = -1;
                    dp_left[i][j] = -1;
                    dp_up[i][j] = -1;
                    dp_final[i][j] = -1;
                }
            }

            int ans = 0;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(map[i][j] == '#') continue;
                    if(i == 0 && j == 0) {
                        dp_base[i][j] = 1;
                        ans = Math.max(ans, 1);
                        continue;
                    }
                    int max_prev = -1;
                    if(i > 0 && dp_base[i-1][j] != -1) max_prev = Math.max(max_prev, dp_base[i-1][j]);
                    if(j > 0 && dp_base[i][j-1] != -1) max_prev = Math.max(max_prev, dp_base[i][j-1]);
                    
                    if(max_prev != -1) {
                        dp_base[i][j] = max_prev + 1;
                        ans = Math.max(ans, dp_base[i][j]);
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = m - 1; j >= 0; j--) {
                    if(map[i][j] == '#') continue;
                    int max_val = -1;
                    
                    if(i > 0 && j + 1 < m && map[i][j+1] == '.') {
                        if(dp_base[i-1][j+1] != -1) {
                            max_val = Math.max(max_val, dp_base[i-1][j+1] + 2);
                        }
                    }
                    if(j + 1 < m && dp_left[i][j+1] != -1) {
                        max_val = Math.max(max_val, dp_left[i][j+1] + 1);
                    }
                    
                    dp_left[i][j] = max_val;
                    if(max_val != -1) ans = Math.max(ans, max_val);
                }
            }

            for(int i = n - 1; i >= 0; i--) {
                for(int j = 0; j < m; j++) {
                    if(map[i][j] == '#') continue;
                    int max_val = -1;
                    
                    if(i + 1 < n && j > 0 && map[i+1][j] == '.') {
                        if(dp_base[i+1][j-1] != -1) {
                            max_val = Math.max(max_val, dp_base[i+1][j-1] + 2);
                        }
                    }
                    if(i + 1 < n && dp_up[i+1][j] != -1) {
                        max_val = Math.max(max_val, dp_up[i+1][j] + 1);
                    }
                    
                    dp_up[i][j] = max_val;
                    if(max_val != -1) ans = Math.max(ans, max_val);
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(map[i][j] == '#') continue;
                    int max_val = -1;
                    
                    if(i > 0 && dp_left[i-1][j] != -1) {
                        max_val = Math.max(max_val, dp_left[i-1][j] + 1);
                    }
                    if(j > 0 && dp_up[i][j-1] != -1) {
                        max_val = Math.max(max_val, dp_up[i][j-1] + 1);
                    }
                    if(i > 0 && dp_final[i-1][j] != -1) {
                        max_val = Math.max(max_val, dp_final[i-1][j] + 1);
                    }
                    if(j > 0 && dp_final[i][j-1] != -1) {
                        max_val = Math.max(max_val, dp_final[i][j-1] + 1);
                    }
                    
                    dp_final[i][j] = max_val;
                    if(max_val != -1) ans = Math.max(ans, max_val);
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static int readInt() throws Exception {
        while(c <= ' ') c = System.in.read();
        int res = 0;
        while(c >= '0' && c <= '9') {
            res = (res << 3) + (res << 1) + (c & 15);
            c = System.in.read();
        }
        return res;
    }
}