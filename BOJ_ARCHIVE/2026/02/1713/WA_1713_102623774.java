/**
 * [BOJ] 1713 - 후보 추천하기
 * - 제출 날짜: 2026년 2월 4일
 * - 결과: 틀렸습니다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer> arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int recoNum;
    static List<int[]> resultNumList;
    static boolean[] bool = new boolean[101];

    public static void main(String[] args)throws IOException{
        //최종 남아있는애들 출력 
        n = Integer.parseInt(br.readLine());
        recoNum = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        resultNumList = new ArrayList<>();
        for(int i =0; i<100; i++){
            bool[i] = false;
        }


        for(int i =0; i<recoNum; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        for(int i =0; i<recoNum; i++){
            //추천받은거 list 에 있는 경우 
            if(bool[arr.get(i)]){
                for(int j=0; j<n; j++){
                    if(resultNumList.get(j)[0] ==arr.get(i)){
                        resultNumList.get(j)[1]++;
                    }
                }
            }
            else{ //없는 경우
                //빈자리 있는 경우 넣어줌
                if(resultNumList.size()<n){
                    resultNumList.add(new int[]{arr.get(i),1}); 
                    bool[arr.get(i)] = true;
                }  
                //빈자리 없으면 추천수 젤 작은거 , 숫자가 같으면 들어온지 오래된거 뺌 ->추천수0 
                else{
                    int minRecoIdx =resultNumList.get(0)[1]; //추천수
                    int minIndex=0; //인덱스
                    for(int k=0; k<resultNumList.size(); k++){
                        if(minRecoIdx > resultNumList.get(k)[1]){ //추천수 작은거, 순서대로 뽑으니까 같으면 그대로 둠 
                            minRecoIdx = resultNumList.get(k)[1];
                            minIndex = k; //빼야될 수 
                        }
                    }
                    
                    bool[resultNumList.get(minIndex)[0]] = false;
                    resultNumList.remove(minIndex); //빼고 새거 
                    resultNumList.add(new int[]{arr.get(i),1});
                    bool[arr.get(i)] = true; 
                }
            }
        }
        resultNumList.sort((a,b)->b[1]-a[1]);
        for(int[] result : resultNumList){
            System.out.print(result[0]+" ");
        }
        
    }
    
}
