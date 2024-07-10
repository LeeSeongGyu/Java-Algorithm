package BOJ.DynamicProgramming.P2482_색상환;

import java.io.*;

public class Main {

    static int n, k;
    static int[][] dp;
    static int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        dp = new int[n + 1][n + 1];

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for (int i = 3; i <= n; i++) {
            for (int j = 2; j <= i / 2; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[n][k];
    }
}
