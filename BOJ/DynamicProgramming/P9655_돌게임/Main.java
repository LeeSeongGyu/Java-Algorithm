package BOJ.DynamicProgramming.P9655_돌게임;

import java.io.*;

public class Main {

    static int n;
    static boolean[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new boolean[1001];

        bw.write(solution());
        bw.flush();
        bw.close();
    }

    public static String solution(){
        for(int i = 1; i <= n; i++){
            if(i % 3 == 0){
                dp[i] = !dp[i - 3];
            } else {
                dp[i] = !dp[i - 1];
            }
        }

        if(dp[n]){
            return "SK";
        } else {
            return "CY";
        }
    }
}
