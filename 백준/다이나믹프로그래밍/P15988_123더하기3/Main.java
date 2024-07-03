package 백준.다이나믹프로그래밍.P15988_123더하기3;

import java.io.*;
import java.util.*;

public class Main {

    static int T, n;
    static Long[] dp;
    static final long MOD = 1_000_000_009;
    static final int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dp = new Long[MAX + 1];
        solution();

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            bw.write(dp[n] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void solution() {
        dp[0] = 1l;
        dp[1] = 1l;
        dp[2] = 2l;

        for(int i = 3; i <= MAX; i++){
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }
    }
}
