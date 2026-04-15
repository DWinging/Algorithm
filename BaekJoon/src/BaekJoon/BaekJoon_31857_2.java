package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_31857_2 {

    private static class Node {
        Node pre;
        Node next;
        char item;

        Node(char item) {
            this.item = item;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        Node[] belt1 = inputBelt(n, r, br.readLine());
        Node[] belt2 = inputBelt(n, r, br.readLine());

        operateFactory(belt1, belt2, n, q, br);
        System.out.println(getBeltInfo(belt1[0], n));
        System.out.println(getBeltInfo(belt2[0], n));
    }

    private static Node[] inputBelt(int n, int r, String text) {
        Node[] belt = new Node[n];
        for(int i = 0; i < n; i++) {
            belt[i] = new Node(text.charAt(i));
        }

        for(int i = 0; i < n; i++) {
            belt[i].pre = belt[(i - 1 + n) % n];
            belt[i].next = belt[(i + 1) % n];
        }

        return new Node[]{belt[0], belt[r-1]};
    }

    private static void operateFactory(Node[] belt1, Node[] belt2, int n, int q, BufferedReader br) throws IOException {
        while(q-- > 0) {
            String command = br.readLine();
            if(command.charAt(0) == 'S') {
                swapBeltHead(belt1, belt2);
                swapBeltTail(belt1, belt2);
            }
            else if(command.charAt(0) == 'L') {
                if (command.charAt(2) == '1') {
                    rotateLeft(belt1);
                } else {
                    rotateLeft(belt2);
                }
            }
            else if(command.charAt(0) == 'R') {
                if (command.charAt(2) == '1') {
                    rotateRight(belt1);
                } else {
                    rotateRight(belt2);
                }
            }
            else if(command.charAt(0) == 'I') {
                belt1[1] = belt1[1].next;
                belt2[1] = belt2[1].next;
            }
            else {
                belt1[1] = belt1[1].pre;
                belt2[1] = belt2[1].pre;
            }
        }
    }

    private static void swapBeltHead(Node[] belt1, Node[] belt2) {
        Node head1 = belt1[0];
        Node head2 = belt2[0];

        Node cur1 = head1.pre;
        cur1.next = head2;

        Node cur2 = head2.pre;
        cur2.next = head1;

        head1.pre = cur2;
        head2.pre = cur1;

        belt1[0] = head2;
        belt2[0] = head1;
    }

    private static void swapBeltTail(Node[] belt1, Node[] belt2) {
        Node tail1 = belt1[1];
        Node tail2 = belt2[1];

        Node cur1 = tail1.next;
        cur1.pre = tail2;

        Node cur2 = tail2.next;
        cur2.pre = tail1;

        tail1.next = cur2;
        tail2.next = cur1;

        belt1[1] = tail2;
        belt2[1] = tail1;
    }

    private static void rotateLeft(Node[] belt) {
        belt[0] = belt[0].next;
        belt[1] = belt[1].next;
    }

    private static void rotateRight(Node[] belt) {
        belt[0] = belt[0].pre;
        belt[1] = belt[1].pre;
    }

    private static String getBeltInfo(Node cur, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(cur.item);
            cur = cur.next;
        }
        return sb.toString();
    }
}
