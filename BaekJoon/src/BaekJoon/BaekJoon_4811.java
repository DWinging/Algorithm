package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_4811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int aSize = Integer.parseInt(st.nextToken());
        int bSize = Integer.parseInt(st.nextToken());

        int[] aPiece = inputPieces(aSize, br);
        int[] bPiece = inputPieces(bSize, br);

        int[] sumPieceToA = sumOf(aPiece, aSize);
        int[] sumPieceToB = sumOf(bPiece, bSize);

        Map<Integer, Integer> aSumCnt = calculateCountSum(sumPieceToA, aSize, size);
        Map<Integer, Integer> bSumCnt = calculateCountSum(sumPieceToB, bSize, size);

        System.out.println(countOf(aSumCnt, bSumCnt, size));
    }

    private static int[] inputPieces(int size, BufferedReader br) throws IOException {
        int[] pieces = new int[size];
        for(int i = 0; i < size; i++) pieces[i] = Integer.parseInt(br.readLine());
        return pieces;
    }

    private static int[] sumOf(int[] pieces, int size) {
        int[] sumPieces = new int[size * 2 + 1];
        for(int i = 1; i <= size * 2; i++) sumPieces[i] = sumPieces[i-1] + pieces[(i-1) % size];
        return sumPieces;
    }

    private static Map<Integer, Integer> calculateCountSum(int[] sumArr, int size, int maxSize) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        if(sumArr[size] <= maxSize) map.put(sumArr[size], 1);
        for(int s = 1; s < size; s++) {
            for(int i = 0; i < size; i++) {
                int temp = sumArr[i + s] - sumArr[i];
                if(temp > maxSize) continue;
                map.compute(temp, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        return map;
    }

    private static int countOf(Map<Integer, Integer> aMap, Map<Integer, Integer> bMap, int size) {
        int cnt = 0;
        for(int i = 0; i <= size; i++) {
            if(aMap.containsKey(i) && bMap.containsKey(size - i)) {
                cnt += aMap.get(i) * bMap.get(size - i);
            }
        }
        return cnt;
    }
}
