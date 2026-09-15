import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int[] arr1 = inputArray(n);
        int[] arr2 = inputArray(n);

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(arr1[i] != arr2[i]) {
                if(arr1[i] == arr2[i + 1] && arr1[i + 1] == arr2[i]) i++;
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static int[] inputArray(int n) throws IOException {
        int[] arr = new int[n + 1];
        while(c <= ' ') c = System.in.read();
        for(int i = 0; i < n; i++) {
            arr[i] = c & 15;
            c = System.in.read();
        }
        return arr;
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