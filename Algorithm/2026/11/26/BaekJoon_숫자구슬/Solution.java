import java.io.*;

class Main {

    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        int[] arr = new int[n];
        int total = 0;

        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            total += arr[i];
        }

        int target = binarySearch(arr, total, m);
        System.out.println(buildString(arr, target, m));
    }

    private static int binarySearch(int[] arr, int total, int m) {
        int left = 0, right = total;
        int target = total;

        while(left <= right) {
            int mid = (left + right) >> 1;

            if(countGroup(arr, mid, m) <= m) {
                target = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return target;
    }

    private static int countGroup(int[] arr, int target, int m) {
        int val = 0, cnt = 1;

        for(int num : arr) {
            if(num > target) return m + 1;

            if(val + num > target) {
                cnt++;
                val = 0;
            }

            val += num;
        }

        return cnt;
    }

    private static String buildString(int[] arr, int target, int m) {
        StringBuilder sb = new StringBuilder();
        sb.append(target).append('\n');

        int val = 0, cnt = 0, remain = m;

        for(int i = 0; i < arr.length; i++) {
            if(val + arr[i] > target || arr.length - i == remain - 1) {
                sb.append(cnt).append(' ');
                remain--;
                val = 0;
                cnt = 0;
            }

            val += arr[i];
            cnt++;
        }

        sb.append(cnt);

        return sb.toString();
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();

        int n = 0;

        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}