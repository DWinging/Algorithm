package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_2563 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int paper_num = in.nextInt();

        int[][] drawing_paper = new int[100][100]; // 도화지 생성.
//		int[][] color_paper = new int[paper_num][2]; // 각 색종이 입력받기.
        int area = 0;


//		for (int i = 0; i < paper_num; i++) {
//			color_paper[i][0] = in.nextInt(); // 색종이의 왼쪽밑 꼭짓점의 값
//			color_paper[i][1] = in.nextInt(); // 색종이의 왼쪽밑 꼭짓점의 값
//		}
//
//		for (int i = 0; i < paper_num; i++) { // 색종이인 부분
//			for (int j = 0; j < 10; j++) {
//				for (int k = 0; k < 10; k++) {
//					drawing_paper[(color_paper[i][0] + j)][(color_paper[i][1] + k)] = 1;
//				}
//			}
//		}
        // 색종이를 별도의 배열 color_paper로 저장하지 않고 바로 drawing_paper에 저장하면 됩니다.
//        for(int i = 0; i < paper_num; i++){
//            int x = in.nextInt();
//            int y = in.nextInt();
//            for(int j = x; j < x + 10; j++){
//                for(int k = y; k < y + 10; k++){
//                    drawing_paper[j][k] = 1;
//                }
//            }
//        }
        while(paper_num-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            for(int j = x; j < x + 10; j++){
                Arrays.fill(drawing_paper[j], y, y+10, 1);
            }
        }


        for (int i = 0; i < 100; i++) { // 색종이인 부분 0에서 1로 바꾸기
            for (int j = 0; j < 100; j++) {
                if (drawing_paper[i][j] == 1) {
                    area += 1;
                }
                // area += ( drawing_paper[i][j] == 1 ) ? 1 : 0 ;
            }
        }
        System.out.println(area);
    }
}
