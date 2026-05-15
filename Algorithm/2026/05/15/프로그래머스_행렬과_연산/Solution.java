import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        if(rc[0].length == 2) {
            return exceptionCase(rc, operations);
        } else {
            return solve(rc, operations);
        }
    }
    
    private int[][] exceptionCase(int[][] rc, String[] operations) {
        int row = rc.length, col = rc[0].length;        
        Deque<Integer> leftQue = new ArrayDeque<>();
        Deque<Integer> rightQue = new ArrayDeque<>();
        
        for(int i = 0; i < row; i++) {
            leftQue.addLast(rc[i][0]);
            rightQue.addFirst(rc[i][1]);
        }
        
        for(String operation : operations) {
            if(operation.equals("Rotate")) {
                rightQue.addLast(leftQue.pollFirst());
                leftQue.addLast(rightQue.pollFirst());
            } else {
                rightQue.addLast(rightQue.pollFirst());
                leftQue.addFirst(leftQue.pollLast());
            }
        }
        
        int[][] res = new int[row][col];
        for(int i = 0; i < row; i++) {
            res[i][0] = leftQue.pollFirst();
            res[i][1] = rightQue.pollLast();
        }
        return res;
    }
    
    private static int[][] solve(int[][] rc, String[] operations) {
        int row = rc.length, col = rc[0].length;
        
        Deque<Integer> leftQue = new ArrayDeque<>();
        Deque<Integer> rightQue = new ArrayDeque<>();
        Deque<Integer>[] midQue = new ArrayDeque[row];
        
        for(int i = 0; i < row; i++) {
            leftQue.addLast(rc[i][0]);
            rightQue.addFirst(rc[i][col - 1]);
            midQue[i] = new ArrayDeque<>();
            
            for(int j = 1; j < col - 1; j++) {
                midQue[i].addLast(rc[i][j]);
            }
        }
        
        int idx = 0;
        for(String operation : operations) {
            if(operation.equals("Rotate")) {
                midQue[idx].addFirst(leftQue.pollFirst());
                rightQue.addLast(midQue[idx].pollLast());
                midQue[(idx - 1 + row) % row].addLast(rightQue.pollFirst());
                leftQue.addLast(midQue[(idx - 1 + row) % row].pollFirst());
            } else {
                rightQue.addLast(rightQue.pollFirst());
                leftQue.addFirst(leftQue.pollLast());
                idx = (idx - 1 + row) % row;
            }
        }
        
        int[][] res = new int[row][col];
        for(int i = 0; i < row; i++) {
            res[i][0] = leftQue.pollFirst();
            res[i][col - 1] = rightQue.pollLast();
            for(int j = 1; j < col - 1; j++) {
                res[i][j] = midQue[idx].pollFirst();
            }
            idx = (idx + 1) % row;
        }
        
        return res;
    }
}