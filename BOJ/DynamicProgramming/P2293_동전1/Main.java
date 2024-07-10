package BOJ.DynamicProgramming.P2293_동전1;

import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] coin, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coin = new int[n];
        dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        dp[0] = 1;

        for(int c: coin){
            for(int i = 0; i + c <= k; i++){
                dp[i + c] += dp[i];
            }
        }

        return dp[k];
    }
}
