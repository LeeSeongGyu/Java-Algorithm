package BOJ.Greedy.P11501_주식;

import java.io.*;
import java.util.*;

public class Main {

    static int T, n;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            n = Integer.parseInt(br.readLine());
            price = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < n; i++){
                price[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(solution() + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static long solution(){
        int max = price[price.length - 1];
        long sum = 0;

        for(int i = price.length - 2; i >= 0; i--){
            if(price[i] > max){
                max = price[i];
            }
            else {
                sum += max - price[i];
            }
        }

        return sum;
    }
}
