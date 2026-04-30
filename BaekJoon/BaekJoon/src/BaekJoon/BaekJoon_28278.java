package BaekJoon;

import java.util.Scanner;
import java.util.Stack;

public class BaekJoon_28278 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();   //Java에는 Stack Class가 있습니다.
        int cmd_for = in.nextInt();

        //Code Example if-else Ver
        while(cmd_for-- > 0){
            int cmd = in.nextInt();
            if(cmd == 1){
                stack.add(in.nextInt());
            }
            else if(cmd == 2){
                System.out.println(stack.isEmpty() ? -1 : stack.pop());
            }
            else if(cmd == 3){
                System.out.println(stack.size());
            }
            else if(cmd == 4){
                System.out.println(stack.isEmpty() ? 1 : 0);
            }
            else {
                System.out.println(stack.isEmpty() ? -1 : stack.peek());
            }
        }

        //Code Example(가독성 떨어짐)
        while(cmd_for-- > 0){
            int cmd = in.nextInt();
            if(cmd == 1) stack.add(in.nextInt());
            else if(cmd == 2) System.out.println(stack.isEmpty() ? -1 : stack.pop());
            else if(cmd == 3) System.out.println(stack.size());
            else if(cmd == 4) System.out.println(stack.isEmpty() ? 1 : 0);
            else System.out.println(stack.isEmpty() ? -1 : stack.peek());
        }

        //Code Example - Switch case Ver
        while(cmd_for-- > 0){
            switch (in.nextInt()) {
                case 1:
                    stack.add(in.nextInt());
                    break;
                case 2:
                    System.out.println(stack.isEmpty() ? -1 : stack.pop());
                    break;
                case 3:
                    System.out.println(stack.size());
                    break;
                case 4:
                    System.out.println(stack.isEmpty() ? 1 : 0);
                    break;
                case 5:
                    System.out.println(stack.isEmpty() ? -1 : stack.peek());
                    break;
            }
        }
    }
}
