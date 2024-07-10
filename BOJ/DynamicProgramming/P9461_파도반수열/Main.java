package BOJ.DynamicProgramming.P9461_파도반수열;

import java.io.*;

public class Main {

    static int T;
    static long[] dp = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        solution();

        for(int i = 0; i < T; i++){
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void solution(){
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        for(int i = 5; i <= 100; i++){
            dp[i] = dp[i - 1] + dp[i - 5];
        }
    }
}
