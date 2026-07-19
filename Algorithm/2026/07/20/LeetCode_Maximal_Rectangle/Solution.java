class Solution {

    int[][] stack;
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] arr = new int[row][col];
        for(int i = 0; i < col; i++) {
            int h = 0;
            for(int j = 0; j < row; j++) {
                if(matrix[j][i] == '0') {
                    h = 0;
                } else {
                    arr[j][i] = ++h;
                }
            }
        }

        stack = new int[col][2];
        int res = 0;
        for(int i = 0; i < row; i++) {
            int val = calculateArea(arr[i], col);
            if(val > res) res = val;
        }

        return res;
    }

    private int calculateArea(int[] arr, int n) {
        int top = -1, val = 0;

        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) {
                int temp = finishStack(top);
                top = -1;

                if(temp > val) val = temp;
            } else {
                int cnt = 1;
                while(top > -1 && stack[top][0] >= arr[i]) {
                    int h = stack[top][0];
                    cnt += stack[top--][1];

                    int temp = h * (cnt - 1);
                    if (temp > val) val = temp;
                }

                stack[++top][0] = arr[i];
                stack[top][1] = cnt;
            }
        }

        int temp = finishStack(top);
        if(temp > val) val = temp;

        return val;
    }

    private int finishStack(int top) {
        int cnt = 0, res = 0;
        for(int i = top; i >= 0; i--) {
            int h = stack[i][0];
            cnt += stack[i][1];
            int val = h * cnt;

            if(val > res) res = val;            
        }
        return res;
    }
}