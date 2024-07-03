package 백준.다이나믹프로그래밍.P11057_오르막수;

import java.io.*;
import java.util.*;

public class Main {

    static int n, MOD = 10_007;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[1001][10];

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        Arrays.fill(dp[1], 1);

        for(int i = 2; i <= n; i++){
            for(int j = 1; j < 10; j++){
                for(int k = 1; k <= j; k++){
                    dp[i][k] = (dp[i][k] + dp[i - 1][j]) % MOD;
                }
            }
        }

        int count = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 10; j++){
                count = (count + dp[i][j]) % MOD;
            }
        }

        return count;
    }
}
