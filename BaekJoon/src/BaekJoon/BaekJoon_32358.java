package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_32358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] point = new int[n];
        long len = 0L;
        int cnt = 0, position = 0;

        StringTokenizer st;
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("1")) {
                point[cnt++] = Integer.parseInt(st.nextToken());
            }
            else {
                if(cnt == 0) continue;

                Arrays.sort(point, 0, cnt);
                if(point[0] >= position || point[cnt-1] <= position) {
                    for(int i = 0; i < cnt; i++)  {
                        len += Math.abs(point[i] - position);
                        position = point[i];
                    }
                }
                else {
                    int right = binarySearch(point, position, cnt);
                    int left = right - 1;

                    while (left >= 0 && right < cnt) {
                        if (position - point[left] <= point[right] - position) {
                            len += position - point[left];
                            position = point[left--];
                        } else {
                            len += point[right] - position;
                            position = point[right++];
                        }
                    }

                    while (left >= 0) {
                        len += position - point[left];
                        position = point[left--];
                    }

                    while (right < cnt) {
                        len += point[right] - position;
                        position = point[right++];
                    }
                }

                cnt = 0;
            }
        }
        System.out.println(len);
    }

    private static int binarySearch(int[] point, int position, int cnt) {
        int left = 0, right = cnt-1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(position == point[mid]) return mid;

            if(position < point[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}
